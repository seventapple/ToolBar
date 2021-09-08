package com.wang.GOF23.facade;

public class Client {
	public static void main(String[] args) {
		System.out.println("===外观模式前===");
		Bank bank = new ICBC();
		bank.bussiness();
		Ju ju = new GongShangJu();
		ju.bussiness();
		System.out.println("===外观模式后===");
		Facade.run();
	}
}
