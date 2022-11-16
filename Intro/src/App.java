
public class App {

	public static int iFact(int n)
	{
		int rs=1;
		for(int i=2;i<=n;i++)
		{
			rs*=i;
		}
		return rs;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(iFact(100));
	
		
	}

}
