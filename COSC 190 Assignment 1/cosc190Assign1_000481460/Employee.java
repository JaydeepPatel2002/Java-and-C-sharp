package cosc190Assign1_000481460;

public class Employee {
	
	private String firstName;
	private String lastName;
	private String position;
	private double income;
	
	

	

	public Employee(String firstName, String lastName, String position, double income) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
		this.income = income;
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", position=" + position + ", income="
				+ income + "]";
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPosition() {
		return position;
	}

	public double getIncome() {
		return income;
	}

	

	public static void main(String[] args) 
	{
		

	}

}
