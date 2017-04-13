
public class Student {
	
	private String firstName;
	private String lastName;
	private String guardianEmail;
	private int id;
	private boolean status;
	private boolean loginStatus;
	private boolean logoutStatus;
	
	public Student(String firstName, String lastName, String guardianEmail, int id, boolean status) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.guardianEmail = guardianEmail;
		this.id = id;
		this.status = status;
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

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	

	public String getGuardianEmail() {
		return guardianEmail;
	}

	public void setGuardianEmail(String guardianEmail) {
		this.guardianEmail = guardianEmail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

	public boolean isLogoutStatus() {
		return logoutStatus;
	}

	public void setLogoutStatus(boolean logoutStatus) {
		this.logoutStatus = logoutStatus;
	}
	
	
	
	
	
	
}

