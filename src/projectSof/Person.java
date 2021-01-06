package projectSof;

import javax.swing.JOptionPane;

public abstract class  Person {

	protected int id;//person id
	private String firstName;//person first name
	private String lastName;//person last name
	private String gender;//person gender
		
	public Person() {}
	
	// copy constructor 1
	public Person(int id,String firstName,String lastName,String gender) {
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.gender=gender;
		
	}
	
	// copy constructor 2
	public Person(String firstName,String lastName,String gender)
	{
		
		setFirstName(firstName);
		setLastName(lastName);
		setGender(gender);
		
	}
	////////////
	
	/////id get
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id=id;
	}
	
	
	////////////////
	
	//first name get&&set
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	//////////////
	
	//last name get&&set
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	//////////
	
	////gender get&&set
	public String getGender()
	{
		return this.gender;
	}
	
	public void setGender(String gender){
			this.gender=gender;
	
	}
	// to string
	public String toString()
	{
		return "id: "+this.id +" first name: "+this.firstName+" last name: "+this.lastName;
	}
	/////////////
	

}