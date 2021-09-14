package com.wang.thread.volatileTest;

/**
 * 并发下变量的不可见性 解决方案二:volatile修饰变量
 * 
 * @author 王李点儿
 *
 */
public class Demo3 {

	public static void main(String[] args) {
		ReadThread3 t = new ReadThread3();
		t.start();
		while (true) {
			if (t.isFlag()) {
				System.out.println("get the flag.");
				break;
			}
		}
	}
}

class ReadThread3 extends Thread {
	private volatile boolean flag;

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
