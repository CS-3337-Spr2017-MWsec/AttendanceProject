package application.view;





import application.Main;
import application.classes.Course;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class HomeViewController {
	
	@FXML
	private TextFlow roster;
	
	@FXML
	private TextFlow dates;
	
	private Main main;
	
	@FXML
	private void takeAttendanceScreen() {
		Course course = main.currentCourse;
		System.out.println(course.getStudents().get(0).getFirstName());
		main.takeAttendance(main.currentCourse);
		
	}
	public static String fillString(int count, String s) {
	   
	    for( int i=0; i<count; i++ ) {
	      if(s.length() < count)
	    	s += (" "); 
	    }
	    return s;
	}
	@FXML
	private void setTable() {
		String courseInfo = main.currentCourse.getCourseName() + " " + main.currentCourse.getCourseNumber() + " "
				+ main.currentCourse.getDays() + " " + main.currentCourse.getTime() + "\n";
		
		String studentInfo = "";
		
		String fHeader = fillString(25, "First Name");
		System.out.println(fHeader.length());
		
		String lHeader = fillString(25, "Last Name");
		
	
		String idHeader = fillString(25, "Id Number");
		
		String emailHeader = fillString(30, "Guardian Email");
		studentInfo += fHeader + lHeader + idHeader + emailHeader + "\n";
		
		String dateInfo = "";
		
		for (int i = 0; i < main.currentCourse.students.size(); i++) {
			
			int studentId = main.currentCourse.students.get(i).getId();
			String firstName = fillString(25, main.currentCourse.students.get(i).getFirstName());
			System.out.println(firstName.length());
			String lastName = fillString(25, main.currentCourse.students.get(i).getLastName());
			String email = fillString(30, main.currentCourse.students.get(i).getGuardianEmail());
			String id = fillString(25, Integer.toString(studentId));
			studentInfo += firstName + lastName + id + email;
			studentInfo += "\n";
			
			for(int j = 0 ; j < main.currentCourse.attendanceRecords.size(); j++) {
				String oneDay = main.currentCourse.attendanceRecords.get(j).getStudents().get(i).getLoginTime() +"/" + main.currentCourse.attendanceRecords.get(j).getStudents().get(i).getLogoutTime() + fillString(50, " ");
				dateInfo += oneDay;
			}
			
		}
		
		

		
		
		
		Text rosterText = new Text(studentInfo);
		Text dateText = new Text(dateInfo);
		
	 	roster.getChildren().add(rosterText);
	 	dates.getChildren().add(dateText);
		
		
		
	}
	 
}
