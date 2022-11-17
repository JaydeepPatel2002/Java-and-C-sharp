
/*Question 4

Write a Java program Count that when given command line arguments consisting of a file name and a 
word pattern will try to open that given file and count the number of times the given word exists in the file. 
 The Program should throw an Exception Assign2Exception (which you must write) if the file does not exist. 

*/
package cosc190Assign1_000481460;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileQue1 {
	
	public static int Count(String name,String target) throws Assign2Exception
	{
		int result = 0;
		String string;
		File myFile = new File(name);
		try 
		{
			Scanner scanner = new Scanner(myFile);
			while (scanner.hasNext()) 
			{
				 string = (String) scanner.next();
				 if (string.compareTo(target)==0)
				 {
					result++;	
				 }
			}
			scanner.close();
		} 
		catch (FileNotFoundException  e) 
		{
			throw new Assign2Exception("");
		}
		return result;
		
	}


	public static void main(String[] args) throws Assign2Exception
	{

		
		
		int j = Count("C:\\Users\\jayde\\OneDrive\\Desktop\\joy.txt","sold");
		System.out.println(j);
	}

}
