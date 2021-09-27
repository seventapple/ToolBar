package com.wang.GOF23.strategy;

//上下文类 管理算法
public class Context {
	private Counting strategy;

	public Context(Counting strategy) {
		this.strategy = strategy;
	}

	public void setStrategy(Counting strategy) {
		this.strategy = strategy;
	}

	public void getPrice(double s) {
		strategy.getPrice(s);
	}
}
