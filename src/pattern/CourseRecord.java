package pattern;

/*
 *	An individual Course Record, which hold the data for one Course 
 */

public class CourseRecord {

	public CourseRecord(String subject, int numOfStudents) {
		this.subject = subject;
		this.numOfStudents = numOfStudents;
	}

	public int getNumOfStudents() {
		return numOfStudents;
	}

	public String getSubject() {
		return subject;
	}

	public void setNumOfStudents(int numOfStudents) {
		this.numOfStudents = numOfStudents;
	}

	public String toString() {
		return "Subject = " + this.subject + ", Number of Students = "
				+ this.numOfStudents;
	}

	private String subject;

	private int numOfStudents;
}
