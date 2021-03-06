package pattern;

import java.util.Vector;

/*
 *	Abstract Superclass of data to be observed. Takes care of the
 *	Observers and notifications 
 */

public abstract class Observable {

	public Observable() {
		this.observers = new Vector<SubjectObserver>();
	}

	public Object attach(SubjectObserver o) {
		this.observers.addElement(o);
		return this.getUpdate();
	}

	public void detach(SubjectObserver o) {
		for (int i = 0; i < observers.size(); i++) {
			if (observers.elementAt(i).equals(o))
				observers.removeElementAt(i);
		}
	}

	public void notifyStateChange(CourseRecord record) {
		for (int i = 0; i < observers.size(); i++) {
			SubjectObserver observer = observers.elementAt(i);
			observer.onValueChanged(record);
		}
	}

    public void notifyNewRecord(CourseRecord record) {
        for(SubjectObserver obs: observers)
            obs.newRecord(record);
    }

	public abstract CourseRecord[] getUpdate();

	protected Vector<SubjectObserver> observers;
}
