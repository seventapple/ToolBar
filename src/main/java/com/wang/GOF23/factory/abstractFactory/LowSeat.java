package com.wang.GOF23.factory.abstractFactory;

public class LowSeat implements Seat {

	@Override
	public void creat() {
		System.out.println("便宜的座椅");
	}

}
