package application.view;

import application.Main;
import javafx.fxml.FXML;
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
	public void addStudentConfirmBt() {
		System.out.println(firstName.getText() + " " + lastName.getText() + " " + CIN.getText() + " " + email.getText());
	}
}
