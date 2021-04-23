package com.wang.thread;

//线程同步与锁定
//synchronize(引用类型|类.class|this)
public class Demo06 {

	public static void main(String[] args) {
		SafeTest demo = new SafeTest();
		Thread t1 = new Thread(demo, "学生");
		Thread t2 = new Thread(demo, "黄牛一");
		Thread t3 = new Thread(demo, "黄牛二");
		t1.start();
		t2.start();
		t3.start();
		System.out.println("main over");
	}

	public void demo1() {
		// 线程安全
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("safe");
		// 线程不安全
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("safe");
	}

}

class SafeTest implements Runnable {
	private int cnt = 100;

	private boolean flag = true;

	@Override
	public void run() {
		while (flag) {
			// 错误示范
//			notSafe();
//			errByRange();
//			errByObj();
			// 正确引用
//			lockMethod();
			lockThis();
		}
	}

	// 线程不安全,无法保证线程并发,正确按顺序执行
	public void notSafe() {
		if (cnt <= 0) {
			flag = false;
			return;
		}
		try {
			Thread.sleep(50);
			System.out.println(Thread.currentThread().getName() + " get ticket, left " + --cnt);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 线程安全
	// 锁定方法
	public synchronized void lockMethod() {
		if (cnt <= 0) {
			flag = false;
			return;
		}
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " get ticket, left " + --cnt);
	}

	// 线程安全
	// 锁定对象
	public void lockThis() {
		synchronized (this) {
			if (cnt <= 0) {
				flag = false;
				return;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " get ticket, left " + --cnt);
		}
	}

	// 线程不安全
	// 锁定对象 范围过小
	public void errByRange() {
		synchronized (this) {
			if (cnt <= 0) {
				flag = false;
				return;
			}
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " get ticket, left " + --cnt);
	}

	// 线程不安全
	// 锁定对象不够全
	public void errByObj() {
		synchronized ((Integer) cnt) {
			if (cnt <= 0) {
				flag = false;
				return;
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " get ticket, left " + --cnt);
		}
	}

}