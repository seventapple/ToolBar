package com.wang.GOF23.facade;

public class Facade {
	public static void run() {
		Bank bank = new ICBC();
		bank.bussiness();
		Ju ju = new GongShangJu();
		ju.bussiness();
	}
}
