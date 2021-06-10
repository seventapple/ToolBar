package com.wang.生产者消费者.ver1;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * 消费者
 */
public class Consumer implements Runnable {
	private BlockingQueue<PCData> queue;
	private static final int SLEEPTIME = 1000;
	private String name;
	private boolean isRunning = true;

	public Consumer(BlockingQueue<PCData> queue, String nameString) {
		this.queue = queue;
		name = nameString;
	}

	@Override
	public void run() {
		System.out.println("start Consumer name : " + name);
		try {
			while (isRunning) {
				PCData data = queue.take();
				if (data != null) {
					int re = data.getIntData() * data.getIntData();
					System.out.println(MessageFormat.format("{0} * {1} = {2} , by {3}", data.getIntData(),
							data.getIntData(), re, name));
					Thread.sleep(SLEEPTIME);
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
