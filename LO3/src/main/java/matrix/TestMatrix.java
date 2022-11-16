/*
 *  Date submitted: November 2021
 *  Assignment number: N/A
 *  Course name:  MATH 282
 *  Instructor:  Michael Grzesina
 */

package matrix;

/**
 * Tests the implementation of the Matrix class and its methods
 *
 * @author MATH 282
 * @version November 2021
 */
public class TestMatrix
{
    // Arrays with values to create test matrices from
    private static double[][] dArray1 = { { 12.0,  3.0, 52.0 },
                                         { -10.0, 45.0,  0.98 } };
    private static double[][] dArray2 = { {  1.0, 3.0 },
                                          { -1.0, 0.0 } };
    private static double[][] dArray3 = { { -2.0,  3.0 }, 
                                          {  0.0, -5.0 } };

    
    /**
     * Main method that runs the specified test code for testing matrices.
     * 
     * @param args  Arguments for the program - not used
     */
    public static void main( String[] args )
    {
        System.out.println("Hello World");
        
        testCreateMatrix();
        testAdd();
        testSubtract();
        testScalarMultiply();
        testMatrixMultiply();
        testGaussJordanElimination();
        testLeastSquares();
    }


    /**
     * Test matrix constructors.
     */
    private static void testCreateMatrix()
    {
        System.out.println("Testing creating matrices");

        Matrix m = new Matrix(dArray1);
        dArray1[0][0] = 0.0; // shouldn't affect m
                             // since our constructor makes a deep copy of the array

        System.out.println("Creating matrix from array:");
        System.out.println(m.toString());

        System.out.println("Creating matrix from another matrix (copy constructor):");
        IMatrix m2 = new Matrix((Matrix)m);
        ((Matrix)m).setElement(1, 1, 1.0); // shouldn't affect m2
                                           // because of deep copy
        System.out.println(m2);

        dArray1[0][0] = 12.0; // set it back so it doesn't affect other tests

        IMatrix m3 = new Matrix(3, 4);
        System.out.println(m3);
        
        // the matrix creation in the try/catch block should fail
        // just testing that we cannot create a 0 by 0 matrix
        try
        {
            m = new Matrix(new double[0][0]);
            System.out.println(m);
        }
        catch (Exception e)
        {
            System.out.println("Matrix could not be created\n" + e);
        }
    }


    /**
     * Test matrix addition.
     */
    private static void testAdd()
    {
        IMatrix m1 = new Matrix(dArray1);
        IMatrix m2 = new Matrix(dArray2);
        IMatrix m3 = new Matrix(dArray3);

        System.out.println("\n\nTesting matrix addition");
        IMatrix mSum = m2.add(m3);
        System.out.println(mSum);
        
        System.out.println(m1.add(m1));

        // the matrix addition in the try/catch block should fail
        // just testing that we cannot add matrices of differing sizes
        try
        {
            mSum = m1.add(m2);
            System.out.println(mSum);
        }
        catch (Exception e)
        {
            System.out.println("Couldn't add\n" + e);
        }
    }


    /**
     * Test scalar multiplication of matrices
     */
    private static void testScalarMultiply()
    {
        IMatrix m1 = new Matrix(dArray1);
        IMatrix m2 = new Matrix(dArray2);

        System.out.println("\n\nTesting scalar multiplication");
        IMatrix mResult = m2.scalarMultiply(10.0);
        System.out.println(mResult);

        mResult = m1.scalarMultiply(0.5);
        System.out.println(mResult);
    }


    /**
     * Test matrix subtraction.
     */
    private static void testSubtract()
    {
        IMatrix m1 = new Matrix(dArray1);
        IMatrix m2 = new Matrix(dArray2);
        IMatrix m3 = new Matrix(dArray3);

        System.out.println("\n\nTesting matrix subtraction");
        IMatrix mSum = m2.subtract(m3);
        System.out.println(mSum);
        
        System.out.println(m3.subtract(m2));

        System.out.println(m1.subtract(m1));
        
        // the matrix subtraction in the try/catch block should fail
        // just testing that we cannot subtract matrices of differing sizes
        try
        {
            mSum = m1.subtract(m2);
            System.out.println(mSum);
        }
        catch (Exception e)
        {
            System.out.println("Couldn't subtract\n" + e);
        }
    }

    
    /**
     * Test matrix multiplication.
     */
    private static void testMatrixMultiply()
    {
        IMatrix m1 = new Matrix(dArray1);
        IMatrix m2 = new Matrix(dArray2);
        IMatrix m3 = new Matrix(dArray3);

        System.out.println("\n\nTesting matrix multiplication");
        IMatrix mProduct = m2.multiply(m3);
        System.out.println(mProduct);
        
        System.out.println(m3.multiply(m2));

        mProduct = m2.multiply(m1);
        System.out.println(mProduct);

        // the matrix multiplication in the try/catch block should fail
        // just testing that we cannot multiply matrices where
        // columns of invoking matrix do not match rows of operands
        try
        {
            mProduct = m1.multiply(m2);
            System.out.println(mProduct);
        }
        catch (Exception e)
        {
            System.out.println("Couldn't multiply\n" + e);
        }
    }


