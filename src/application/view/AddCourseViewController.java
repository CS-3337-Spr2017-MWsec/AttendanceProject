package application.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import application.Main;
import application.classes.Course;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddCourseViewController {
	private Main main;

	@FXML
	TextField name;
	@FXML
	TextField number;
	@FXML
	TextField days;
	@FXML
	TextField time;

	@FXML
	void createCourseConfirmBt() throws FileNotFoundException {

		main.createCourse(name.getText(), number.getText(), days.getText(), time.getText());
	}

	
	
	
}
