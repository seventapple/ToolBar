package com.wang.GOF23.proxy.dynamicProxy;

public class RealSinger implements Star {

	private String name;

	public RealSinger(String name) {
		this.name = name;
	}

	@Override
	public void confer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void signContract() {
		// TODO Auto-generated method stub

	}

	@Override
	public void bookTicket() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sing() {
		System.out.println(name + " sing a song.");
	}

	@Override
	public void collectMoney() {
		// TODO Auto-generated method stub
		System.out.println(name + " collectMoney.");
	}

}
