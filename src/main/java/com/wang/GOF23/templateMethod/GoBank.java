package com.wang.GOF23.templateMethod;

public abstract class GoBank {
	public void start() {
		System.out.println("start");
	};

	public abstract void doSth();

	public void finish() {
		System.out.println("finish");
	};

	public final void process() {
		start();
		doSth();
		finish();
	}
}

class BrowMoney extends GoBank {

	@Override
	public void doSth() {
		System.out.println("brow money");
	}

}
