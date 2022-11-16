public abstract class ACFunction implements IFunction{
    public abstract double calculate(double x);

    public double findZero(/*Function->this*/double xl,double xr,
                                             double precision)
    {
        double guess = 0.0;
        boolean keepGoing = true;
        while(keepGoing)
        {
            guess = (xl+xr)/2.0;
            double y = this.calculate(guess);
            if(y==0.0)
            {
                keepGoing=false;
            }
            else if(y>0.0)
            {
                xl=guess;
            }
            else {
                xr=guess;
            }
            if(Math.abs(xr-xl)<=precision) keepGoing =false;
        }
        return guess;
    }

    @Override
    public void printTable(double dStart, double dEnd, double dStep) throws IllegalArgumentException {
        if(dStart>dEnd) throw new IllegalArgumentException("Start must < End");
        if(dStep<=0) throw new IllegalArgumentException("Step must >0");
        System.out.println("x\tf(x)");
        System.out.println("===\t======");
        for(double x = dStart;x<=dEnd;x+=dStep)
            System.out.println(x+"\t"+this.calculate(x));
    }
}
