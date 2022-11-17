package assign2;

import java.util.ArrayList;

public class AssignPractice 
{
	static int small = 0;
	static int med = 0;
	static int large = 0;
	
	public static void update(PopCenter p)
	{
		
		if (p.getsSizeGroup().equalsIgnoreCase("Small"))
		{
//			int pop = p.getnLatestPop();
//			p.setnLatestPop( (pop*100) /100);
			small++;
			System.out.println("-->" + small + " "+ p.getsSizeGroup());
		
			
		}
		else if(p.getsSizeGroup().equalsIgnoreCase("Medium"))
		{
			med++;			System.out.println("-->" + med+ " "+ p.getsSizeGroup());

			
		}
		else if(p.getsSizeGroup().equalsIgnoreCase("Large urban"))
		{
			large++;			System.out.println("-->" + large+ " "+ p.getsSizeGroup());

		}
		
	}
	
	public static void q9() 
	{
		ArrayList<PopCenter> oontario = PopUtilities.onList;
		
		FunctionalMethods.foreach(oontario, p->update(p));
		FunctionalMethods.foreach(oontario, p->System.out.println(p.toString() + "->"+ p.getsSizeGroup()) );

		
	}
	
	
	
	
	public static void main(String[] args) 
	{
		PopUtilities.LoadAll();
	
		q9();
		

	}

}
