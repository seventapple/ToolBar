package com.wang.GOF23.observer;

public class Client {
	public static void main(String[] args) {
		// 目标对象
		ConcreteSubject subject = new ConcreteSubject();
		// 观查者
		ObserverA obs1 = new ObserverA();
		ObserverA obs2 = new ObserverA();
		ObserverA obs3 = new ObserverA();

		subject.registerObserver(obs1);
		subject.registerObserver(obs2);
		subject.registerObserver(obs3);

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
