package pattern;

import java.util.Vector;

/*
 *	Abstract Superclass of data to be observed. Takes care of the
 *	Observers and notifications 
 */

public abstract class Observable {

	public Observable() {
		this.observers = new Vector();
	}

	public Object attach(Observer o) {
		this.observers.addElement(o);
		return this.getUpdate();
	}

	public void detach(Observer o) {
		for (int i = 0; i < observers.size(); i++) {
			if (observers.elementAt(i).equals(o))
				observers.removeElementAt(i);
		}
	}

	public void notifyObservers() {
		for (int i = 0; i < observers.size(); i++) {
			Observer observer = (Observer) observers.elementAt(i);
			observer.update(this.getUpdate());
		}
	}

	public abstract Object getUpdate();

	protected Vector observers;
}
