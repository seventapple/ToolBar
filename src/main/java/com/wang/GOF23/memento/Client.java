package com.wang.GOF23.memento;

public class Client {
	public static void main(String[] args) {
		CareTake care = new CareTake();
		Originator ori = new Originator("wang", 5000);
		care.setMeo(ori.save());
		System.out.println("first:" + ori);
		ori.setSalery(7600);
		System.out.println("second:" + ori);
		ori.recovery(care.getMeo());
		System.out.println("third:" + ori);
	}
}
