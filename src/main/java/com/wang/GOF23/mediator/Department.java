package com.wang.GOF23.mediator;

/**
 * 同事类接口
 * 
 * @author 王李点儿
 *
 */
public interface Department {
	void selfAction();// 本部门业务

	void outAction();// 对外业务请求
}

class Development implements Department {

	private MyMediator m;

	public Development(MyMediator m) {
		super();
		this.m = m;
		m.register("deve", this);
	}

	@Override
	public void selfAction() {
		System.out.println("开发部干活,敲代码");
	}

	@Override
	public void outAction() {
		m.command("money");
	}
}

class Money implements Department {

	private MyMediator m;

	public Money(MyMediator m) {
		super();
		this.m = m;
		m.register("money", this);
	}

	@Override
	public void selfAction() {
		System.out.println("财务部干活,发钱");
	}

	@Override
	public void outAction() {
		m.command("deve");
	}
}