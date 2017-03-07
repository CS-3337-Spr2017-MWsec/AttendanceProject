import java.util.ArrayList;

public class Course {
	
	private String courseName;
	private int courseNumber;
	private String days;
	private String time;
	private ArrayList<AttendanceRecord> attendanceRecords = new ArrayList<AttendanceRecord>();
	
	public Course(String courseName, int courseNumber, String days, String time, ArrayList<AttendanceRecord> attendanceRecords) {
		super();
		this.courseName = courseName;
		this.courseNumber = courseNumber;
		this.days = days;
		this.time = time;
		this.attendanceRecords = attendanceRecords;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public ArrayList<AttendanceRecord> getAttendanceRecords() {
		return attendanceRecords;
	}

	public void setRecords(ArrayList<AttendanceRecord> attendanceRecords) {
		this.attendanceRecords = attendanceRecords;
	} 
	
	
	
}

