package com.wang.GOF23.singleton;

/**
 * 枚举实现单例模式 优点:线程安全,能延加载,调用效率高
 * 
 * @author 王李点儿
 *
 */
public enum Demo05 {
	INSTANCE;
	
	public void doSth() {
		System.out.println("do sth.");
	}
}
