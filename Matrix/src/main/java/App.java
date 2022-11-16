public class App {
    public static double[][] dArray1 = { { 12.0, 3.0, 52.0 }, { -10.0, 45.0, 0.98 } };
    public static double[][] dArray2 = { { 1.0, 3.0 }, { -1.0, 2.0 } };
    public static double[][] dArray3 = { { -2.0, 2.0 }, { 7.0, 8.0 } };

    public static void main(String[] args) {
//        System.out.println("Hello World");
//
//        TestCreateMatrix();
//        TestAdd();
//
//        TestSubtract();
//        TestScalarMultiply();
//
//        TestMatrixMultiply();
        testLeastSquares();
    }

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

    public static void TestSubtract()
    {
        IMatrix m1 = new Matrix(dArray1);
        IMatrix m2 = new Matrix(dArray2);
        IMatrix m3 = new Matrix(dArray3);

        System.out.println("\n\nTesting matrix subtraction");
        IMatrix mSum = m2.Subtract(m3);
        System.out.println(mSum);

        try
        {
            mSum = m1.Subtract(m2);
            System.out.println(mSum);
        }
        catch (Exception e)
        {
            System.out.println("Couldn't subtract\n" + e);
        }
    }

    public static void TestMatrixMultiply()
    {
        IMatrix m1 = new Matrix(dArray1);
        IMatrix m2 = new Matrix(dArray2);
        IMatrix m3 = new Matrix(dArray3);

        System.out.println("\n\nTesting matrix multiplication");
        IMatrix mProduct = m2.Multiply(m3);
        System.out.println(mProduct);

        mProduct = m2.Multiply(m1);
        System.out.println(mProduct);

        try
        {
            mProduct = m1.Multiply(m2);
            System.out.println(mProduct);
        }
        catch (Exception e)
        {
            System.out.println("Coudn't multiply\n" + e);
        }
    }
    public static void TestScalarMultiply()
    {
        IMatrix m1 = new Matrix(dArray1);
        IMatrix m2 = new Matrix(dArray2);

        System.out.println("\n\nTesting scalar multiplication");
        IMatrix mResult = m2.ScalarMultiply(10.0);
        System.out.println(mResult);

        mResult = m1.ScalarMultiply(0.5);
        System.out.println(mResult);
    }

    public static void TestCreateMatrix()
    {
        System.out.println("Testing creating matrices");

        Matrix m = new Matrix(dArray1);
        dArray1[0][0] = 0.0; // shouldn't affect m
        // since our constructor makes a deep copy of the array

        System.out.println("Creating matrix from array:");
        System.out.println(m.toString());

        //System.out.println("Creating matrix from another matrix (copy constructor):");
        //IMatrix m2 = new Matrix((Matrix)m);
        //System.out.println(m2);

        dArray1[0][0] = 12.0; // set it back so it doesn't affect other tests

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

    public static void TestAdd()
    {
        IMatrix m1 = new Matrix(dArray1);
        IMatrix m2 = new Matrix(dArray2);
        IMatrix m3 = new Matrix(dArray3);

        System.out.println("\n\nTesting matrix addition");
        IMatrix mSum = m2.Add(m3);
        System.out.println(mSum);

        try
        {
            mSum = m1.Add(m2);
            System.out.println(mSum);
        }
        catch (Exception e)
        {
            System.out.println("Couldn't add\n" + e);
        }
    }

}
