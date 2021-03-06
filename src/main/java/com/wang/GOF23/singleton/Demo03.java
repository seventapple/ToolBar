package com.wang.GOF23.singleton;

import java.io.Serializable;

/**
 * 懒汉模式 优点:线程安全,能延加载 缺点:调用效率不高
 * 
 * @author 王李点儿
 *
 */
public class Demo03 implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Demo03 instance;

	private Demo03() {
	};

	public Object readResolve() {
		return instance;
	}

	public static synchronized Demo03 getInstance() {
		if (instance == null) {
			instance = new Demo03();
		}
		return instance;
	}
}
