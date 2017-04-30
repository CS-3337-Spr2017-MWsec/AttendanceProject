package application.view;


import java.awt.TextField;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import application.Main;
import application.classes.Administrator;
import application.classes.AttendanceRecord;
import application.classes.Course;
import application.classes.Student;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TakeAttendanceViewController {
	
	private static Main main;
	
	@FXML
	private static TextField scan;
	
	@FXML
	private Label mode;
	
	@FXML 
	private Label firstName;
	
	@FXML
	private Label lastName;
	
	@FXML
	private Label id;
	
	@FXML
	private static Label status;
	
	@FXML
	public void initialize() {
		
	}
	
	@FXML
	public void show() {

		
	}
	
	@FXML void takeAttendanceBt(){
		takeAttendance(main.currentCourse);
	}
	
	@FXML
	public void takeAttendance(Course currentCourse) {
		
		
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

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		dateFormat.format(date);
		AttendanceRecord currentAttendanceRecord = new AttendanceRecord(date);
		
		currentAttendanceRecord.students = currentCourse.students;
		
		int currentStudentId = 1;
		
	
		do {
			modeSp.setValue("System in Login Mode");
			 currentStudentId = parse(currentCourse);
			if (currentStudentId == 304999154) {
				break;
			}
			
			Student currentStudent = null;
			Timestamp loginTime = new Timestamp(System.currentTimeMillis());
			for(int i = 0; i < currentAttendanceRecord.students.size(); i++) {
				if(currentAttendanceRecord.students.get(i).getId() == currentStudentId) {
					currentStudent = currentAttendanceRecord.students.get(i);
				}
			}
			
			firstNameSp.setValue(currentStudent.getFirstName());
			lastNameSp.setValue(currentStudent.getLastName());
			idSp.setValue(Integer.toString(currentStudent.getId()));
			statusSp.setValue("You are logged in");
			
			currentStudent.setLoginTime(loginTime);
			

		} while (currentStudentId != 304999154);

		System.out.println("System in Standby Mode");
		currentStudentId = 1;
		do {
			currentStudentId = parse(currentCourse);
			if (currentStudentId == 304999154) {
				break;
			}
			modeSp.setValue("System in Standby Mode");

		} while (currentStudentId != 304999154);
		
		currentStudentId = 1;
		do {
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
			

		} while (currentStudentId != 304999154);

		currentCourse.attendanceRecords.add(currentAttendanceRecord);
		main.writeToFile(main.file);

	}
	
	public static int parse(Course currentCourse) {

		Administrator test = new Administrator("Name", "Mr", "Richards", "email", "richards", "ilovejose");
		// example: student card scan
		String student = "";
		do {
			System.out.println("scan now");
			Scanner scanner = new Scanner(System.in);
			student = scan.getText();

			if (student.length() < 95) {
				status.setText("Scan Failed");
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

}
