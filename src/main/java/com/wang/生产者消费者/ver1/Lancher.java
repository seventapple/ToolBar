package com.wang.生产者消费者.ver1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class Lancher {

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<PCData> queue = new LinkedBlockingDeque<>(3);
		Producer p1 = new Producer(queue, "P1");
		Producer p2 = new Producer(queue, "P2");
//		Producer p3 = new Producer(queue, "P3");
//		Producer p2 = new Producer(queue);
		Consumer c1 = new Consumer(queue, "C1");
		Consumer c2 = new Consumer(queue, "C2");
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(p1);
		service.execute(p2);
//		service.execute(p3);
		service.execute(c1);
		service.execute(c2);
		Thread.sleep(4000);
		p1.stop();
		p2.stop();
//		p3.stop();
		c1.stop();
		c2.stop();
		Thread.sleep(1500);
		service.shutdownNow();
	}

}
