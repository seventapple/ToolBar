package com.wang.thread.volatileTest;

/**
 * 并发下变量的不可见性 解决方案一:加锁
 * 
 * @author 王李点儿
 *
 */
public class Demo2 {
	public static void main(String[] args) {
		ReadThread2 t = new ReadThread2();
		t.start();
		while (true) {
			// 也可以锁类Demo2.class
			synchronized (t) {
				if (t.isFlag()) {
					System.out.println("get the flag.");
					break;
				}
			}
		}
	}
}

class ReadThread2 extends Thread {
	private boolean flag;

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.flag = true;
	}

	public boolean isFlag() {
		return flag;
	}
}
