
import java.text.DateFormat;
import java.util.ArrayList;


/* example input: 
 * %B6048880000654321^PERSON/THREE^4912120000000000000000000000000?;6048880000419099=4912120303452468?
 */


public class AttendanceRecord{
	
	
	DateFormat dateTime;
	ArrayList<Student> students = new ArrayList<Student>();
	
	public AttendanceRecord(DateFormat dateTime, ArrayList<Student> students) {
		super();
		this.dateTime = dateTime;
		this.students = students;
	}
	
	public DateFormat getDateTime() {
		return dateTime;
	}
	public void setDateTime(DateFormat dateTime) {
		this.dateTime = dateTime;
	}
	public ArrayList<Student> getStudents() {
		return students;
	}
	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
	
	public void start() {	// Method will start the scanning process and wont stop until specified. This method should go to students array and change status
							// to be true of false. Please check to see if master student array is immutable or not 
		
	}
	public void checkNotification(Student student) {	//Check to see if student has signed up for absent reports. if yes send email else do nothing
										
	}
	
	
	
	
}


