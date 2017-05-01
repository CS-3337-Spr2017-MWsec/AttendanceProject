package application.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.mail.Transport;

import application.Main;
import application.classes.AttendanceRecord;
import application.classes.Course;
import application.classes.Student;

	public class MessagingController {

		private Main main;
		
		ArrayList<AttendanceRecord> temp = main.currentCourse.getAttendanceRecords();

		AttendanceRecord last = temp.get(temp.size() - 1);

		ArrayList<Student> studentlist = last.getStudents();
		
		//ArrayList<Student> email = last.getGuardianEmail();


		
		private void messagingBt() throws IOException {
			main.messaging();
	
}}
