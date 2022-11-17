package cosc190Assign1_000481460;


public class RecursiveMethods 
{
	
	//===================================================================================================================================
	
	public static double sumA(int n)
	{
		if (n == 0) {
			
			return 0.0;
			
		}
		else {
			return (double)n/(2*(double)n + 1) + sumA(n - 1);
		}
		
	}
	
	//====================================================================================================================================
	
	public static int jay= -1;
	
	
	public static int Bin2Dec(String sBinNumber)
	{
		if(sBinNumber.length()<1)
		{
			return 0;
		}
		
		
		if (sBinNumber.charAt(sBinNumber.length()-1)=='1')
		{
			jay++;
			return (int)Math.pow(2, jay) + Bin2Dec(sBinNumber.substring(0, sBinNumber.length()-1)) ;
			
		}
		else if(sBinNumber.charAt(sBinNumber.length()-1)=='0')
		{
			jay++;
			return 0 + Bin2Dec(sBinNumber.substring(0, sBinNumber.length()-1)) ;
			
		}
		else
		{
			return 0 + Bin2Dec(sBinNumber.substring(0, sBinNumber.length()-1));
		}
		
		
	
		
	}
	
	

	public static void main(String[] args) 
	{
//		for (int i = 0; i < 11; i++) {
//			
//		System.out.println(sumA(i));
//		}
		System.out.println(Bin2Dec( "0 0 1 0   0110")); 
		
	}

}
