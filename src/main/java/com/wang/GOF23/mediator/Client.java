package com.wang.GOF23.mediator;

public class Client {

	public static void main(String[] args) {
		MyMediator mediator = new Present();
		Department d1 = new Development(mediator);
		Department d2 = new Money(mediator);
		d1.selfAction();
		d1.outAction();
	}

}
