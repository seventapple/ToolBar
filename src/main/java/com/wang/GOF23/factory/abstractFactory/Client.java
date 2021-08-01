package com.wang.GOF23.factory.abstractFactory;

public class Client {

	public static void main(String[] args) {
		CarFactory cf = new LowCarFactory();
		Engine e = cf.createEngine();
		e.create();
		Seat s = cf.createSeat();
		s.creat();
		CarFactory cf2 = new LuxuryCarFactory();

		Engine e2 = cf2.createEngine();
		e2.create();
		Seat s2 = cf2.createSeat();
		s2.creat();
	}

}
