public class Func1 extends ACFunction{
    @Override
    public double calculate(double x) {
        return 1.0/3.0*(x*x)-1.0/300.0*Math.pow(x,4);
    }
}
