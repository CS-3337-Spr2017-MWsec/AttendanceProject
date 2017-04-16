
import java.util.ArrayList;
import java.util.Date;


/* example input: 
 * %B6048880000654321^PERSON/THREE^4912120000000000000000000000000?;6048880000419099=4912120303452468?
 */


public class AttendanceRecord{
	
	
	Date date;
	protected ArrayList<Student> students = new ArrayList<Student>();
	
	public AttendanceRecord(Date date) {
		super();
		this.date = date;
		
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	
	public int getStudentById(int Id) {
		int index = -1;
		
		for(int i = 0 ; i < this.getStudents().size(); i ++) {
			if(this.getStudents().get(i).getId() == Id) {
				return index;
			}
		}
		return index;
	}
	
	
	
}


