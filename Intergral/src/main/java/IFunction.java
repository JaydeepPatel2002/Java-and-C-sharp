public interface IFunction {
    public double calculate(double x);
    public void printTable(double dStart, double dEnd, double dStep)
        throws IllegalArgumentException;
    public double findZero(/*Function->this*/double xPos,double xNeg,
                                             double precision);
    public double leftRectRule(double x_left, double x_right,double precision, int max_loop);
    public double leftRectRuleEfficient(double x_left, double x_right,
                                       double precision, int max_loop);
    public double TrapezoidRule(double x_left, double x_right,double precision, int max_loop);
}
