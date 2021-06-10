package com.wang.生产者消费者.ver3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Lancher {
	public static ReentrantLock lock = new ReentrantLock();
	public static Condition empty = lock.newCondition();
	public static Condition full = lock.newCondition();

	public static void main(String[] args) throws InterruptedException {

		List<PCData> queue = new ArrayList<PCData>();
		Producer p1 = new Producer(queue, 3, "P1");
//		Producer p2 = new Producer(queue, 5, "P2");
//		Producer p3 = new Producer(queue, 5, "P3");
		Consumer c1 = new Consumer(queue, "C1");
		Consumer c2 = new Consumer(queue, "C2");
		Consumer c3 = new Consumer(queue, "C3");
		ExecutorService service = Executors.newCachedThreadPool();
		service.submit(p1);
//		service.execute(p2);
//		service.execute(p3);
		service.submit(c1);
		service.submit(c2);
		service.execute(c3);
	}

}
