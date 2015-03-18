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
		this.notifyStateChange(subjectRecord);
	}

	public void changeSubjectRecord(String subjectName, int numOfStudents) {
        CourseRecord match = null;
		for (int i = 0; i < subjectData.size(); i++) {
			CourseRecord record = subjectData.elementAt(i);
			if (record.getSubject().equals(subjectName)) {
				record.setNumOfStudents(numOfStudents);
                match = record;
				i = subjectData.size();
			}
		}
		printState();
		this.notifyStateChange(match);
	}

	public Vector<CourseRecord> getSubjectData() {
		return subjectData;
	}

	//use the pull model
	public CourseRecord[] getUpdate() {
		Vector<CourseRecord> data = getSubjectData();
        return data.toArray(new CourseRecord[data.size()]);
	}

	private void printState() {
		for (int i = 0; i < subjectData.size(); i++) {
			CourseRecord record = subjectData.elementAt(i);
			System.out.println(record.toString());
		}
	}

	private Vector<CourseRecord> subjectData;
}
