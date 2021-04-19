package com.wang.thread;

public class Demo01Thread extends Thread {

	public Demo01Thread() {
		super();
	}

	public Demo01Thread(String name) {
		super();
		this.name = name;
	}

	private String name = "";

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println(name+" run No."+i+" step.");
		}
	}

}
