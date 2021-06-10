package com.wang.生产者消费者.ver1;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * 生产者
 */
public class Producer implements Runnable {

	private boolean isRunning = true;
	private BlockingQueue<PCData> queue;// 内存缓冲区
	private static AtomicInteger count = new AtomicInteger();// 总数 原子操作
	private static final int SLEEPTIME = 1000;
	private String name;

	public Producer(BlockingQueue<PCData> queue, String nameString) {
		this.queue = queue;
		name = nameString;
	}

	@Override
	public void run() {
		PCData data = null;
		System.out.println("start producting name : " + name);
		try {
			while (isRunning) {
				Thread.sleep(SLEEPTIME);
				data = new PCData(count.incrementAndGet());
				System.out.println(data + " join to queue by " + name);
				if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
					System.err.println("join to queue ERROR");
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

	public void stop() {
		isRunning = false;
	}

}
