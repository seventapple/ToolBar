package com.wang.生产者消费者.ver3;

import java.text.MessageFormat;
import java.util.List;


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
				Lancher.lock.lock();
				if (queue.size() == 0) {
					System.out.println("queue is ZERO, consumer is wait - " + name);
					Lancher.full.signalAll();
					Lancher.empty.await();
				}else {
				Thread.sleep(SLEEPTIME);
				data = queue.remove(0);
				Lancher.full.signal();
				Lancher.lock.unlock();
				int re = data.getIntData() * data.getIntData();
				System.out.println(MessageFormat.format("{0} * {1} = {2} , by {3}", data.getIntData(),
						data.getIntData(), re, name));
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
}
