package com.wang.GOF23.command;

public class Client {
	public static void main(String[] args) {
		Command com = new RealCommand(new Receiver());
		Invoker invoker =new Invoker(com);
		invoker.call();
		//相较于new Receiver().action(),拓展性更好
	}
}