    /**
     * Test Gauss-Jordan elimination
     */
    private static void testGaussJordanElimination()
    {
        double dGauss1[][] = { { 2.0,  4.0,  6.0,  46.0 },
                               { 3.0, 12.0, 12.0, 102.0 },
                               { 4.0,  5.0,  1.0,  28.0 } };
        double dGauss2[][] = { {  2.0,  3.0,  7.0 },
                               { -7.0, -3.0, 10.0 } };
        double dGauss3[][] = { {  3.0,  2.0, 7.0, 123.0 },
                               {  6.0,  4.0, 1.0,  98.0 },
                               { -3.0, -2.0, 8.0,  10.0 } };
        double dGauss4[][] = { { 0.0, 1.0, 0.0, 10.0 },
                              { 0.0, 0.0, 1.0, 20.0 },
                              { 1.0, 0.0, 0.0, 30.0 } };
        double dGauss5[][] = { { 1.0, 1.0,   2.0 },
                               { 1.0, 1.001, 2.0 } };
        
        System.out.println("\n\nTesting Gauss-Jordan elimination");
        IMatrix mTest = new Matrix(dGauss1);
        IMatrix mResult = mTest.gaussJordanElimination();
        System.out.println(mResult);
        
        mTest = new Matrix(dGauss2);
        System.out.println(mTest.gaussJordanElimination());
        
        // test example where swapping of rows is required for solution
        mTest = new Matrix(dGauss4);
        System.out.println(mTest.gaussJordanElimination());

        // the Gauss-Jordan elimination in the try/catch blocks should fail
        // just testing that we must start with an augmented matrix
        try
        {
            mTest = new Matrix(dArray2);
            System.out.println(mTest.gaussJordanElimination());
        }
        catch (Exception e)
        {
            System.out.println("Couldn't solve\n" + e);
        }
        // just testing that we can determine when a system of equations is not solvable
        try
        {
            mTest = new Matrix(dGauss3);
            System.out.println(mTest.gaussJordanElimination());
        }
        catch (Exception e)
        {
            System.out.println("Couldn't solve\n" + e);
        }
        
        // test ill-conditioned matrices
        // where a small change in the starting conditions
        // leads to a large change in the results
        mTest = new Matrix(dGauss5);
        System.out.println(mTest.gaussJordanElimination());
        dGauss5[1][2] = 2.001;
        mTest = new Matrix(dGauss5);
        System.out.println(mTest.gaussJordanElimination());
    }
    
    
    /**
     * Test least-squares polynomial coefficient calculations
     */
    private static void testLeastSquares()
    {
        double dPoints[][] = { { -1.0, 4.0 },
                               {  0.0, 2.0 },
                               {  1.0, 1.0 },
                               {  2.0, 2.0 },
                               {  3.0, 4.0 } };
        double dPoints2[][] = { { 0.0, 1.0 },
                                { 1.0, 1.0 },
                                { 2.0, 2.0 },
                                { 3.0, 4.0 },
                                { 4.0, 5.0 },
                                { 5.0, 6.0 } };
        
        System.out.println("\n\nTesting least-squares coefficient calculations");
        IMatrix mPoints = new Matrix(dPoints);
        System.out.println(mPoints.leastSquares(2));
        
        mPoints = new Matrix(dPoints2);
        System.out.println(mPoints.leastSquares(1));

        // the least-squares calculations in the try/catch blocks should fail
        // just testing that we must start with a matrix of points
        try
        {
            mPoints = new Matrix(dArray1);
            System.out.println(mPoints.leastSquares(1));
        }
        catch (Exception e)
        {
            System.out.println("Couldn't calculate coefficients\n" + e);
        }
        // just testing that we must have enough points to do the calculations
        try
        {
            mPoints = new Matrix(dArray2);
            System.out.println(mPoints.leastSquares(2));
        }
        catch (Exception e)
        {
            System.out.println("Couldn't calculate coefficients\n" + e);
        }
    }
}
