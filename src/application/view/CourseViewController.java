package application.view;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;

public class CourseViewController {
	private Main main;
	
	@FXML
	private void createCourse() {
		main.createCourse();
	}
	
	@FXML
	private void loadCourse() throws IOException {
		main.openFile();
		main.load(main.file);
		
	}
}
