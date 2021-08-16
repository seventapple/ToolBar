package com.wang.GOF23.adapter;

/**
 * 客户端(笔记本电脑,只有USB接口)
 * 
 * @author 王李点儿
 *
 */
public class Client {
	public void test1(Target t) {
		t.handleReq();
	}

	public static void main(String[] args) {
		// 方法一
		new Client().test1(new Adapter());
		// 方法二
		new Client().test1(new Adapter2(new Adaptee()));
	}
}
