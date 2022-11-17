package assign2;

import java.io.Serializable;

public class Customer implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  String firstName ;
	private  String lastName;
	private  String Province;
	private  String City;
	private  String postalCode;
	
	
	
	
	public Customer(String firstName, String lastName, String province, String city, String postalCode)
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.Province = province;
		this.City = city;
		this.postalCode = postalCode;
		
	}


	public  String getFirstName() 
	{
		return firstName;
	}


	public  String getLastName() 
	{
		return lastName;
	}


	public  String getProvince() 
	{
		return Province;
	}


	public  String getCity() 
	{
		return City;
	}


	public  String getPostalCode()
	{
		return postalCode;
	}


	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", lastName=" + lastName + ", Province=" + Province + ", City="
				+ City + ", postalCode=" + postalCode + "]";
	}
	
	

}
