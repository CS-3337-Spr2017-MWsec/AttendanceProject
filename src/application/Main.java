package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JFileChooser;

import application.classes.Administrator;
import application.classes.AttendanceRecord;
import application.classes.Course;
import application.classes.Student;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	private static Stage primaryStage; 
	private static BorderPane mainLayout;
	public static File file = null;
	public static Course currentCourse = new Course();

	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Attendance Tracker");
		showLoginView();
	}

	// Fx Code

	public void showLoginView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/LoginView.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void showCourseScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/CourseView.fxml"));
		BorderPane courses = loader.load();
		mainLayout.setCenter(courses);
	}

	public static void showHomeScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/HomeView.fxml"));
		BorderPane home = loader.load();
		mainLayout.setCenter(home);


	}
	
	

	// Java Code
	public static void createCourse() {

		// Choose a correctly formatted CSV file to upload

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(fileChooser);

		File selectedFile = fileChooser.getSelectedFile();

		// Read from CSV File
		ArrayList<String> records = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
			String line;
			while ((line = reader.readLine()) != null) {
				records.add(line);
			}
			reader.close();

		} catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.", selectedFile);
			e.printStackTrace();

		}

		// Prompt user to enter Course information
		/*
		 * Scanner scanner = new Scanner(System.in);
		 * System.out.println("Enter course name: "); String name =
		 * scanner.nextLine(); System.out.println("Enter course number: ");
		 * String number = scanner.nextLine();
		 * System.out.println("Enter course days: "); String days =
		 * scanner.nextLine(); System.out.println("Enter course time: "); String
		 * time = scanner.nextLine();
		 */
		String name = "SoftWareE";
		String number = "3337";
		String days = "MW";
		String time = "6pm";

		// Crate a Course from above information
		Course currentCourse = new Course(name, number, days, time);

		// Add students into Course (students taken from the CSV File)
		for (int i = 1; i < records.size(); i++) {

			String[] tokens = records.get(i).split(",");
			Student newStudent = new Student(tokens[0], tokens[1], Integer.parseInt(tokens[2]), tokens[3]);
			currentCourse.students.add(newStudent);

		}

		// Select the Directory to put the Attendance Tracker File into. (We
		// will edit this file for every attendance taken)

		try {

			JFileChooser directory = new JFileChooser();

			directory.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			directory.showSaveDialog(null);

			String path = directory.getSelectedFile().getAbsolutePath().toString() + "\\"
					+ currentCourse.getCourseName() + currentCourse.getDays() + currentCourse.getTime() + ".csv";
			// System.out.println(path);
			File newfile = new File(path);
			FileWriter fw = new FileWriter(newfile);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(currentCourse.getCourseName() + "," + currentCourse.getCourseNumber() + ","
					+ currentCourse.getDays() + "," + currentCourse.getTime());
			bw.newLine();
			for (int i = 0; i < currentCourse.students.size(); i++) {
				bw.write(currentCourse.students.get(i).getFirstName() + ",");
				bw.write(currentCourse.students.get(i).getLastName() + ",");
				bw.write(currentCourse.students.get(i).getId() + ",");
				bw.write(currentCourse.students.get(i).getGuardianEmail());
				bw.newLine();
			}
			bw.close();
		} catch (Exception e) {

		}

	}

	public static void load(File selectedFile) throws IOException {

		// Read from CSV File
		ArrayList<String> records = new ArrayList<String>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
			String line;
			while ((line = reader.readLine()) != null) {
				records.add(line);
			}

		} catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.", selectedFile);
			e.printStackTrace();

		}

		String[] courseInfo = records.get(0).split(",");
		currentCourse.setCourseName(courseInfo[0]);
		currentCourse.setCourseNumber(courseInfo[1]);
		currentCourse.setDays(courseInfo[2]);
		currentCourse.setTime(courseInfo[3]);
		if (courseInfo.length > 4) {
			for (int i = 4; i < courseInfo.length; i++) {
				String temp_date = courseInfo[i];
				String pattern = "MM/dd/yyyy";
				SimpleDateFormat format = new SimpleDateFormat(pattern);
				try {
					Date date = format.parse(temp_date);
					AttendanceRecord currentAttendanceRecord = new AttendanceRecord(date);
					currentCourse.attendanceRecords.add(currentAttendanceRecord);
					for (int j = 1; j < records.size(); j++) {
						String[] studentInfo = records.get(j).split(",");
						Student currentStudent = new Student(studentInfo[0], studentInfo[1],
								Integer.parseInt(studentInfo[2]), studentInfo[3]);
						String[] timeStamps = studentInfo[i].split("/");

						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
						System.out.println(timeStamps[0]);
						if(timeStamps[0].length() > 10) {
							Date dateLogin = sdf.parse(timeStamps[0]);
							Timestamp Login = new Timestamp(dateLogin.getTime());
							currentStudent.setLoginTime(Login);
						}
						if(timeStamps[1].length() > 10) {
							Date dateLogout = sdf.parse(timeStamps[1]);
							Timestamp Logout = new Timestamp(dateLogout.getTime());
							currentStudent.setLogoutTime(Logout);
						}
						currentStudent.setStatus();
						currentAttendanceRecord.students.add(currentStudent);
						
						

					}
					System.out.println(currentCourse.getCourseName());
					System.out.println(currentCourse.getAttendanceRecords().get(0).getStudents().get(1).getLoginTime());
					System.out.println(currentCourse.getAttendanceRecords().get(0).getDate());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

		}

		for (int i = 1; i < records.size(); i++) {

			String[] tokens = records.get(i).split(",");
			Student newStudent = new Student(tokens[0], tokens[1], Integer.parseInt(tokens[2]), tokens[3]);
			currentCourse.students.add(newStudent);
		}
		showHomeScene();
		

	}

	public static void takeAttendance(Course currentCourse) {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		dateFormat.format(date);
		AttendanceRecord currentAttendanceRecord = new AttendanceRecord(date);
		
		currentAttendanceRecord.students = currentCourse.students;

		int currentStudentId = 1;
		System.out.println("System in Login Mode");
		do {
			 currentStudentId = parse(currentCourse);
			if (currentStudentId == 304999154) {
				break;
			}
			
			Student currentStudent = null;
			Timestamp loginTime = new Timestamp(System.currentTimeMillis());
			for(int i = 0; i < currentAttendanceRecord.students.size(); i++) {
				if(currentAttendanceRecord.students.get(i).getId() == currentStudentId) {
					currentStudent = currentAttendanceRecord.students.get(i);
				}
			}
			currentStudent.setLoginTime(loginTime);
			

		} while (currentStudentId != 304999154);

		System.out.println("System in Standby Mode");
		currentStudentId = 1;
		do {
			currentStudentId = parse(currentCourse);
			if (currentStudentId == 304999154) {
				break;
			}
			System.out.println("System in Standby Mode");

		} while (currentStudentId != 304999154);
		System.out.println("System in Logout Mode");
		currentStudentId = 1;
		do {
			 currentStudentId = parse(currentCourse);
			if (currentStudentId == 304999154) {
				break;
			}
			
			Student currentStudent = null;
			Timestamp logoutTime = new Timestamp(System.currentTimeMillis());
			for(int i = 0; i < currentAttendanceRecord.students.size(); i++) {
				if(currentAttendanceRecord.students.get(i).getId() == currentStudentId) {
					currentStudent = currentAttendanceRecord.students.get(i);
				}
			}
			currentStudent.setLogoutTime(logoutTime);
			

		} while (currentStudentId != 304999154);

		currentCourse.attendanceRecords.add(currentAttendanceRecord);
		writeToFile(file);

	}

	public static int parse(Course currentCourse) {

		Administrator test = new Administrator("Name", "Mr", "Richards", "email", "richards", "ilovejose");
		// example: student card scan
		String student = "";
		do {
			System.out.println("scan now");
			Scanner scanner = new Scanner(System.in);
			student = scanner.nextLine();

			if (student.length() < 95) {
				System.out.println("Scan Failed!!!!");
			}

		} while (student.length() < 95);

		String firstName;
		String lastName;
		String temp_id;

		int number = 1;
		String delims = "[%,/,?,=,;,^, ]+";
		String[] tokens = student.split(delims);

		firstName = tokens[2];
		lastName = tokens[3];
		temp_id = tokens[6];
		int id = Integer.parseInt(temp_id.substring(7, temp_id.length()));

		Student currentStudent = new Student();
		for (int i = 0; i < currentCourse.students.size(); i++) {
			if (id == currentCourse.students.get(i).getId()) {
				currentStudent = currentCourse.students.get(i);
			}
		}

		System.out.println("firstName: " + firstName);
		System.out.println("lastName: " + lastName);

		System.out.println("id: " + id);

		return id;

	}

	public static void writeToFile(File selectedFile) {

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			fw = new FileWriter(selectedFile);
			bw = new BufferedWriter(fw);
		
			String courseInfo = currentCourse.getCourseName() + "," + currentCourse.getCourseNumber() + ","
					+ currentCourse.getDays() + "," + currentCourse.getTime();
			
			System.out.println(currentCourse.getAttendanceRecords().size());
			for (int i = 0; i < currentCourse.getAttendanceRecords().size(); i++) {
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				String date = df.format(currentCourse.getAttendanceRecords().get(i).getDate());
				courseInfo += ","+ date;
				
			}
			
			
			bw.write(courseInfo);
			bw.newLine();

			
			for (int i = 0; i < currentCourse.students.size(); i++) {
				String studentInfo = "";
				int studentId = currentCourse.students.get(i).getId();
				studentInfo += currentCourse.students.get(i).getFirstName() + ","
						+ currentCourse.students.get(i).getLastName() + "," + studentId + ","
						+ currentCourse.students.get(i).getGuardianEmail() + ",";
				
				for(int j = 0 ; j < currentCourse.attendanceRecords.size(); j++) {
					studentInfo += currentCourse.attendanceRecords.get(j).getStudents().get(i).getLoginTime() +"/" + currentCourse.attendanceRecords.get(j).getStudents().get(i).getLogoutTime() + ",";
				}
				bw.write(studentInfo);
				bw.newLine();
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}

	}

	public static File openFile() {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(fileChooser);

		File selectedFile = null;

		selectedFile = fileChooser.getSelectedFile();
		file = selectedFile;
		return selectedFile;
	}

	public static int search(int id, Course currentCourse) {
		ArrayList<AttendanceRecord> temp = currentCourse.getAttendanceRecords();
		AttendanceRecord last = temp.get(temp.size()-1);
		ArrayList<Student> studentlist = last.getStudents();
		for(Student a: studentlist) {
			if(a.getId()==id) {
				System.out.println("Name: "+ a.getFirstName() + " " + a.getLastName());
				if(a.getLoginTime()!=null && a.getLogoutTime()!=null) {
					System.out.println("Status: Present on " + last.getDate());
					System.out.println("Log in time: " + a.getLoginTime());
					System.out.println("Log out time: " +a.getLogoutTime());
				}
				else
					System.out.println("Status: Absent on " + last.getDate());
				return 1;
			}
		}
		return -1;
	}

	public static void edit(int id, Course currentCourse) {
		ArrayList<AttendanceRecord> temp = currentCourse.getAttendanceRecords();
		AttendanceRecord last = temp.get(temp.size()-1);
		ArrayList<Student> studentlist = last.getStudents();
		int numOfStudents = studentlist.size();
		search(id,currentCourse);
		
		System.out.println("How many days would you like to go back?");
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();

		    //Grab the Student list for the requested day
		    ArrayList<Student> currentARStudentList = temp.get(temp.size()-1-i*(numOfStudents+1)).getStudents();

		    //Iterate through students and change login and log out times when the student is found
		    for(Student a:currentARStudentList) 
			if(a.getId()==id) {

			//Absent to present
			if(a.getLoginTime()==null && a.getLogoutTime()==null) {
				Scanner input = new Scanner(System.in);
				System.out.println("Name: " +a.getFirstName() + " " + a.getLastName()); 
				System.out.println("Log in time: " + a.getLoginTime());
				System.out.println("Log out time: "+ a.getLogoutTime());
				System.out.println("Would you like to make this student present? Y/N ");
				String scinput = input.nextLine();
				if(scinput.toUpperCase()=="Y") {
					//Trick the system into logging in and logging out without the need for user input Timestamps
					Timestamp tempLogin = new Timestamp(System.currentTimeMillis());
					a.setLoginTime(tempLogin);
					Timestamp tempLogout = new Timestamp(System.currentTimeMillis());
					a.setLogoutTime(tempLogout);
					System.out.println("Student has been marked present");
				}
			}

			//Present to absent
			if(a.getLoginTime()!=null || a.getLogoutTime()!=null) {
				Scanner input = new Scanner(System.in);
				System.out.println("Name: " +a.getFirstName() + " " + a.getLastName()); 
				System.out.println("Log in time: " + a.getLoginTime());
				System.out.println("Log out time: "+ a.getLogoutTime());
				System.out.println("Would you like to make this student absent? Y/N ");
				String scinput = input.nextLine();
				if(scinput.toUpperCase()=="Y") {
					a.setLoginTime(null);
					a.setLogoutTime(null);
					System.out.println("Student has been marked absent");

				}
			}
		}
	}

	public static void main(String[] args) {

		launch(args);
	}

}
