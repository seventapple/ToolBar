package com.wang.thread.volatileTest;


/**
 * 并发下变量的不可见性
 * 
 * @author 王李点儿
 *
 */
public class Demo1 {
	public static void main(String[] args) {
		ReadThread t = new ReadThread();
		t.start();
		while (true) {
			if (t.isFlag()) {
				System.out.println("get the flag.");
				break;
			}
		}
	}
}

class ReadThread extends Thread {
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
