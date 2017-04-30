package application.view;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import application.Main;
import application.classes.AttendanceRecord;
import application.classes.Student;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class EditViewController {
	private Main main;
	
	ArrayList<Student> studentarray = main.currentCourse.students;
	ArrayList<AttendanceRecord> datearray = main.currentCourse.getAttendanceRecords();
	
	@FXML
	private ComboBox namesComboBox;
	@FXML
	private ComboBox datesComboBox;
	@FXML
	private ComboBox actionsComboBox;
	
	@FXML
	private void initilize() {
		clearAllComboBox();
		for(Student a: studentarray)
			namesComboBox.getItems().add(a.getId()+ ": " +a.getFirstName()+ " "+a.getLastName());
		for(AttendanceRecord a: datearray) 
			datesComboBox.getItems().add(a.getDate());
		actionsComboBox.getItems().addAll("Mark Present","Mark Tardy","Mark Absent");
	}
	
	@FXML
	private void editStudentBt() throws ParseException, IOException{
		int id = getidFromCB();		
		Date d = getDateFromCB();
		String str = getActionFromCB();
		main.edit(main.currentCourse, id, d, str);
		main.showHomeScene();
	}
	
	private void clearAllComboBox(){
		namesComboBox.getItems().clear();
		datesComboBox.getItems().clear();
		actionsComboBox.getItems().clear();

	}
	
	private int getidFromCB(){
		String o = namesComboBox.getValue().toString();
		String[] tokens = o.split(":");
		return Integer.parseInt(tokens[0]);
	}
	
	private Date getDateFromCB() throws ParseException{
		String o = datesComboBox.getValue().toString();
		String[] tokens = o.split(" ");
		String day = tokens[5].concat("-").concat(tokens[1]).concat("-").concat(tokens[2]);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MMM-dd");
		Date date = sdf2.parse(day);
	    return date;
	}
	
	private String getActionFromCB(){
		String o = actionsComboBox.getValue().toString();
		return o;
	}
}
