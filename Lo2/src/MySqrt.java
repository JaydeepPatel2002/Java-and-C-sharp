import java.math.BigDecimal;
import java.math.MathContext;

public class MySqrt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(mySqrt(4.0,0.0001));
//		System.out.println(mySqrt(81.0,1E-10));
//		System.out.println(mySqrt(3.0,0.0001));
//		System.out.println(mySqrt(2.0,0.0001));
//		System.out.println(mySqrt(49.0,0.0001));
		System.out.print(bdSqrt(new BigDecimal("1.9E1000"),new BigDecimal("1E-10")));
	}

	public static BigDecimal bdSqrt(BigDecimal bdValue, BigDecimal bdPrecision)
	{
		BigDecimal bdGuess = BigDecimal.ZERO;
		if(bdValue.compareTo(BigDecimal.ZERO)<0)
		{
			throw new ArithmeticException("no nagative number");
		}
		else if(bdValue.compareTo(BigDecimal.ZERO)>0)
		{
			BigDecimal bdLower = BigDecimal.ONE;
			BigDecimal bdUpper = bdValue;
			boolean bKeepGoing = true;
			MathContext mcPrecision = new MathContext(bdPrecision.scale()+
					bdPrecision.precision()+bdValue.precision());
			while(bKeepGoing)
			{
				bdGuess = bdUpper.add(bdLower).divide(new BigDecimal("2"));
				BigDecimal bdTest = bdGuess.multiply(bdGuess);
				if(bdTest.compareTo(bdValue)==0)
				{
					bKeepGoing = false;
				}
				else if(bdTest.compareTo(bdValue)<0)
				{
					bdLower = bdGuess;
				}
				else
				{
					bdUpper = bdGuess;
				}
				BigDecimal bdRange = bdUpper.subtract(bdLower);
				BigDecimal bdError = bdRange.divide(bdGuess,mcPrecision);
				if(bdError.compareTo(bdPrecision)<0)
				{
					bKeepGoing = false;
				}
			}
		}
		return bdGuess;
	}
	
	public static double mySqrt(double dValue, double dPrecision)
	{
		double dResult = 0.0;
		if(dValue<0.0)
		{
			return Double.NaN;
		}
		else
		if(dValue>0.0)
		{
			double dUpper = dValue;
			double dLower = 1.0;
			double dGuess = dValue;
			boolean bKeepGoing = true;
			while(bKeepGoing)
			{
				//System.out.printf("%f %f %f %f \n",dGuess,dLower,dUpper,dUpper-dLower);
				dGuess = (dUpper+dLower)/2.0;
				double dTest = dGuess * dGuess;
				if(dTest==dValue)
				{
					bKeepGoing = false;
				}
				else if(dTest>dValue)
				{
					dUpper = dGuess;
				}
				else
				{
					dLower = dGuess;
				}
				if((dUpper-dLower)<dPrecision)
				{
					bKeepGoing = false;
				}
			}
			dResult = dGuess;
		}
		return dResult;
	}
}
