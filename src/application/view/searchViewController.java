package application.view;

import java.util.ArrayList;
import application.Main;
import application.classes.AttendanceRecord;
import application.classes.Student;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class searchViewController {

	private Main main;

	ArrayList<AttendanceRecord> temp = main.currentCourse.getAttendanceRecords();

	AttendanceRecord last = temp.get(temp.size() - 1);

	ArrayList<Student> studentlist = last.getStudents();

	@FXML
	TextField id;

	@FXML
	Text info1;
	@FXML
	Text info2;
	@FXML
	Text info3;
	@FXML
	Text info4;

	@FXML
	void searchbt() {
		int searchId = Integer.parseInt(id.getText());
		Main.search(searchId, main.currentCourse);
		id.clear();

		for (Student a : studentlist) {

			if (a.getId() == searchId) {

				info1.setText("Name: " + a.getFirstName() + " " + a.getLastName());

				if (a.getLoginTime() != null && a.getLogoutTime() != null) {

					info2.setText("Status: Present on " + last.getDate());

					info3.setText("Log in time: " + a.getLoginTime());

					info4.setText("Log out time: " + a.getLogoutTime());

				}

				else

					info2.setText("Status: Absent on " + last.getDate());

			}
		}
	}
}