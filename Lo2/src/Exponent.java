
public class Exponent {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(myExpFunction(-10,0.0001));
	}
	
	public static double dFact(int n)
	{
		double rs=1;
		for(int i=2;i<=n;i++)
		{
			rs*=i;
		}
		return rs;
	}
	
	public static double myExpFunction(double x,
			double dPrecision)
	{
		double dResult = 1.0+x;
		double delta = x;
		int nCount =1;
		if(x<0)
		{
			return 1.0/myExpFunction(-x,dPrecision);
		}
		do
		{
			nCount++;
			delta = delta * x/nCount;
			dResult += delta;
			
		}while(Math.abs((delta)/dResult)
				>dPrecision);

		return dResult;
	}
}
