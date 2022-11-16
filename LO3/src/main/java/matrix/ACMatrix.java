/*
 *  Date submitted: November 2021
 *  Assignment number: N/A
 *  Course name:  MATH 282
 *  Instructor:  Michael Grzesina
 */

package matrix;

/**
 * Abstract class for Matrix class<br>
 * for creating matrices and performing operations on them<br>
 * for Learning Outcomes 3 and 5<br>
 * 
 * @author MATH 282
 * @version November 2021
 */
public abstract class ACMatrix implements IMatrix
{
    /**
     * Number of rows in the matrix
     */
    protected int iRows;
    
    /**
     * Number of columns in the matrix
     */
    protected int iCols;
    
    
    /**
     * Gets the element from the matrix in the specified row and column.
     * 
     * @param iRow      The row to get the element from
     * @param iCol      The column to get the element from
     * @return          The element in the matrix in the specified row and column
     */
    protected abstract double getElement(int iRow, int iCol);
    
    
    /**
     * Sets the element in the matrix in the specified row and column
     * to the specified value.
     * 
     * @param iRow      The row to set the element in
     * @param iCol      The column to set the element in
     * @param dValue    The value to set the element to
     */
    protected abstract void setElement(int iRow, int iCol, double dValue);
    

    /**
     * Creates a new matrix of the specified size with all entries set to 0.0
     * 
     * @param iRows     The number of rows in the new matrix
     * @param iCols     The number of columns in the new matrix
     * @return          A new matrix of the specified size with all entries set to 0.0
     */
    protected abstract ACMatrix createMatrix(int iRows, int iCols);

    
    /**
     * Creates a new matrix that is exactly the same as the invoking matrix
     * 
     * @return          A new matrix with the same size and same entries as the invoking matrix
     */
    protected abstract ACMatrix copyMatrix();

    
    /* (non-Javadoc)
     * @see matrix.IMatrix#add(matrix.IMatrix)
     */
    /**
     * Adds together the invoking matrix and the passed-in matrix and returns
     * the sum matrix. Throws exception if matrices are not of equal size.
     * 
     * @param mRight    Matrix to be added to the invoking matrix
     * @return          Sum of invoking matrix and matrix passed in
     * @throws IllegalArgumentException     Matrices are not the same size and cannot be added
     */
    @Override
    public IMatrix add( IMatrix mRight ) throws IllegalArgumentException
    {
        ACMatrix mLeftOp = this;
        ACMatrix mRightOp = (ACMatrix)mRight;
        ACMatrix mSum = null;
        
        // if the matrices are not the same size, throw an exception
        if (mLeftOp.iRows != mRightOp.iRows || mLeftOp.iCols != mRightOp.iCols)
        {
            throw new IllegalArgumentException("Matrices must be the same size for addition");
        }
        
        // sum matrix is the same size as the operands
        mSum = createMatrix(mLeftOp.iRows, mLeftOp.iCols);
        
        // for every row in the sum matrix
        for (int r = 1; r <= mSum.iRows; r++)
        {
            // for every column in the current row of the sum matrix
            for (int c = 1; c <= mSum.iCols; c++)
            {
                // add corresponding elements in the operand matrices and put the result
                // in the same position in the sum matrix
                mSum.setElement(r, c, mLeftOp.getElement(r, c)
                        + mRightOp.getElement(r, c));
            }
        }
        
        return mSum;
    }

    
    /* (non-Javadoc)
     * @see matrix.IMatrix#scalarMultiply(double)
     */
    /**
     * Multiplies all entries in the invoking matrix by the scalar value passed in.
     * 
     * @param dScalar   Value to multiply the matrix entries by
     * @return          Matrix with all entries multiplied by the scalar value passed in
     */
    @Override
    public IMatrix scalarMultiply( double dScalar )
    {
        // TODO Auto-generated method stub
        // left for you to implement
        return null;
    }

    
    /* (non-Javadoc)
     * @see matrix.IMatrix#subtract(matrix.IMatrix)
     */
    /**
     * Subtracts the passed-in matrix from the invoking matrix and returns
     * the resultant matrix. Throws exception if matrices are not of equal size.
     * 
     * @param mRight    Matrix to be subtracted the invoking matrix
     * @return          Matrix that results from subtracting the passed-in matrix from the invoking matrix
     * @throws IllegalArgumentException     Matrices are not the same size and cannot be subtracted
     */
    @Override
    public IMatrix subtract( IMatrix mRight ) throws IllegalArgumentException
    {
        // TODO Auto-generated method stub
        // left for you to implement
        return null;
    }

    
    /* (non-Javadoc)
     * @see matrix.IMatrix#multiply(matrix.IMatrix)
     */
    /**
     * Multiplies the invoking matrix by the passed-in matrix and returns
     * the product matrix. Throws exception if matrices cannot be multiplied.
     * 
     * @param mRight    Matrix to be multiplied against the invoking matrix
     * @return          Matrix that results from multiplying the invoking matrix by the passed-in matrix
     * @throws IllegalArgumentException     Matrices cannot be multiplied because they are the wrong sizes
     */
    @Override
    public IMatrix multiply( IMatrix mRight ) throws IllegalArgumentException
    {
        // TODO Auto-generated method stub
        // left for you to implement
        return null;
    }

    
    /* (non-Javadoc)
     * @see matrix.IMatrix#gaussJordanElimination()
     */
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
    @Override
    public IMatrix gaussJordanElimination()
            throws IllegalCallerException, ArithmeticException
    {
        // TODO Auto-generated method stub
        return null;
    }

    
    /* (non-Javadoc)
     * @see matrix.IMatrix#leastSquares(int)
     */
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
    @Override
    public IMatrix leastSquares( int m )
            throws IllegalCallerException, IllegalArgumentException
    {
        // TODO Auto-generated method stub
        return null;
    }

    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    /**
     * Returns the matrix as a string with each row on one line
     * and each column separated by tabs.
     * 
     * @return  String representation of the invoking matrix
     */
    @Override
    public String toString()
    {
        String s = "";
        
        // go through every row
        for (int r = 1; r <= this.iRows; r++)
        {
            s += "[\t";
            // go through every column in this row
            for (int c = 1; c <= this.iCols; c++)
            {
                s += this.getElement(r, c) + "\t";
            }
            s += "]\n";
        }
        
        return s;
    }
}
