package cosc190Assign1_000481460;

/*A sample file exists liveexample.pearsoncmg.com/data/Salary.txt that on each line indicates 
  a Faulty member’s first name, last name, position (i.e., associate professor), and annual salary.
    Write a Java program Payroll that reads this file in and determines the high, low, and average salary for 
    each of the following positions assistant professor, associate professor, full professor, and faculty.
     In addition, provide counts that indicate the number of persons in each of those positions.*/


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class FileQue4 
{
	
	
	public static void Payroll()
	{
		int fullProfessorCount = 0;
		int AssistantCount = 0;
		int AssociateCount = 0;
		int facultyCount = 0;
		
		int fullProfessorAverage = 0;
		int AssistantAverage = 0;
		int AssociateAverage = 0;
		int facultyAverage = 0;
		
		double maxAssociate = Integer.MIN_VALUE;
		double maxFullProfessor=Integer.MIN_VALUE;
		double maxAssistant=Integer.MIN_VALUE;
		double maxfaculty= Integer.MIN_VALUE;
		
		double minAssociate= Integer.MAX_VALUE;
		double minFullProfessor= Integer.MAX_VALUE;
		double minAssistant= Integer.MAX_VALUE;
		double minfaculty= Integer.MAX_VALUE;
		
		String string = "";
		ArrayList<Employee> arEmployees = new ArrayList<>();
		
		try {
			URL jay = new URL("http://liveexample.pearsoncmg.com/data/Salary.txt");
			try {
				Scanner jScanner = new Scanner(jay.openStream());
				while (jScanner.hasNextLine())
				{
					string = jScanner.nextLine();
					String[]tempStrings = string.split(" ");
					
						Employee tempEmployee = new Employee(tempStrings[0], tempStrings[1], tempStrings[2], Double.parseDouble(tempStrings[3]));
						
						arEmployees.add(tempEmployee);
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		

		
		
		for (int i = 0; i < arEmployees.size(); i++)
		{
			if (arEmployees.get(i).getPosition().compareTo("associate")==0)
			{
				AssociateCount = AssociateCount + 1;
				AssociateAverage += arEmployees.get(i).getIncome();
				if (arEmployees.get(i).getIncome()>maxAssociate)
				{
					maxAssociate = arEmployees.get(i).getIncome();
				}
				
				if(arEmployees.get(i).getIncome()<minAssociate)
				{
					minAssociate = arEmployees.get(i).getIncome();
				}
				

				
			}
			else if(arEmployees.get(i).getPosition().compareTo("full")==0) 
			{
				fullProfessorCount++;
				fullProfessorAverage += arEmployees.get(i).getIncome();
				if (arEmployees.get(i).getIncome()>maxFullProfessor)
				{
					maxFullProfessor = arEmployees.get(i).getIncome();
				}
				
				if(arEmployees.get(i).getIncome()<minFullProfessor)
				{
					minFullProfessor = arEmployees.get(i).getIncome();
				}
				
			}
			
			else if(arEmployees.get(i).getPosition().compareTo("assistant")==0)
			{
				AssistantCount++;
				AssistantAverage += arEmployees.get(i).getIncome();
				
				if (arEmployees.get(i).getIncome()>maxAssistant)
				{
					maxAssistant = arEmployees.get(i).getIncome();
				}
				
				if(arEmployees.get(i).getIncome()<minAssistant)
				{
					minAssistant = arEmployees.get(i).getIncome();
				}
				
				
				
			}
			else if(arEmployees.get(i).getPosition().compareTo("faculty")==0)
			{
				facultyCount++;
				facultyAverage += arEmployees.get(i).getIncome();
				if (arEmployees.get(i).getIncome()>maxfaculty)
				{
					maxfaculty = arEmployees.get(i).getIncome();
				}
				
				if(arEmployees.get(i).getIncome()<minfaculty)
				{
					minfaculty = arEmployees.get(i).getIncome();
				}
				
			}
			
		}
		
		System.out.println();
		
		System.out.println(" total assistant  :" + AssistantCount);
		System.out.println("total full professor :" + fullProfessorCount);
		System.out.println("total associates : " + AssociateCount);
		System.out.println("total faculty  :" + facultyCount);
		System.out.println();

		System.out.println("max income of assistant :" + maxAssistant );
		System.out.println("min income of assistant :" + minAssistant );
		System.out.println("average income of assistant :" + (AssistantAverage/AssistantCount));
		System.out.println();

		System.out.println("max income of associate :" + maxAssociate );
		System.out.println("min income of associate :" + minAssociate );
		System.out.println("average income of associate :" + (AssociateAverage/AssociateCount));
		System.out.println();
		
		System.out.println("max income of full professor :" + maxFullProfessor );
		System.out.println("min income of  full professor :" + minFullProfessor );
		System.out.println("average income of  full professor :" + (fullProfessorAverage/fullProfessorCount));
		System.out.println();
		
		
		System.out.println("average income of  faculty :" + (facultyAverage));
		System.out.println();
	
	}

	public static void main(String[] args) 
	{
		Payroll();

	}

}
