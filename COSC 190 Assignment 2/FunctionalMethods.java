package assign2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Predicate;






public class FunctionalMethods 
{
	
	public static  void foreach(ArrayList<PopCenter> list, Consumer<PopCenter> c)
	{
		for(PopCenter o:list)
		{
			c.accept(o);
		}
	}
	
	/*================================================================================================================================================================================*/
	
	public static void q1()
	{
		Collections.sort(PopUtilities.abList,(pop1, pop2)-> (pop1.getsName().charAt(0) - pop2.getsName().charAt(0)));
		
		foreach(PopUtilities.abList, e-> System.out.println(e.toString()));
	}
	
	/*================================================================================================================================================================================*/

	public static void q2()
	{
		ArrayList<PopCenter> list = PopUtilities.skList;
		
		Predicate<PopCenter> p = (PopCenter P)-> {return P.getnLatestPop()>5000;};
		
		foreach(list, e->{if(p.test(e)) System.out.println(e.toString());});
		
	}
	
	/*================================================================================================================================================================================*/

	
	public static int q3()
	
	{
		var wrapper = new Object() {int counter = 0;};
		ArrayList<PopCenter> list = PopUtilities.peList;
		
		
		foreach(list, p-> { 
			
			 wrapper.counter += p.getnEarlierPop();
			 });
		
		return wrapper.counter;

	}
	
	/*================================================================================================================================================================================*/

	
	public static int q4()
	{
		ArrayList<PopCenter> list = PopUtilities.skList;
		var wrapper = new Object() {int oldPop = 0; int newPop = 0; };
		foreach(list, p-> { 
			
			 wrapper.oldPop += p.getnEarlierPop();
			 wrapper.newPop += p.getnLatestPop();
			 });

		return (wrapper.newPop - wrapper.oldPop);
	}
	
	/*================================================================================================================================================================================*/

	
	public static PopCenter q5()
	{
		ArrayList<PopCenter> list = PopUtilities.abList;
		var wrapper = new Object() {int max = Integer.MIN_VALUE; PopCenter temPopCenter;};
		FunctionalMethods.foreach(list, p-> { 
			
			int popChange = (int)Math.sqrt(Math.pow((p.getnLatestPop() - p.getnEarlierPop()),2));
			
			if(popChange > wrapper.max)
			{
				wrapper.max = popChange;
				
				wrapper.temPopCenter = p;
			}
			
			 
			 });

		System.out.println("maximum change is " + wrapper.max + " from " + wrapper.temPopCenter.getsName());
		
		return wrapper.temPopCenter;
	}

	/*================================================================================================================================================================================*/
	
	
	public static PopCenter q6Helper(ArrayList<PopCenter> list)
	{
		var wrapper = new Object() {int max = Integer.MIN_VALUE; PopCenter temPopCenter;};
		FunctionalMethods.foreach(list, p-> { 
			
			int popChange = (int)Math.sqrt(Math.pow((p.getnLatestPop() - p.getnEarlierPop()),2));
			
			if(popChange > wrapper.max)
			{
				wrapper.max = popChange;
				
				wrapper.temPopCenter = p;
			}
			
			 
			 });

		return wrapper.temPopCenter;
	}

	
	@SuppressWarnings("unchecked")
	
	//==========================================================================//
	
	public static PopCenter[] q6()
	{
		ArrayList<PopCenter> highestList = new ArrayList<>();
		
		 ArrayList<PopCenter>[] al = new ArrayList[10];		
		 al[0] =PopUtilities.abList;
		 al[1] =PopUtilities.bcList;
		 al[2] =PopUtilities.mnList;
		 al[3] =PopUtilities.nbList;
		 al[4] =PopUtilities.nfList;
		 al[5] =PopUtilities.nsList;
		 al[6] =PopUtilities.onList;
		 al[7] =PopUtilities.peList;
		 al[8] =PopUtilities.qeList;
		 al[9] =PopUtilities.skList;
		 
		PopCenter[] arr = new PopCenter[5];

   // i know there is a method arr.tolist but sir i cannot use that method here because it eas throwing error
		
		highestList.add(q6Helper(al[0])); 
		 highestList.add(q6Helper(al[1])); 
		 highestList.add(q6Helper(al[2])); 
		 highestList.add(q6Helper(al[3])); 
		 highestList.add(q6Helper(al[4])); 
		 highestList.add(q6Helper(al[5])); 
		 highestList.add(q6Helper(al[6])); 
		 highestList.add(q6Helper(al[7])); 
		 highestList.add(q6Helper(al[8])); 
		 highestList.add(q6Helper(al[9]));
		 
		 Collections.sort(highestList,(p1,p2)->(int)( Math.sqrt(Math.pow((p2.getnLatestPop() - p2.getnEarlierPop()),2)) - Math.sqrt(Math.pow((p1.getnLatestPop() - p1.getnEarlierPop()),2))  ));
		 arr[0]	= highestList.get(0);
		 arr[1]	= highestList.get(1);
		 arr[2]	= highestList.get(2);
		 arr[3]	= highestList.get(3);
		 arr[4]	= highestList.get(4);
		 
		return (PopCenter[]) arr;
	}
	
	
	/*================================================================================================================================================================================*/

