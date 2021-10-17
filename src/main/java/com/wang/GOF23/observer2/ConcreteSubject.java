package com.wang.GOF23.observer2;

import java.util.Observable;

//主体(使用现有工具类)
public class ConcreteSubject extends Observable {
	private int state;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;// 目标对象发生改变
		setChanged();// 标识目标对象已经更改
		notifyObservers(state);// 通知所有观察者
	}

}
