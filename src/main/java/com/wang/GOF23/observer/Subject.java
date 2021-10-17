package com.wang.GOF23.observer;

import java.util.ArrayList;
import java.util.List;

//主体
public class Subject {
	private List<Observer> list = new ArrayList<Observer>();

	public void registerObserver(Observer obj) {
		list.add(obj);
	}

	public void removeObserver(Observer obj) {
		list.remove(obj);
	}

	// 通知所有的观查者更新状态
	public void notifyObserver() {
		for (Observer obj : list) {
			obj.update(this);
		}
	}
}
