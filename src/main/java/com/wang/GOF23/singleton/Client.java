package com.wang.GOF23.singleton;

public class Client {

	public static void main(String[] args) {
		Demo01 d1 = Demo01.getInstance();
		Demo01 d2 = Demo01.getInstance();
		System.out.println(d1);
		System.out.println(d2);
		System.out.println(d1 == d2);
		Demo02 d3 = Demo02.getInstance();
		Demo02 d4 = Demo02.getInstance();
		System.out.println(d3);
		System.out.println(d4);
		System.out.println(d3 == d4);
		
		Demo04 d5 = Demo04.getInstance();
		Demo04 d6 = Demo04.getInstance();
		System.out.println(d5);
		System.out.println(d6);
		System.out.println(d5 == d6);
		
		Demo05 d7 = Demo05.INSTANCE;
		Demo05 d8 = Demo05.INSTANCE;
		System.out.println(d7==d8);
	}

}
