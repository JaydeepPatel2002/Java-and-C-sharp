package assign2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PopUtilities 
{
	
	public static ArrayList<PopCenter> loadPopCenters(String sFileName) 
	{
		
		ArrayList<PopCenter> popCenters = new ArrayList<>();

		
		BufferedReader jydeep;
		
		try
		{
			jydeep = new BufferedReader(new FileReader(sFileName));
			jydeep.readLine();
			String lineString;
			while((lineString = jydeep.readLine())!=null)
			{
				String[]tempStrings = lineString.split(",");
				
				PopCenter temPopCenter = new PopCenter(tempStrings[1], tempStrings[2], Integer.parseInt(tempStrings[3]), Integer.parseInt(tempStrings[4]));
				
				popCenters.add(temPopCenter);
			}
			
			jydeep.close();

		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();		
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
			return popCenters;
		
	}
	
	public static  ArrayList<PopCenter> bcList ;
	public static  ArrayList<PopCenter> abList ;
	public static  ArrayList<PopCenter> skList ;
	public static  ArrayList<PopCenter> mnList ;
	public static  ArrayList<PopCenter> onList ;
	public static  ArrayList<PopCenter> qeList ;
	public static  ArrayList<PopCenter> nsList ;
	public static  ArrayList<PopCenter> peList ;
	public static  ArrayList<PopCenter> nbList ;
	public static  ArrayList<PopCenter> nfList ;

	
	
	public static void LoadAll()
	{
		 bcList  = loadPopCenters("C:\\Users\\jayde\\OneDrive\\Desktop\\data\\BC.csv");
		 abList  = loadPopCenters("C:\\Users\\jayde\\OneDrive\\Desktop\\data\\AB.csv");
		 skList  = loadPopCenters("C:\\Users\\jayde\\OneDrive\\Desktop\\data\\SK.csv");
		 mnList  = loadPopCenters("C:\\Users\\jayde\\OneDrive\\Desktop\\data\\Man.csv");
		 onList  = loadPopCenters("C:\\Users\\jayde\\OneDrive\\Desktop\\data\\Ont.csv");
		 qeList  = loadPopCenters("C:\\Users\\jayde\\OneDrive\\Desktop\\data\\QE.csv");
		 nsList  = loadPopCenters("C:\\Users\\jayde\\OneDrive\\Desktop\\data\\NS.csv");
		 peList  = loadPopCenters("C:\\Users\\jayde\\OneDrive\\Desktop\\data\\PEI.csv");
		 nbList  = loadPopCenters("C:\\Users\\jayde\\OneDrive\\Desktop\\data\\NB.csv");
		 nfList  = loadPopCenters("C:\\Users\\jayde\\OneDrive\\Desktop\\data\\NF.csv");

	}
		
	
	
	
	public static void main(String[] args) 
	{
		LoadAll();
		for (int i = 0; i < skList.size(); i++) 
		{
			System.out.println(skList.get(i).toString());;
		}
		System.out.println(skList.size());
	}

}
