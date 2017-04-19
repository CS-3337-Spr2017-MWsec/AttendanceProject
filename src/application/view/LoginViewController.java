package application.view;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;

public class LoginViewController {
	private Main main;
	
	@FXML
	private void CourseScreen() throws IOException{
		main.showCourseScene();
	}
}
