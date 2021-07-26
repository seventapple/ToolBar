package com.wang.GOF23.singleton;

/**
 * 静态内部类模式 
 * 优点:线程安全,能延加载,调用效率高
 * 
 * @author 王李点儿
 *
 */
public class Demo04 {
	private static class Demo04Instance {
		private static Demo04 instance = new Demo04();
	}

	private Demo04() {
	};

	public static Demo04 getInstance() {
		return Demo04Instance.instance;
	}

}
