package com.wang.生产者消费者.ver2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lancher {

	public static void main(String[] args) throws InterruptedException {
		List<PCData> queue = new ArrayList<PCData>();
		Producer p1 = new Producer(queue, 5, "P1");
//		Producer p2 = new Producer(queue, 5, "P2");
//		Producer p3 = new Producer(queue, 5, "P3");
//		Producer p2 = new Producer(queue);
		Consumer c1 = new Consumer(queue, "C1");
		Consumer c2 = new Consumer(queue, "C2");
		Consumer c3 = new Consumer(queue, "C3");
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(p1);
//		service.execute(p2);
//		service.execute(p3);
		service.execute(c1);
		service.execute(c2);
		service.execute(c3);
	}

}
