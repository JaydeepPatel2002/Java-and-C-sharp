/*
 *  Date submitted: November 2021
 *  Assignment number: N/A
 *  Course name:  MATH 282
 *  Instructor:  Michael Grzesina
 */

package matrix;

/**
 * Concrete class for implementing matrices using a 2D array of values.
 *
 * @author MATH 282
 * @version November 2021
 */

public class Matrix extends ACMatrix
{
    /**
     * 2D array of entries in the matrix
     */
    private double[][] dArray;
    
    
    /**
     * Constructor for the Matrix class that creates a matrix with the entries
     * given by the 2D array of doubles passed in.
     * 
     * @param dArray    2D array of doubles passed in for matrix values
     */
    public Matrix(double[][] dArray)
    {
        this.iRows = dArray.length;
        this.iCols = dArray[0].length;
        //this.dArray = dArray; // shallow copy
        
        // deep copy
        this.dArray = new double[this.iRows][this.iCols]; // all 0.0 values
        for (int i = 0; i < this.iRows; i++)
        {
            for (int j = 0; j < this.iCols; j++)
            {
                this.dArray[i][j] = dArray[i][j];
            }
        }
    }


    /**
     * Constructor for the Matrix class that creates a matrix full of 0's
     * with the specified number of rows and columns
     * 
     * @param iRows     Number of rows for the matrix to be created
     * @param iCols     Number of columns for the matrix to be created
     */
    public Matrix(int iRows, int iCols)
    {
        this.iRows = iRows;
        this.iCols = iCols;
        this.dArray = new double[iRows][iCols];
    }
    
    
    /**
     * Copy constructor for the Matrix class - create an identical
     * matrix from the matrix passed in.
     * 
     * @param mOriginal Original matrix to be copied
     */
    public Matrix(ACMatrix mOriginal)
    {
        this(((Matrix)mOriginal).dArray);
    }
    
    
    /* (non-Javadoc)
     * @see matrix.ACMatrix#getElement(int, int)
     */
    /**
     * Gets the element from the matrix in the specified row and column.
     * 
     * @param iRow      The row to get the element from
     * @param iCol      The column to get the element from
     * @return          The element in the matrix in the specified row and column
     */
    @Override
    public double getElement( int iRow, int iCol )
    {
        // return element at iRow and iCol of matrix
        // remembering that iRow and iCol start at 1 for matrices
        // but dArray starts at 0
        return this.dArray[iRow - 1][iCol - 1];
    }

    
    /* (non-Javadoc)
     * @see matrix.ACMatrix#setElement(int, int, double)
     */
    /**
     * Sets the element in the matrix in the specified row and column
     * to the specified value.
     * 
     * @param iRow      The row to set the element in
     * @param iCol      The column to set the element in
     * @param dValue    The value to set the element to
     */
    @Override
    public void setElement( int iRow, int iCol, double dValue )
    {
        this.dArray[iRow - 1][iCol - 1] = dValue;
    }

    
    /* (non-Javadoc)
     * @see matrix.ACMatrix#createMatrix(int, int)
     */
    /**
     * Creates a new matrix of the specified size with all entries set to 0.0
     * 
     * @param iRows     The number of rows in the new matrix
     * @param iCols     The number of columns in the new matrix
     * @return          A new matrix of the specified size with all entries set to 0.0
     */
    @Override
    protected ACMatrix createMatrix( int iRows, int iCols )
    {
        return new Matrix(iRows, iCols);
    }
    
    
    /* (non-Javadoc)
     * @see matrix.ACMatrix#copyMatrix()
     */
    /**
     * Creates a new matrix that is exactly the same as the invoking matrix
     * 
     * @return          A new matrix with the same size and same entries as the invoking matrix
     */
    @Override
    protected ACMatrix copyMatrix()
    {
        return new Matrix(this);
    }
}
