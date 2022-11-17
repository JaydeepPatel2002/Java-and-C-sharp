package assign2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Convert
{
	
	
	public static void convert()
	{
		ArrayList<Customer> customers = new ArrayList<>();

		try
		{
			BufferedReader jay = new BufferedReader(new FileReader("C:\\Users\\jayde\\OneDrive\\Desktop\\data\\caSample.csv"));
			jay.readLine();
			String lineString;
			while((lineString = jay.readLine()) != null)
			{
				lineString = lineString.replaceAll(" ", "");
				String[]tempStrings = lineString.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				
				//this if else statement was my strategy to deal with extra comma in data but your solution is more better than mine. thanks
				
				if(tempStrings.length == 11)
				{
					Customer tempCustomer = new Customer(tempStrings[0], tempStrings[1], tempStrings[5], tempStrings[4], tempStrings[6]);
					customers.add(tempCustomer);

				}
				else {
					Customer tempCustomer = new Customer(tempStrings[0], tempStrings[1], tempStrings[6], tempStrings[5], tempStrings[7]);
					customers.add(tempCustomer);

				}
				
				
			}
			jay.close();
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();		
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		Collections.sort(customers,(cust1, cust2)-> (cust1.getCity().charAt(1) - cust2.getCity().charAt(1)));
		
		// if you want to print whole sorted list of customers then uncomment this code
		
//		for (Customer customer : customers) {
//			System.out.println(customer.toString());
//		}
		
		
		try(RandomAccessFile out = new RandomAccessFile("Customers.bin", "rw"))
		{
			for(Customer Cust:customers)
			{
				
				out.write(stringtoFixLenByteArray(Cust.getFirstName(),MAX_LEN_FIRST));
				out.write(stringtoFixLenByteArray(Cust.getLastName(),MAX_LEN_LAST));
				out.write(stringtoFixLenByteArray(Cust.getCity(),MAX_LEN_CITY));
				out.write(stringtoFixLenByteArray(Cust.getProvince(),MAX_LEN_PROVINCE));
				out.write(stringtoFixLenByteArray(Cust.getPostalCode(),MAX_LEN_POSTALCODE));
				
			}
		} 
		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		} 
		catch (IOException e2) 
		{
			e2.printStackTrace();
		}
		
		

	}
	
	//====================================================================================================================================//
	
	private static final int MAX_LEN_FIRST = 20;
	private static final int MAX_LEN_LAST = 20;
	private static final int MAX_LEN_CITY = 20;
	private static final int MAX_LEN_PROVINCE = 4;
	private static final int MAX_LEN_POSTALCODE = 8;
	
	
	private static byte[] stringtoFixLenByteArray(String s,int len)
	{
		byte[] rs = new byte[len];
		byte[] src = s.getBytes();
		System.arraycopy(src,0, rs, 0, src.length<len?src.length:len);
		return rs;
	}
	
	
	
	private static byte[] readFile(RandomAccessFile io, int len) throws IOException
	{
		byte[] ba = new byte[len];
		int nRead = io.read(ba);
		if(nRead!=len)
		{
			
		}
		return ba;
	}
	
	
	private static void Read(int Index)  throws IOException
	{
		 
		RandomAccessFile input = new RandomAccessFile("Customers.bin", "r");
		
		
		input.seek(72*(Index - 1));
		String first = new String(readFile(input,MAX_LEN_FIRST),StandardCharsets.UTF_8);
		String last = new String(readFile(input,MAX_LEN_LAST),StandardCharsets.UTF_8);
		String city = new String(readFile(input,MAX_LEN_CITY),StandardCharsets.UTF_8);
		String province = new String(readFile(input,MAX_LEN_PROVINCE),StandardCharsets.UTF_8);
		String postal = new String(readFile(input,MAX_LEN_POSTALCODE),StandardCharsets.UTF_8);
		
		
		System.out.println("Your "+Index+"th record is --> "+"\n"+"First name : "+first.substring(0, first.indexOf('"',2)+1) + "   "+"\n"+"Last name : "+
				
				           last.substring(0, last.indexOf('"',2)+1) + "   "+"\n"+"city : "+
				           city.substring(0, city.indexOf('"',2)+1) + "   "+"\n"+"province : "+
				            province+"   "+"\n"+"postal code : "+postal);
		
		

	}
	
	
	public static void CustAccess()
	{
		System.out.print("enter the number between 1 to 500 to get corresponding customer :");
		Scanner jayScanner = new Scanner(System.in);
		int index = jayScanner.nextInt();
		if(index<1 || index > 500)
		{
			System.out.print("i am executing this program again please ");
			CustAccess();
		}
		else
		{
			try
			{
				Read(index);
			}
			catch (IOException e) 
			{
				
				e.printStackTrace();
				
			}
		}
		
		jayScanner.close();
	}
	
	public static void main(String[] args)
	{
		PopUtilities.LoadAll();

		convert();
		
		CustAccess();
		
		
	}
	
}
	


