import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;



public class Main {

	public static void main(String[] args) {
	
		
		Course currentCourse = null;
		currentCourse = createRoster();
		
		//test to see if the current course roster is updated;
		for (int i = 0 ; i < currentCourse.students.size(); i ++) {
			System.out.print(currentCourse.students.get(i).getFirstName() + " ");
			System.out.print(currentCourse.students.get(i).getLastName() + " ");
			System.out.print(currentCourse.students.get(i).getId()+ " ");
			System.out.println("");
			
		}
		
		
		
	}
	
	public static Course createRoster() {
		
		//Choose the course File
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(fileChooser);
		File selectedFile = null;
		if (result == JFileChooser.APPROVE_OPTION) {
		    selectedFile = fileChooser.getSelectedFile();
		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		}
		
		//Read from File
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
		  
		//Course information
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter course name: ");
			String name = scanner.nextLine();
			System.out.println("Enter course number: ");
			String number = scanner.nextLine();
			System.out.println("Enter course days: ");
			String days = scanner.nextLine();
			System.out.println("Enter course time: ");
			String time = scanner.nextLine();
			
			Course currentCourse = new Course(name, number, days, time);
		
			//Add students into Course
		  for(int i = 1; i < records.size(); i++) {
			  
			 String[] tokens = records.get(i).split(",");
			 Student newStudent = new Student(tokens[1], tokens[2], Integer.parseInt(tokens[0]), tokens[3]);
			 currentCourse.students.add(newStudent);
			  
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
		
		
		
		

	}

	


	
}// end of Main()
