public class Matrix extends AMatrix{
    private double[][] dArray;
    public Matrix(int nRows,int nCols)
    {
        this.setCols(nCols);
        this.setRows(nRows);
        this.dArray = new double[nRows][nCols];
    }

    public Matrix(double[][] dArray)
    {
        this(dArray.length,dArray[0].length);
        for(int r=0;r<this.getRows();r++)
            for(int c=0;c<this.getCols();c++)
                this.dArray[r][c]=dArray[r][c];
    }

    public Matrix(AMatrix mMatrix)
    {
        this(((Matrix)mMatrix).dArray);
    }


    @Override
    protected double getElement(int nRow, int nCol) {
        return this.dArray[nRow-1][nCol-1];
    }

    @Override
    protected void setElement(int nRow, int nCol, double dVal) {
        this.dArray[nRow-1][nCol-1]=dVal;
    }

    @Override
    protected AMatrix createMatrix(int nRows, int nCols) {
        return new Matrix(nRows,nCols);
    }

    @Override
    protected AMatrix copyMatrix() {
        return new Matrix(this);
    }
}
