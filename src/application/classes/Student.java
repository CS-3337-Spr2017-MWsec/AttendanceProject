package application.classes;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Student {
	
	private String firstName;
	private String lastName;
	private String guardianEmail;
	private int id;
	private String status;
	private Timestamp loginTime;
	private Timestamp logoutTime;
	public ArrayList<AttendanceRecord> attendanceRecords = new ArrayList<>();
	
	public Student(){
		
	};
	public Student(String firstName, String lastName, int id, String guardianEmail) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.guardianEmail = guardianEmail;
		this.id = id;
		
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

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public Timestamp getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Timestamp logoutTime) {
		this.logoutTime = logoutTime;
	}

	public void setStatus() {
		
		if(this.getLoginTime() == null || this.getLogoutTime() == null){
			this.status = "absent";
		}else {
			this.status = "present";
		}
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public Student getStudentById(int id) {
		if(id == this.id) {
			return this;
		}
		return null;
		
	}
	

	
	
	
	
}

