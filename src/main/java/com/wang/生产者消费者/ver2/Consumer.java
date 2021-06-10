package com.wang.生产者消费者.ver2;

import java.text.MessageFormat;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * 消费者
 */
public class Consumer implements Runnable {
	private List<PCData> queue;
	private String name;
	private static final int SLEEPTIME = 1000;

	public Consumer(List<PCData> queue, String nameString) {
		this.queue = queue;
		name = nameString;
	}

	@Override
	public void run() {
		System.out.println("start Consumer name : " + name);
		try {
			while (true) {
				if (Thread.currentThread().isInterrupted())
					break;
				PCData data = null;
				synchronized (queue) {
					if (queue.size() == 0) {
						System.out.println("queue is ZERO, consumer is wait - " + name);
						queue.wait();
						queue.notifyAll();
					} else {
						data = queue.remove(0);
						int re = data.getIntData() * data.getIntData();
						System.out.println(MessageFormat.format("{0} * {1} = {2} , by {3}", data.getIntData(),
								data.getIntData(), re, name));
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
