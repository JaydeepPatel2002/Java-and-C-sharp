/*
 *  Date submitted: November 2021
 *  Assignment number: N/A
 *  Course name:  MATH 282
 *  Instructor:  Michael Grzesina
 */

package matrix;

/**
 * Interface for Matrix class<br>
 * for creating matrices and performing operations on them<br>
 * for Learning Outcomes 3 and 5<br>
 * 
 * @author MATH 282
 * @version November 2021
 */
public interface IMatrix
{
    /**
     * Adds together the invoking matrix and the passed-in matrix and returns
     * the sum matrix. Throws exception if matrices are not of equal size.
     * 
     * @param mRight    Matrix to be added to the invoking matrix
     * @return          Sum of invoking matrix and matrix passed in
     * @throws IllegalArgumentException     Matrices are not the same size and cannot be added
     */
    public IMatrix add(IMatrix mRight)
            throws IllegalArgumentException;

    
    /**
     * Multiplies all entries in the invoking matrix by the scalar value passed in.
     * 
     * @param dScalar   Value to multiply the matrix entries by
     * @return          Matrix with all entries multiplied by the scalar value passed in
     */
    public IMatrix scalarMultiply(double dScalar);

    
    /**
     * Subtracts the passed-in matrix from the invoking matrix and returns
     * the resultant matrix. Throws exception if matrices are not of equal size.
     * 
     * @param mRight    Matrix to be subtracted the invoking matrix
     * @return          Matrix that results from subtracting the passed-in matrix from the invoking matrix
     * @throws IllegalArgumentException     Matrices are not the same size and cannot be subtracted
     */
    public IMatrix subtract(IMatrix mRight)
            throws IllegalArgumentException;

    
    /**
     * Multiplies the invoking matrix by the passed-in matrix and returns
     * the product matrix. Throws exception if matrices cannot be multiplied.
     * 
     * @param mRight    Matrix to be multiplied against the invoking matrix
     * @return          Matrix that results from multiplying the invoking matrix by the passed-in matrix
     * @throws IllegalArgumentException     Matrices cannot be multiplied because they are the wrong sizes
     */
    public IMatrix multiply(IMatrix mRight)
            throws IllegalArgumentException;
    

    /**
     * Solves a system of linear equations given by an n by (n+1) matrix where the first n columns
     * are the coefficients for the n linear equations and the (n+1)st column contains the known vector.
     * Throws an exception if the matrix is not of size n by (n+1) or if the system of equations cannot
     * be solved.
     * 
     * @return          Matrix with solved linear equation (identity matrix plus solution vector)
     * @throws IllegalCallerException       Invoking matrix is not of size n by (n+1)
     * @throws ArithmeticException          System of equations cannot be solved
     */
    public IMatrix gaussJordanElimination()
            throws IllegalCallerException, ArithmeticException;
    

    /**
     * Calculates the least squares polynomial of the given degree that matches the given data points.
     * Throws an exception if the degree given is less than 0, or if the invoking matrix is not a 2D
     * matrix of points, or if the number of points cannot create a least squares polynomial of the
     * given degree. Returns a matrix of size n by (n+1) with the identity matrix followed by the vector
     * of coefficients for the least squares polynomial.
     * 
     * @param m         Degree sought for the least squares polynomial
     * @return          Solved system of linear equations with the coefficients for the least squares polynomial
     * @throws IllegalCallerException       Invoking matrix is not a matrix of points
     * @throws IllegalArgumentException     Matrix of points given does not support the degree sought
     */
    public IMatrix leastSquares(int m)
            throws IllegalCallerException, IllegalArgumentException;
}
