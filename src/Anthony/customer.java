package Anthony;

public class customer {
	private String firstname;
	
	private String lastname;
	
	private String PhoneNumber;
	
	public customer(String firstname, String lastname, String PhoneNumber ){
		this.firstname = firstname;
		this.lastname = lastname;
		this.PhoneNumber = PhoneNumber;
		
	}
	public String getFirstname()
	{
		
		return firstname;
	}
	public String getlastname()
	{
		
		return lastname;
	}
	public String getPhoneNumber()
	{
		
		return PhoneNumber;
	}
	
	public String toString()
	{
		String st = String.format("%-20%\t%-100%-20", firstname, lastname, PhoneNumber);
		return st;
	}
	
	

}
