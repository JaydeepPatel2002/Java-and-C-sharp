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


    @Override
    public double TrapezoidRule(double x_left, double x_right,double precision, int max_loop)
    {

    }

    @Override
    public double leftRectRuleEfficient(double x_left, double x_right,
                               double precision, int max_loop)
    {
        //Complete the one rect
        long nRects = 1;
        double dTotdalWidth = x_right - x_left;
        double dHeights = this.calculate(x_left);//y1
        double dEstimate = dHeights * dTotdalWidth;//y1*w1

        int nLoops = 0;
        boolean bKeepGoing = true;
        while(bKeepGoing)
        {
            nLoops++;
            double dOldEstimate = dEstimate;
            //Always double the nRects for each time
            nRects *=2;
            double dWidth = dTotdalWidth/nRects;
            //We need to keep the result from last iteration
            //dEstimate = 0;

            for(long i=1;i<nRects;i+=2)
                dHeights+=this.calculate(x_left+i*dWidth);

            dEstimate=dHeights*dWidth;

            double dError = Math.abs(dEstimate-dOldEstimate);
            double dRelError = dError/dEstimate;
            if(dRelError<=precision)
                bKeepGoing = false;
            else if (nLoops>=max_loop)
            {
                bKeepGoing=false;
                System.out.println("Did not converge with loops"+nLoops);
            }

        }
        return dEstimate;
    }


    @Override
    public double leftRectRule(double x_left, double x_right,
                               double precision, int max_loop)
    {
        //Complete the one rect
        long nRects = 1;
        double dTotdalWidth = x_right - x_left;
        double dHeight = this.calculate(x_left);//Left rect rule
        double dEstimate = dHeight * dTotdalWidth;

        int nLoops = 0;
        boolean bKeepGoing = true;
        while(bKeepGoing)
        {
            nLoops++;
            double dOldEstimate = dEstimate;
            //Always double the nRects for each time
            nRects *=2;
            double dWidth = dTotdalWidth/nRects;
            dEstimate = 0;
            for(long i=0;i<nRects;i++)
            {
//                double dLeftofRect = x_left+i*dWidth;
//                double dHeightofRect = this.calculate(dLeftofRect);
//                double dAreaofRect = dWidth*dHeightofRect;
//                dEstimate+=dAreaofRect;
                dEstimate+=this.calculate(x_left+i*dWidth);
            }
            dEstimate*=dWidth;
            double dError = Math.abs(dEstimate-dOldEstimate);
            double dRelError = dError/dEstimate;
            if(dRelError<=precision)
                bKeepGoing = false;
            else if (nLoops>=max_loop)
            {
                bKeepGoing=false;
                System.out.println("Did not converge with loops"+nLoops);
            }

        }
        return dEstimate;
    }
}
