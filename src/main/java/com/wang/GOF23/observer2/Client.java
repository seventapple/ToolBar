package com.wang.GOF23.observer2;

import com.wang.GOF23.observer2.ConcreteSubject;
import com.wang.GOF23.observer2.ObserverA;

public class Client {

	public static void main(String[] args) {
		// 目标对象
		ConcreteSubject subject = new ConcreteSubject();
		// 观查者
		ObserverA obs1 = new ObserverA();
		ObserverA obs2 = new ObserverA();
		ObserverA obs3 = new ObserverA();

		subject.addObserver(obs1);
		subject.addObserver(obs2);
		subject.addObserver(obs3);

		System.out.println("===========before===========");
		System.out.println(obs1.getMyState());
		System.out.println(obs2.getMyState());
		System.out.println(obs3.getMyState());

		subject.setState(100);
		System.out.println("===========after===========");
		System.out.println(obs1.getMyState());
		System.out.println(obs2.getMyState());
		System.out.println(obs3.getMyState());
	}

}
