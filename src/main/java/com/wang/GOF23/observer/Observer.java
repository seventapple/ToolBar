package com.wang.GOF23.observer;

public interface Observer {
	void update(Subject obj);
}

class ObserverA implements Observer {
	private int myState;

	@Override
	public void update(Subject obj) {
		myState = ((ConcreteSubject) obj).getState();
	}

	public int getMyState() {
		return myState;
	}

	public void setMyState(int myState) {
		this.myState = myState;
	}

}
