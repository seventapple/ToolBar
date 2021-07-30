package com.wang.GOF23.singleton;

/**
 * 饿汉模式 优点:线程安全,调用效率高 缺点:不能延加载
 * 
 * @author 王李点儿
 *
 */
public class Demo01 {
	private static Demo01 instance = new Demo01();

	private Demo01(){
	};

	public static Demo01 getInstance() {
		return instance;
	}
}
