package com.wang.thread.volatileTest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile无法保证原子性,锁可以,原子类也可以
 * 
 * @author 王李点儿
 *
 */
public class Demo4 {

	public static void main(String[] args) {
		// volatile无法保证原子性
//		ReadThread4 t = new ReadThread4();
		// 锁保证原子性
//		ReadThread5 t = new ReadThread5();
		// 原子类保证原子性
		ReadThread6 t = new ReadThread6();
		for (int i = 0; i < 20; i++) {
			new Thread(t, "T" + i).start();
		}
	}
}

class ReadThread4 implements Runnable {
	private volatile int cnt;

	@Override
	public void run() {
		for (int i = 1; i <= 10000; i++) {
			cnt++;
			System.out.println(Thread.currentThread().getName() + " cale : " + cnt);
		}
	}
}

//锁保证原子性
class ReadThread5 implements Runnable {
	private int cnt;

	@Override
	public void run() {
		synchronized (ReadThread5.class) {
			for (int i = 1; i <= 10000; i++) {
				cnt++;
				System.out.println(Thread.currentThread().getName() + " cale : " + cnt);
			}
		}
	}
}

//原子类保证原子性
class ReadThread6 implements Runnable {
	private AtomicInteger cnt = new AtomicInteger(0);

	@Override
	public void run() {
		for (int i = 1; i <= 10000; i++) {
			System.out.println(Thread.currentThread().getName() + " cale : " + cnt.addAndGet(1));
		}
	}
}
