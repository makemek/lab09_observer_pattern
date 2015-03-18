package pattern;

/*
 *	Observer interface, which all Observers must implement 
 */

public interface SubjectObserver {
	public void stateChange(CourseRecord o);
}
