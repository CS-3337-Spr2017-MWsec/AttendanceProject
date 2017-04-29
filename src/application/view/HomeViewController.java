package application.view;

import java.util.ArrayList;

import application.Main;
import application.classes.Course;
import application.classes.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HomeViewController{
	
	@FXML
	private TableView studentTable;

	@FXML
	private TableView attendanceTable;

	private Main main;

	@FXML
	private void takeAttendanceScreen() {
		Course course = main.currentCourse;
		System.out.println(course.getStudents().get(0).getFirstName());
		main.takeAttendance(main.currentCourse);

	}
	
	@FXML
	public void initialize(){
		setStudentTable();
		setAttendanceTable();
	}

	public static String fillString(int n, String s) {
		return String.format("%1$-" + n + "s", s);
	}

	@FXML
	public void setStudentTable() {
		TableColumn firstNameCol = new TableColumn("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory<Student,String>("firstName"));
		firstNameCol.setPrefWidth(120);
		
		TableColumn lastNameCol = new TableColumn("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
		lastNameCol.setPrefWidth(120);
		
		TableColumn idCol= new TableColumn("CIN");
		idCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
		idCol.setPrefWidth(120);
		
		TableColumn emailCol = new TableColumn("Guardian Email");
		emailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("guardianEmail"));
		emailCol.setPrefWidth(150);
		
		studentTable.getColumns().addAll(firstNameCol, lastNameCol, idCol, emailCol);
	
		ObservableList<Student> data = FXCollections.observableArrayList();

		for(Student student : main.currentCourse.students){
			data.add(student);
		}
		studentTable.setItems(data);

	}
	
	@FXML
	public void setAttendanceTable(){
		for(int i = 0; i < main.currentCourse.attendanceRecords.size(); i++) {
			
			String dateString = main.currentCourse.attendanceRecords.get(i).getDate() + "";
			TableColumn date = new TableColumn(dateString);
			date.setCellValueFactory(new PropertyValueFactory<ArrayList, String>("i"));
			date.setPrefWidth(250);
			attendanceTable.getColumns().add(date);
			attendanceTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		}
	
		
		
	}
		
}
