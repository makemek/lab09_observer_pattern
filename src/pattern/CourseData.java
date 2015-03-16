package pattern;

import java.util.Vector;

import javax.swing.JOptionPane;

/*
 *	Stores the Course Data to be diplayed 
 */

public class CourseData extends Observable {

	public CourseData() {
		this.subjectData = new Vector<CourseRecord>();
	}

	public void addSubjectRecord(CourseRecord subjectRecord) {
		boolean alreadyExists = false;
		for (int i = 0; i < subjectData.size(); i++) {
			CourseRecord record = subjectData.elementAt(i);
			if (record.getSubject().equals(subjectRecord.getSubject())) {
				alreadyExists = true;
				JOptionPane
						.showMessageDialog(
								null,
								"Warning: Attempt to add new subject with existing name",
								"alert", JOptionPane.ERROR_MESSAGE);
				i = subjectData.size(); //exit the loop
			}
		}
		if (!alreadyExists)
			this.subjectData.addElement(subjectRecord);
		printState();
		this.notifyObservers();
	}

	public void changeSubjectRecord(String subjectName, int numOfStudents) {
		for (int i = 0; i < subjectData.size(); i++) {
			CourseRecord record = subjectData.elementAt(i);
			if (record.getSubject().equals(subjectName)) {
				record.setNumOfStudents(numOfStudents);
				i = subjectData.size();
			}
		}
		printState();
		this.notifyObservers();
	}

	public Vector<CourseRecord> getSubjectData() {
		return subjectData;
	}

	//use the pull model
	public CourseData getUpdate() {
		return this;
	}

	private void printState() {
		for (int i = 0; i < subjectData.size(); i++) {
			CourseRecord record = subjectData.elementAt(i);
			System.out.println(record.toString());
		}
	}

	private Vector<CourseRecord> subjectData;
}
