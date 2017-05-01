package application.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import application.Main;
import application.classes.AttendanceRecord;
import application.classes.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class AddStudentViewController {
	private Main main;

	@FXML
	private TextField firstName;

	@FXML
	private TextField lastName;

	@FXML
	private TextField CIN;
	
	@FXML
	private TextField email;
	
	@FXML
	public void addStudentConfirmBt() throws IOException {
		if(firstName.getText().isEmpty() || lastName.getText().isEmpty() || CIN.getText().isEmpty() || email.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR, "Please fill in all neccesary student information.");
			Optional<ButtonType> result = alert.showAndWait();
			 if (result.isPresent() && result.get() == ButtonType.OK) {
			 }
		}
		else {
			Student student = new Student(firstName.getText(),lastName.getText(),Integer.parseInt(CIN.getText()),email.getText());
			main.currentCourse.students.add(student);
			ArrayList<AttendanceRecord> ar = main.currentCourse.getAttendanceRecords();
			for(AttendanceRecord z:ar)
				z.students.add(student);
			Alert alert = new Alert(AlertType.INFORMATION, "Student added into " + main.currentCourse.getCourseName() + " " + main.currentCourse.getCourseNumber());
			alert.show();
			clearText();
		}
		main.showHomeScene();
<<<<<<< HEAD
=======

>>>>>>> branch 'master' of https://github.com/anguyen8613/AttendanceProject.git
	}
	
	public void clearText(){
		firstName.clear();
		lastName.clear();
		CIN.clear();
		email.clear();
	}
}
