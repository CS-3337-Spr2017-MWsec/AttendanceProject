package src;

import java.util.ArrayList;

public class Administrator {
	
	private String name;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private ArrayList<Course> courses = new ArrayList<>();
	
	public Administrator(String name, String firstName, String lastName, String email, String username, String password) {
		
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setCourse(String name) {	//Uses course name to create a course object, then adds it to the course array for this class
		
	}


	
}
