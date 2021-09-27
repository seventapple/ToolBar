package com.wang.GOF23.templateMethod;

public class Client {

	public static void main(String[] args) {
		GoBank d1 = new BrowMoney();
		d1.process();

		new GoBank() {
			@Override
			public void doSth() {
				System.out.println("打打打打劫");
			}
		}.process();
	}

}
