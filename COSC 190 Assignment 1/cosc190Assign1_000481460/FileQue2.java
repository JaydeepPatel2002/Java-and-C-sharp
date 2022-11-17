/*We will reverse all the words from the file ====================================*/

package cosc190Assign1_000481460;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileQue2 
{
	
	public static void FileReverse(String fileName) throws Assign2Exception
	{
		String string = "";
		
		String answer = "";
		
		File myFile = new File(fileName);
		try 
		{
			Scanner scanner = new Scanner(myFile);
			while (scanner.hasNext()) 
			{
				 string += (String) scanner.next() + " ";
				 
			}
			scanner.close();
		} 
		catch (FileNotFoundException e) 
		{
			
			throw new Assign2Exception("");
		}
		String[] rStrings = string.split(" ");
		String[] anStrings = new String[rStrings.length];
		for (int i = 0; i < anStrings.length; i++)
		{
				anStrings[i] = rStrings[anStrings.length-i-1];
		}
		for (int i = 0; i < anStrings.length; i++)
		{
			answer += anStrings[i] + " ";
		}
		
		
		try 
		{
			FileWriter writer = new FileWriter(myFile);
			writer.write(answer);
			writer.close();
		} 
		catch (IOException e) 
		{
			throw new Assign2Exception("");
		}
		
			
	}
	

	public static void main(String[] args) throws Assign2Exception 
	{
		 
		FileReverse("C:\\Users\\jayde\\OneDrive\\Desktop\\jaya.txt");
		
	}

}
