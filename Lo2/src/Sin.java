
public class Sin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(mySineEx(Math.PI*1.1,0.001));
	}

	
	public static double mySine(double dAngle, double dPrecision)
	{
		dAngle = dAngle %(2*Math.PI);
		double dResult = dAngle;
		int nCount =0;
		double dOldResult = 0.0;
		double delta;
		do {
			dOldResult = dResult;
			nCount ++;
			delta = Math.pow(-1.0, nCount)*
					Math.pow(dAngle,2*nCount+1)/
					Exponent.dFact(2*nCount+1);
			dResult += delta;
					
		}while(Math.abs(dResult-dOldResult)>dPrecision);
		return dResult;
	}
	
	public static double mySineEx(double dAngle, double dPrecision)
	{
		dAngle = dAngle %(2*Math.PI);
		double dResult = dAngle;
		double delta = dAngle;
		int nCount =0;
		do {
			nCount ++;
			delta=-1.0*delta * dAngle*dAngle/
					((nCount*2.0)*(nCount*2.0+1));
			dResult +=delta;
		}while(Math.abs(delta/dResult)>dPrecision);
		return dResult;
	}
}
