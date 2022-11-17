/*Question 6


Write a Java Program, Collect, that given a command line argument corresponding to a text file name will go through a make a 
list of all the unique words and the number of times they occur in file. 
 The program should print out all the unique words first ordered by frequency of occurrence (descending) and then ordered alphabetically.  
 
As before if the file cannot be opened, have the program throw an Assign2 Exception.

*/
package cosc190Assign1_000481460;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



public class FileQue3 
{
	
	
	public static void bubble(ArrayList<String>anStrings)
	{
		int check = anStrings.size();
		while (check>1)
		{
			for (int i = 0; i < anStrings.size()-1; i++)
			{
				if (Integer.parseInt((anStrings.get(i).substring((anStrings.get(i).indexOf(" "))+1))) < Integer.parseInt((anStrings.get(i+1).substring((anStrings.get(i+1).indexOf(" "))+1))))
				{
					 String temp = anStrings.get(i);
					 anStrings.set(i, anStrings.get(i + 1)) ;
					 anStrings.set(i+1, temp) ;
					
				}
			}
			check--;
			
		}
		int check2 = anStrings.size();
		while (check2>1)
		{
			for (int i = 0; i < anStrings.size()-1; i++)
			{
				if (Integer.parseInt((anStrings.get(i).substring((anStrings.get(i).indexOf(" "))+1))) == Integer.parseInt((anStrings.get(i+1).substring((anStrings.get(i+1).indexOf(" "))+1))))
				{
					if(anStrings.get(i).charAt(0)>anStrings.get(i+1).charAt(0))
					{
						String temp = anStrings.get(i);
						 anStrings.set(i, anStrings.get(i + 1)) ;
						 anStrings.set(i+1, temp) ;
					}
				}
			}check2--;
		}
		
		
	}
	
	
	public static ArrayList<String> duplicate(String[]array)
	{
		ArrayList<String> anStrings = new ArrayList<>();
		int count;
		for (int i = 0; i < array.length; i++) 
		{
			count = 1;
			for (int j = i+1; j < array.length; j++)
			{
				if(array[i].equals(array[j]))
				{
					count++;
					array[j] = "0";
				}
			}
			if (array[i]!="0")
			{
				anStrings.add(array[i] + " " + count);
			}
		}
		
		return anStrings;

	}
	
	
	public static void collect(String filename) throws Assign2Exception
	{
		String string = "";
		
		
		
		File myFile = new File(filename);
		try 
		{
			Scanner scanner = new Scanner(myFile);
			while (scanner.hasNext()) 
			{
				 string += (String) scanner.next() + " ";

			}
			scanner.close();
			string =  string.replaceAll("[\\W]+", " ");
			String[] arrSval = string.split(" ");
			 ArrayList<String>jkArrayList = duplicate(arrSval);
			 
			 bubble(jkArrayList);
			 
			 for (int i = 0; i < jkArrayList.size() ; i++) {
				System.out.println(jkArrayList.get(i));
			}
		} 
		catch (FileNotFoundException e) 
		{
			
			throw new Assign2Exception("");
		}
		 
		
		
		
	}

	public static void main(String[] args) throws Assign2Exception 
	{
		collect("C:\\Users\\jayde\\OneDrive\\Desktop\\collectTest.txt");
	}

}
