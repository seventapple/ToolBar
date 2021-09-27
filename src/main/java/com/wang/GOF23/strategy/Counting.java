package com.wang.GOF23.strategy;

public interface Counting {
	public void getPrice(double money);
}

class NewCustom implements Counting {
	@Override
	public void getPrice(double money) {
		System.out.println("you get money : " + money * 0.9);
	}
}

class OldCustom implements Counting {
	@Override
	public void getPrice(double money) {
		System.out.println("you get money : " + money * 0.8);
	}
}
