package pattern;

/*
 *	Observer interface, which all Observers must implement 
 */

public interface SubjectObserver {
	public void onValueChanged(CourseRecord o);
    public void newRecord(CourseRecord rec);
}
