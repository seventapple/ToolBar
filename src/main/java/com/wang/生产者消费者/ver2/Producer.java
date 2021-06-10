package com.wang.生产者消费者.ver2;

import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * 生产者
 */
public class Producer implements Runnable {

	private List<PCData> queue;// 内存缓冲区
	private static final int SLEEPTIME = 1000;
	private static AtomicInteger count = new AtomicInteger();// 总数 原子操作
	private String name;
	private int len;// 队列长度

	public Producer(List<PCData> queue, int length, String nameString) {
		this.queue = queue;
		name = nameString;
		len = length;
	}

	@Override
	public void run() {
		PCData data = null;
		System.out.println("start producting name : " + name);
		try {
			while (true) {
				if (Thread.currentThread().isInterrupted())
					break;
				synchronized (queue) {
					if (queue.size() >= len) {
						System.out.println("queue is FULL, producer is wait - " + name);
						queue.notifyAll();
						queue.wait();
					} else {
						data = new PCData(count.incrementAndGet());
						queue.add(data);
						queue.notify();
						System.out.println(data + " join to queue by " + name);
					}
				}
				Thread.sleep(SLEEPTIME);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
}
