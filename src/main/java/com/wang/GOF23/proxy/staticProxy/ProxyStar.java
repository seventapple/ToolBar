package com.wang.GOF23.proxy.staticProxy;

public class ProxyStar implements Star {

	private Star star;

	public ProxyStar(Star star) {
		this.star = star;
	}

	@Override
	public void confer() {
		System.out.println("confer");
	}

	@Override
	public void signContract() {
		// TODO Auto-generated method stub
		System.out.println("signContract");
	}

	@Override
	public void bookTicket() {
		// TODO Auto-generated method stub
		System.out.println("bookTicket");
	}

	@Override
	public void sing() {
		// TODO Auto-generated method stub
		star.sing();
	}

	@Override
	public void collectMoney() {
		// TODO Auto-generated method stub
		System.out.println("collectMoney");
	}

}
