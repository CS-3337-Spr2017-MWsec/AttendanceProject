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

		Course currentCourse = new Course(name.getText(), number.getText(), days.getText(), time.getText());

		PrintWriter pw = new PrintWriter(new File(
				name.getText() + "_" + number.getText() + "_" + days.getText() + "_" + time.getText() + ".csv"));
		StringBuilder sb = new StringBuilder();
		sb.append("First Name");
		sb.append(',');
		sb.append("Last Name");
		sb.append(',');
		sb.append("Guardian Email");
		sb.append(',');
		sb.append("Id");
		sb.append('\n');

		pw.write(sb.toString());
		pw.close();

		name.clear();
		number.clear();
		days.clear();
		time.clear();
	}

}