	public static ArrayList<PopCenter> q7()
	{
		
		ArrayList<PopCenter> answerList =  new ArrayList<>();

		ArrayList<PopCenter> list1 = PopUtilities.abList;
		ArrayList<PopCenter> list2 = PopUtilities.skList;
		
		Predicate<PopCenter> p = (PopCenter temp)-> {return temp.getnLatestPop()>10000 && temp.getnLatestPop()<40000;};
		
		
		FunctionalMethods.foreach(list1, e->{if(p.test(e)) answerList.add(e);});
		FunctionalMethods.foreach(list2, e->{if(p.test(e)) answerList.add(e);});

		
		
		return answerList;
	}
	
	/*================================================================================================================================================================================*/

	public static ArrayList<PopCenter> q8()
	{
		ArrayList<PopCenter> answerList =  new ArrayList<>();

		ArrayList<PopCenter> list1 = PopUtilities.onList;
		ArrayList<PopCenter> list2 = PopUtilities.qeList;
		

		
		Predicate<PopCenter> p = (PopCenter temp)-> {return temp.getsSizeGroup().equalsIgnoreCase("Small");};

		FunctionalMethods.foreach(list1, e->{if(p.test(e)) answerList.add(e);});
		FunctionalMethods.foreach(list2, e->{if(p.test(e)) answerList.add(e);});

		return answerList;
	}

	   /*================================================================================================================================================================================*/

		public static ArrayList<PopCenter> oontario; 

	
		public static void update(PopCenter p, int Index)
		{
			
			if (p.getsSizeGroup().equalsIgnoreCase("Small"))
			{
				String name = p.getsName();
				String sizeGroup = p.getsSizeGroup();
				int popOld = p.getnEarlierPop();
				int popLatest = p.getnLatestPop() + ((p.getnLatestPop()*10)/100);
				
				PopCenter Temp = new PopCenter(name, sizeGroup, popLatest, popOld);
				oontario.set(Index,Temp);
	
			
				
			}
			else if(p.getsSizeGroup().equalsIgnoreCase("Medium"))
			{
				String name = p.getsName();
				String sizeGroup = p.getsSizeGroup();
				int popOld = p.getnEarlierPop();
				int popLatest = p.getnLatestPop() + ((p.getnLatestPop()*5)/100);
				
				PopCenter Temp = new PopCenter(name, sizeGroup, popLatest, popOld);
				oontario.set(Index,Temp);

	
				
			}
			else if(p.getsSizeGroup().equalsIgnoreCase("Large urban"))
			{
				String name = p.getsName();
				String sizeGroup = p.getsSizeGroup();
				int popOld = p.getnEarlierPop();
				int popLatest = p.getnLatestPop() + ((p.getnLatestPop()*2)/100);
				
				PopCenter Temp = new PopCenter(name, sizeGroup, popLatest, popOld);
				oontario.set(Index,Temp);

			}
			
		}
		
		public static void q9() 
		{
			
			oontario = PopUtilities.onList;

			
			FunctionalMethods.foreach(oontario, p->update(p,oontario.indexOf(p)));
			System.out.println("============updated data is================");
			FunctionalMethods.foreach(oontario, p->System.out.println(p.toString()) );
	
			
		}
	/*================================================================================================================================================================================*/

	public static void main(String[] args) 
	{
		PopUtilities.LoadAll();
		
		q9();	
		
		
	
	}

}
