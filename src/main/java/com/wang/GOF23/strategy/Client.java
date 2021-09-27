package com.wang.GOF23.strategy;

public class Client {

	public static void main(String[] args) {
		Counting c1 = new NewCustom();
		Context cnt = new Context(c1);
		cnt.getPrice(100);
		Counting c2=new OldCustom();
		cnt.setStrategy(c2);
		cnt.getPrice(100);
	}

}
