package com.wang.GOF23.singleton;

/**
 * 双重检测锁模式 
 * 优点:线程安全,能延加载 
 * 缺点:编译器优化原因,JVM底层内部模型原因,偶尔会出现问题
 * 
 * @author 王李点儿
 *
 */
public class Demo02 {
	private static Demo02 instance;

	private Demo02() {
		if (instance != null) {
			throw new RuntimeException();
		}
	};

	public static Demo02 getInstance() {
		if (instance == null) {
			Demo02 c;
			synchronized (Demo02.class) {
				c = instance;
				if (c == null) {
					synchronized (Demo02.class) {
						if (c == null) {
							c = new Demo02();
						}
						instance = c;
					}
				}
			}
		}
		return instance;
	}
}
