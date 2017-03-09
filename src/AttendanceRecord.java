
import java.text.DateFormat;
import java.util.ArrayList;


/* example input: 
 * %B6048880000654321^PERSON/THREE^4912120000000000000000000000000?;6048880000419099=4912120303452468?
 */


public class AttendanceRecord {
	
	
	DateFormat dateTime;
	ArrayList<Student> students = new ArrayList<Student>();
	
	public AttendanceRecord(DateFormat dateTime) {
		super();
		this.dateTime = dateTime;
		
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
	
	
	
	
}


