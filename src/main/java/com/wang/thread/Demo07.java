package com.wang.thread;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Demo07 {

	public static void main(String[] args) {
		// 单例模式(懒汉)线程不安全测试
		// 多线程时可能会触发两次生成实例的现象
//		testSingle1();
		// 单例模式(懒汉)线程安全测试
		// 同步方法
//		testSingle2();
		// ***单例模式(懒汉)线程安全测试***
		// 终版 线程安全,双重检查,性能优化
//		testSingle3();
		// ***饿汉式***
		// 声明私有静态属性,同时创建该对象
		// 属性会直接初始化
		// 优化:方法在使用时才会加载,延缓加载时间
		testSingle4();
	}

	public static void testSingle1() {
//		Single1 s1 = Single1.getInstance(500);
//		Single1 s2 = Single1.getInstance(1000);
//		System.out.println(s1);
//		System.out.println(s2);
		new Thread(new ClassTest(1000, Single1.class)).start();
		new Thread(new ClassTest(1000, Single1.class)).start();
	}

	public static void testSingle2() {
//		Single2 s1 = Single2.getInstance(1000);
//		Single2 s2 = Single2.getInstance(1000);
//		System.out.println(s1);
//		System.out.println(s2);
		new Thread(new ClassTest(1000, Single2.class)).start();
		new Thread(new ClassTest(1000, Single2.class)).start();
	}

	public static void testSingle3() {
//		Single3 s1 = Single3.getInstance(1000);
//		Single3 s2 = Single3.getInstance(1000);
//		System.out.println(s1);
//		System.out.println(s2);
		new Thread(new ClassTest(1000, Single3.class)).start();
		new Thread(new ClassTest(1000, Single3.class)).start();
	}

	public static void testSingle4() {
//		Single4 s1 = Single4.getInstance(1000);
//		Single4 s2 = Single4.getInstance(1000);
//		System.out.println(s1);
//		System.out.println(s2);
		new Thread(new ClassTest(1000, Single4.class)).start();
		new Thread(new ClassTest(1000, Single4.class)).start();
	}
}

class Single4 {
	private static class JVMHolder {
		private static Single4 instance = new Single4();
	}

	private Single4() {
	}

	public static Single4 getInstance(int common) {
		return JVMHolder.instance;
	}
}

//单例模式(懒汉)
//线程安全(优化性能)双重检查
class Single3 {
	private static Single3 sin;

	private Single3() {
	}

	public static Single3 getInstance(int time) {
		if (null == sin) {
			synchronized (Single3.class) {
				if (null == sin) {
					try {
						Thread.sleep(time);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					sin = new Single3();
				}
			}
		}
		return sin;
	}
}

//单例模式(懒汉)
//线程安全(同步方法)
class Single2 {
	private static Single2 sin;

	private Single2() {
	}

	public synchronized static Single2 getInstance(int time) {
		if (null == sin) {
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sin = new Single2();
		}
		return sin;
	}
}

//单例模式(懒汉)
//线程不安全
class Single1 {
	private static Single1 sin;

	private Single1() {
	}

	public static Single1 getInstance() {
		if (null == sin) {
			sin = new Single1();
		}
		return sin;
	}

	public static Single1 getInstance(int time) {
		if (null == sin) {
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sin = new Single1();
		}
		return sin;
	}
}

//线程:按间隔指定时间,指定类的getInstance方法获取类的实例
class ClassTest implements Runnable {

	private int time;
	private Class<?> xClass;

	public ClassTest() {
	}

	public ClassTest(int time, Class xClass) {
		this.time = time;
		this.xClass = xClass;
	}

	@Override
	public void run() {
		try {
//			Thread.sleep(time);
			Method method = xClass.getMethod("getInstance", int.class);
			Object invoke = method.invoke(xClass, time);
			System.out.println(invoke);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}