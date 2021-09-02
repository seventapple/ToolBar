package com.wang.thread.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AllocationDemo {

	// 队列
	private List<Item> queue = new ArrayList<Item>();
	// 获取任务的位置
	private AtomicInteger idx = new AtomicInteger(0);
	// 完成数量
	private AtomicInteger cnt = new AtomicInteger(0);
	// 池
	private List<DealThread> pool = new ArrayList<DealThread>();

	public static void main(String[] args) {
		AllocationDemo demo = new AllocationDemo();
		demo.preDateInit();
		demo.start();
	}

	public void preDateInit() {
		queue.add(new Item(1));
		queue.add(new Item(2));
		queue.add(new Item(3));
		queue.add(new Item(4));
		queue.add(new Item(5));
		queue.add(new Item(6));
		pool.add(new DealThread("A1"));
		pool.add(new DealThread("B2"));
		pool.add(new DealThread("B3"));
		// 设置守护线程(其他线程完毕,自动终了)
		Thread daemon = new Thread() {
			@Override
			public void run() {
				while (true) {
					System.out.println("Daemon run");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		daemon.setDaemon(true);
		daemon.start();
	}

	public synchronized Item getItem() {
		if (isEmpty()) {
			return null;
		}
		Item item = queue.get(idx.get());
		idx.getAndAdd(1);
		return item;
	}

	public boolean isEmpty() {
		return idx.get() >= queue.size();
	}

	public void start() {
		ExecutorService executor = Executors.newFixedThreadPool(3);
		for (DealThread t : pool) {
			executor.submit(t);
		}
		// 监听任务队列完成
		while (true) {
			if (isEmpty()) {
				if (idx.get() == cnt.get()) {
					System.out.println("queue finish.");
					break;
				}
			}
		}
		for (Item it : queue) {
			System.out.println(it);
		}
		//关闭线程
		executor.shutdown();
		for (DealThread t : pool) {
			t.currentInterrupt();
		}
	}

	class DealThread extends Thread {
		private String name;
		private Thread current;
		private boolean isCompleted = false;

		public DealThread(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			current = Thread.currentThread();
			while (!isCompleted) {
				try {
					Item item = getItem();
					if (item == null) {
						System.out.println("get null " + name + " go home.");
						break;
					}
					System.out.println(name + " get a job." + item);
					item.setFlag(true);
					Thread.sleep(item.getSize() * 100);
					cnt.getAndAdd(1);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (isCompleted) {
						System.out.println(name + " sub shutdown.");
						current.interrupt();
					}
				}
			}
		}

		public void currentInterrupt() {
			isCompleted = true;
			if (current != null) {
				if (State.WAITING.equals(current.getState())) {
					System.out.println(name + " shutdown.");
					current.interrupt();
				}
			}
		}

	}

}
