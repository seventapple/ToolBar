package com.wang.GOF23.factory.simpleFactory;

public class Client {

	public static void main(String[] args) {
		Car c1 = CarFactory.creat("audi");
		c1.run();
		Car c2 = CarFactory.creat("benz");
		c2.run();
		Car c3 = CarFactory.creatAudi();
		c3.run();
		Car c4 = CarFactory.createBenz();
		c4.run();
	}

}
