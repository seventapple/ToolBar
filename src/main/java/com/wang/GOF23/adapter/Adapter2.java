package com.wang.GOF23.adapter;

/**
 * 适配器(使用组合的方法,与引用对象建立联系)
 * 
 * @author 王李点儿
 *
 */
public class Adapter2 implements Target {

	private Adaptee adaptee;

	public Adapter2(Adaptee a) {
		adaptee = a;
	}

	@Override
	public void handleReq() {
		adaptee.request();
	}

}
