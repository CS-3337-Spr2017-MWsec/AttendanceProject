package application.view;



import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import application.Main;
import application.classes.Administrator;
import application.classes.AttendanceRecord;
import application.classes.Course;
import application.classes.Student;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TakeAttendanceViewController {
	
	static StringProperty statusSp = new SimpleStringProperty("");
	
	private static Main main;
	
	@FXML
	public TextField scanTf;
	
	@FXML
	public Label mode;
	
	@FXML 
	public Label firstName;
	
	@FXML
	public Label lastName;
	
	@FXML
	public Label id;
	
	@FXML
	public Label status;
	
	
	
	@FXML
	public void initialize() {
		
	}
	
	@FXML
	public void show() {

		
	}
	
	@FXML void takeAttendanceBt() throws InterruptedException{
		main.takeAttendance(main.currentCourse);
	}
	
	@FXML
	public void takeAttendance(Course currentCourse) {
		StringProperty modeSp = new SimpleStringProperty("");
		StringProperty firstNameSp = new SimpleStringProperty("");
		StringProperty lastNameSp = new SimpleStringProperty("");
		StringProperty idSp = new SimpleStringProperty("");
		StringProperty statusSp = new SimpleStringProperty("");	mode.textProperty().bind(modeSp);
		firstName.textProperty().bind(firstNameSp);
		lastName.textProperty().bind(lastNameSp);
		id.textProperty().bind(idSp);
		status.textProperty().bind(statusSp);
		
		Main.parse(currentCourse);
		int currentStudentId = 1;
		
		
		
	}
	
	/* @FXML
	public void takeAttendance(Course currentCourse) throws InterruptedException {
		StringProperty modeSp = new SimpleStringProperty("");
		StringProperty firstNameSp = new SimpleStringProperty("");
		StringProperty lastNameSp = new SimpleStringProperty("");
		StringProperty idSp = new SimpleStringProperty("");
		StringProperty statusSp = new SimpleStringProperty("");
		
		mode.textProperty().bind(modeSp);
		firstName.textProperty().bind(firstNameSp);
		lastName.textProperty().bind(lastNameSp);
		id.textProperty().bind(idSp);
		status.textProperty().bind(statusSp);
		
		boolean empty = true;
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		dateFormat.format(date);
		AttendanceRecord currentAttendanceRecord = new AttendanceRecord(date);
		
		currentAttendanceRecord.students = currentCourse.students;
		System.out.println(currentCourse.students.size());
		System.out.println(currentAttendanceRecord.students.size());
		int currentStudentId = 1;
		
	
		do {
			
			modeSp.setValue("System in Login Mode");
		
					currentStudentId = parse(currentCourse);
				
			if (currentStudentId == 304999154) {
				break;
			}
			System.out.println("id:" + currentStudentId);
			Student currentStudent = null;
			Timestamp loginTime = new Timestamp(System.currentTimeMillis());
			System.out.print("array size" + currentAttendanceRecord.students.size());
			for(int i = 0; i < currentAttendanceRecord.students.size(); i++) {
				if(currentAttendanceRecord.students.get(i).getId() == currentStudentId) {
					currentStudent = currentAttendanceRecord.students.get(i);
				}
			}
			System.out.println(currentStudent.getFirstName());
			
			firstNameSp.setValue(currentStudent.getFirstName());
			lastNameSp.setValue(currentStudent.getLastName());
			idSp.setValue(Integer.toString
					(currentStudent.getId()));
			statusSp.setValue("Logged In");
			currentStudent.setLoginTime(loginTime);
			System.out.println(currentStudent.getLoginTime());
			
			
			scanTf.clear();
			scanTf.wait();
			System.out.println(scanTf.getText().isEmpty());
			
			

		} while (currentStudentId != 304999154);
		scanTf.clear();
		
		System.out.println(scanTf.getText().isEmpty());

		System.out.println("System in Standby Mode1");
		currentStudentId = 1;
		do {
			System.out.println("standbymode2");
			modeSp.setValue("System in Standby Mode2");
			
					currentStudentId = parse(currentCourse);
				
			
			if (currentStudentId == 304999154) {
				break;
			}
			scanTf.clear();
			scanTf.wait();
			

		} while (currentStudentId != 304999154);
		scanTf.clear();
		scanTf.wait();
		System.out.println("out of standbymode2");
		currentStudentId = 1;
		do {
			System.out.println("log out mode");
	
			
					currentStudentId = parse(currentCourse);
				
			
			if (currentStudentId == 304999154) {
				break;
			}
			
			Student currentStudent = null;
			Timestamp logoutTime = new Timestamp(System.currentTimeMillis());
			for(int i = 0; i < currentAttendanceRecord.students.size(); i++) {
				if(currentAttendanceRecord.students.get(i).getId() == currentStudentId) {
					currentStudent = currentAttendanceRecord.students.get(i);
				}
			}
			
			firstNameSp.setValue(currentStudent.getFirstName());
			lastNameSp.setValue(currentStudent.getLastName());
			idSp.setValue(Integer.toString(currentStudent.getId()));
			statusSp.setValue("You are logged out");
			currentStudent.setLogoutTime(logoutTime);
			scanTf.clear();
			scanTf.wait();
			

		} while (currentStudentId != 304999154);

		currentCourse.attendanceRecords.add(currentAttendanceRecord);
		
	//	main.writeToFile(main.file);

	}
	
	public  int parse(Course currentCourse) {

		Administrator test = new Administrator("Name", "Mr", "Richards", "email", "richards", "ilovejose");
		// example: student card scan
		String student = "";
		student = scanTf.getText();
		do {
			if(scanTf.getText().length() > 95)
			student = scanTf.getText();

			if (student.length() < 95) {
				statusSp.setValue("Scan Failed");
			}
		} while (student.length() < 95);

		String firstName;
		String lastName;
		String temp_id;

		int number = 1;
		String delims = "[%,/,?,=,;,^, ]+";
		String[] tokens = student.split(delims);

		firstName = tokens[2];
		lastName = tokens[3];
		temp_id = tokens[6];
		int id = Integer.parseInt(temp_id.substring(7, temp_id.length()));

		Student currentStudent = new Student();
		for (int i = 0; i < currentCourse.students.size(); i++) {
			if (id == currentCourse.students.get(i).getId()) {
				currentStudent = currentCourse.students.get(i);
			}
		}

		

		return id;

	}
 */
}
