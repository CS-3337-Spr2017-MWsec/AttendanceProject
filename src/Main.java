import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.sql.Timestamp;
import javax.swing.JFileChooser;



public class Main {

	public static void main(String[] args) {
		
		createCourse();
		
		
		
		
		Course currentCourse = null;
		currentCourse = load();
		
		if(currentCourse.attendanceRecords.size() > 0){

			System.out.println(currentCourse.attendanceRecords.get(0).students.get(0).getFirstName());
			System.out.println(currentCourse.attendanceRecords.get(0).students.get(0).getLastName());
			System.out.println(currentCourse.attendanceRecords.get(0).students.get(0).getLoginTime());
			System.out.println(currentCourse.attendanceRecords.get(0).students.get(0).getLogoutTime());
		
		}
	}
	
	//This method will be called when the professor wished to create a new Course. 
	//it prompts the professor to upload a csv file and uses it to create a new file
	//this new file will have the course info and student roster(course.students).  to be edited each attendance day
	public static void createCourse() {
		
	        
		//Choose a correctly formatted CSV file to upload
		System.out.println("Upload CSV file");
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(fileChooser);
	
		
		File selectedFile = fileChooser.getSelectedFile();
		System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		
		
		//Read from CSV File
		ArrayList<String> records = new ArrayList<String>();
		  try
		  {
		    BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
		    String line;
		    while ((line = reader.readLine()) != null)
		    {
		      records.add(line);
		    }
		    reader.close();
		   
		  }
		  catch (Exception e)
		  {
		    System.err.format("Exception occurred trying to read '%s'.", selectedFile);
		    e.printStackTrace();
		   
		  }
		  
		//Prompt user to enter Course information
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter course name: ");
			String name = scanner.nextLine();
			System.out.println("Enter course number: ");
			String number = scanner.nextLine();
			System.out.println("Enter course days: ");
			String days = scanner.nextLine();
			System.out.println("Enter course time: ");
			String time = scanner.nextLine();
			scanner.close();
		
		//Crate a Course from above information
			Course currentCourse = new Course(name, number, days, time);
		
		//Add students into Course (students taken from the CSV File)
		  for(int i = 1; i < records.size(); i++) {
			  
			 String[] tokens = records.get(i).split(",");
			 Student newStudent = new Student(tokens[0], tokens[1], Integer.parseInt(tokens[2]), tokens[3]);
			 currentCourse.students.add(newStudent);
			  
		  }
		  
		 //Select the Directory to put the Attendance Tracker File into.  (We will edit this file for every attendance taken)
		  
		  try{
			  System.out.println("Select Save Directory");
			  JFileChooser directory = new JFileChooser();
			  	
		      directory.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
		      directory.showSaveDialog(null);
		        
		        
			  String path = directory.getSelectedFile().getAbsolutePath().toString() + "\\" + currentCourse.getCourseName()+currentCourse.getDays() + currentCourse.getTime()+".csv";
			  System.out.println(path);
			  File newfile = new File(path);
			  FileWriter fw = new FileWriter(newfile);
			  BufferedWriter bw = new BufferedWriter(fw);
			  bw.write(currentCourse.getCourseName() + "," + currentCourse.getCourseNumber() + "," + currentCourse.getDays() + "," + currentCourse.getTime());
			  bw.newLine();
			  for (int i = 0; i < currentCourse.students.size(); i ++) {
					bw.write(currentCourse.students.get(i).getFirstName() + ",");
					bw.write(currentCourse.students.get(i).getLastName() + ",");
					bw.write(currentCourse.students.get(i).getId()+ ",");
					bw.write(currentCourse.students.get(i).getGuardianEmail());
					bw.newLine();
			  }
			  bw.close();
		  } catch(Exception e){
		
		  }
		 
		  
	}
	
	
	public static Course load() {
		
		System.out.println("Select Course to Take Attendance");
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(fileChooser);
	
		File selectedFile = null;
	
		    selectedFile = fileChooser.getSelectedFile();
		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		
		
		//Read from CSV File
		ArrayList<String> records = new ArrayList<String>();
		
		  try
		  {
		    BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
		    String line;
		    while ((line = reader.readLine()) != null)
		    {
		      records.add(line);
		    }
		    
		   
		  }
		  catch (Exception e)
		  {
		    System.err.format("Exception occurred trying to read '%s'.", selectedFile);
		    e.printStackTrace();
		   
		  }
		  
		  String [] courseInfo = records.get(0).split(",");
		  Course currentCourse = new Course(courseInfo[0], courseInfo[1], courseInfo[2], courseInfo[3]);
		  if(courseInfo.length > 4) {
			  for(int i = 4; i < courseInfo.length; i++) {
				  String temp_date = courseInfo[i];
				  String pattern = "MM/dd/yyyy";
				  SimpleDateFormat format = new SimpleDateFormat(pattern);
				  try {
				      Date date = format.parse(temp_date);
				      System.out.println(date);
				      AttendanceRecord currentAttendanceRecord = new AttendanceRecord(date);
				      currentCourse.attendanceRecords.add(currentAttendanceRecord);
				      for(int j =1; j < records.size(); j++) {
				    	  String[] studentInfo = records.get(j).split(",");
				    	  Student currentStudent = new Student(studentInfo[0], studentInfo[1], Integer.parseInt(studentInfo[2]), studentInfo[3]);
				    	  String[]timeStamps = studentInfo[i].split("/");
				    	  String delims = "[-,:,. ]+";
				    	  
				    	  String [] firsttimeStamp = timeStamps[0].split(delims);
				    	  Long loginStamp =Long.parseLong(firsttimeStamp[0].concat(firsttimeStamp[1].concat(firsttimeStamp[2].concat(firsttimeStamp[3].concat(firsttimeStamp[4].concat(firsttimeStamp[5].concat(firsttimeStamp[6])))))));
				    	  Timestamp loginTime = new Timestamp(loginStamp);
				    	  
				    	  String [] secondtimeStamp = timeStamps[0].split(delims);
				    	  Long logoutStamp =Long.parseLong(secondtimeStamp[0].concat(secondtimeStamp[1].concat(firsttimeStamp[2].concat(firsttimeStamp[3].concat(firsttimeStamp[4].concat(firsttimeStamp[5].concat(firsttimeStamp[6])))))));
				    	  Timestamp logoutTime = new Timestamp(logoutStamp);
				    	  
				    	  
				    	  currentStudent.setLoginTime(loginTime);
				    	  currentStudent.setLogoutTime(logoutTime);
				    	  currentAttendanceRecord.students.add(currentStudent);
				    	  currentCourse.attendanceRecords.add(currentAttendanceRecord);
				      }
				    } catch (ParseException e) {
				      e.printStackTrace();
				    }
				  }
			  
			  }
		  
		  for(int i = 1; i < records.size(); i++) {
			  
				 String[] tokens = records.get(i).split(",");
				 Student newStudent = new Student(tokens[0], tokens[1], Integer.parseInt(tokens[2]), tokens[3]);
				 currentCourse.students.add(newStudent);
				 
				 
				 for(int j= 4; i < tokens.length; i ++) {
					 
				 }
				   
		  }
		  
		 
		  
		  return currentCourse;
		  
	
	}
	
	
	public static void parse() {
		

		Administrator test = new Administrator("Name","Mr","Richards","email","richards","ilovejose");
		//example: student card scan
		Scanner scanner = new Scanner(System.in);
		System.out.println("scan now");
		String student = scanner.next();
		// %B6048880000790184^ROJAS/CESAR^4912120000000000000000000000000?;6048880000790184=4912120304418483?
		//
		
		
		String trash;
		String trash_2;
		String firstName;
		String lastName;
		String trash_3;
		String trash_4;
		String midName;
		String temp_id;
		
		
		int number = 1;
		String delims = "[%,/,?,=,;,^, ]+";
		String[] tokens = student.split(delims);
		for (int i = 0; i < tokens.length; i++)
			
			trash = tokens[0];
			trash_2 = tokens[1];
			firstName = tokens[2];
			lastName = tokens[3];
			trash_3 = tokens[4];
			trash_4 = tokens[5];
			temp_id = tokens[6];
			String id = temp_id.substring(7, temp_id.length() -1);
		System.out.println("firstName: " + firstName);
		System.out.println("lastName: " + lastName);
	
		//System.out.println("id: " + temp_id);
		ArrayList<String> strings = new ArrayList<String>();
		int index = 0;
		while (index < temp_id.length()) {
			strings.add(temp_id.substring(index, Math.min(index + 7, temp_id.length())));
			index += 7;
		}
		System.out.println("id: " + id);
		scanner.close();
		

	}

	


	
}// end of Main()
