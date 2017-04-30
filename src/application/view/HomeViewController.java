package application.view;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import application.classes.Course;
import application.classes.Student;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class HomeViewController {

	@FXML
	private TableView studentTable;

	@FXML
	private TableView attendanceTable;

	private Main main;
	
	@FXML
	private void addStudentBt() throws IOException {
	main.showAddStudentStage();
	}

	@FXML
	private void takeAttendanceScreen() {
		Course course = main.currentCourse;
		System.out.println(course.getStudents().get(0).getFirstName());
		main.takeAttendance(main.currentCourse);

	}

	@FXML
	public void initialize() {
		setStudentTable();
		setAttendanceTable();
	}

	public static String fillString(int n, String s) {
		return String.format("%1$-" + n + "s", s);
	}

	@FXML
	public void setStudentTable() {
		TableColumn firstNameCol = new TableColumn("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
		firstNameCol.setPrefWidth(120);
		firstNameCol.setSortable(false);

		TableColumn lastNameCol = new TableColumn("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
		lastNameCol.setPrefWidth(120);
		lastNameCol.setSortable(false);

		TableColumn idCol = new TableColumn("CIN");
		idCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
		idCol.setPrefWidth(120);
		idCol.setSortable(false);

		TableColumn emailCol = new TableColumn("Guardian Email");
		emailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("guardianEmail"));
		emailCol.setPrefWidth(150);
		emailCol.setSortable(false);

		studentTable.getColumns().addAll(firstNameCol, lastNameCol, idCol, emailCol);

		ObservableList<Student> data = FXCollections.observableArrayList();

		for (Student student : main.currentCourse.students) {
			data.add(student);
		}
		studentTable.setItems(data);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	public void setAttendanceTable() {
		if (main.currentCourse.attendanceRecords.size() > 0) {
			List<String> columns = new ArrayList<String>();
			for (int i = 0; i < main.currentCourse.getAttendanceRecords().size(); i++) {
				columns.add(main.currentCourse.getAttendanceRecords().get(i).getDate().toString());
			}
			TableColumn[] tableColumns = new TableColumn[columns.size()];
			int columnIndex = 0;
			for (int i = 0; i < columns.size(); i++) {
				final int j = i;
				TableColumn col = new TableColumn(columns.get(i));
				col.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
								return new SimpleStringProperty(param.getValue().get(j).toString());
							}
						});
				attendanceTable.getColumns().addAll(col);
			}

			ObservableList<String> row1 = FXCollections.observableArrayList();
			ObservableList<String> row2 = FXCollections.observableArrayList();
			ObservableList<String> row3 = FXCollections.observableArrayList();
			ObservableList<String> row4 = FXCollections.observableArrayList();

			for (int i = 0; i < 4; i++) {
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int j = 0; j < 5; j++) {
					String x = "";
					x = main.currentCourse.getAttendanceRecords().get(j).getStudents().get(i).getStatus();

					row.add(x);
					if (main.currentCourse.getAttendanceRecords().get(j).getStudents().get(i).getStatus()
							.equals("absent")) {

					}
				}
				attendanceTable.getItems().add(row);
			}
		}

		/*
		 * for (int i = 0; i < main.currentCourse.students.size(); i++) {
		 * ObservableList<ArrayList<AttendanceRecord>> day =
		 * FXCollections.observableArrayList();
		 * 
		 * for (int j = 0; j < main.currentCourse.attendanceRecords.size(); j++)
		 * {
		 * 
		 * //day.add(main.currentCourse.attendanceRecords.get(j).getStudents().
		 * get(i).getLoginTime() + "/" // +
		 * main.currentCourse.attendanceRecords.get(j).getStudents().get(i).
		 * getLogoutTime());
		 * main.currentCourse.students.get(i).attendanceRecords.add(main.
		 * currentCourse.attendanceRecords.get(j));
		 * 
		 * 
		 * System.out.println(); }
		 * day.add(main.currentCourse.students.get(i).attendanceRecords);
		 * 
		 * data.add(day); }
		 */
		// attendanceTable.setItems(data);;

	}

}
