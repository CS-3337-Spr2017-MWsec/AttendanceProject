package application.view;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;

public class CourseViewController {
	private Main main;
	
	@FXML
	private void showAddCourseStageBt() throws IOException {
		main.showAddCourseStage();
		
	}
	
	@FXML
	private void loadCourseBt() throws IOException {
		main.openFile();
		main.load(main.file);
		
	}
}
