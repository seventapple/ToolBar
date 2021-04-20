package com.wang.thread;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.sun.org.apache.bcel.internal.generic.NEW;

//线程阻塞
//join 合并线程, 让调用的线程以外的线程阻塞
//yield 暂停线程,暂停当前正在执行的线程(静态方法),写在哪个线程体里,就暂停谁
//sleep 休眠,暂停当前线程,不释放锁(排他锁),
public class Demo04 {

	public static void main(String[] args) throws InterruptedException {
		JoinTest demo = new JoinTest();
		Thread t1 = new Thread(demo);
		t1.start();
		System.out.println("===============test join=================================");
		for (int i = 0; i < 10; i++) {
			Thread.sleep(100);
			if (i == 2) {
				t1.join();
			}
			System.out.println("main--->" + i);
		}
		System.out.println("===============test yield=================================");
		YieldTest demo2 = new YieldTest();
		Thread t2 = new Thread(demo2);
		t2.start();
		for (int i = 0; i < 10; i++) {
			Thread.sleep(100);
			if (i % 2 == 0) {
				// 暂停主线程main(是否会使其他线程执行,看CPU调度)
				Thread.yield();
			}
			System.out.println("main--->" + i);
		}
		System.out.println("===============test sleep=================================");
		Date date = new Date(System.currentTimeMillis() + 10 * 1000);
		long mark = date.getTime();
		while (true) {
			System.out.println(new SimpleDateFormat("HH:mm:ss").format(date));
			Thread.sleep(1000);
			date = new Date(date.getTime() - 1000);
			if (mark - 10000 > date.getTime()) {
				break;
			}
		}
	}

}

class JoinTest implements Runnable {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(100);
				System.out.println("thread--->" + i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class YieldTest implements Runnable {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(100);
				System.out.println("thread--->" + i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
