package com.wang.GOF23.factory.factoryMethod;

public class Client {

	public static void main(String[] args) {
		Car c1 = CarAudiFactory.getCar();
		c1.run();
	}

}
