public interface IFunction {
    public double calculate(double x);
    public void printTable(double dStart, double dEnd, double dStep)
        throws IllegalArgumentException;
    public double findZero(/*Function->this*/double xPos,double xNeg,
                                             double precision);
}
