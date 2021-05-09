package com.wang.thread;

/**
 * 生产者消费者模式 信号灯模式
 * 缺点:只能使用一个生产者和一个消费者,关系相对比较死板
 */
public class Demo09 {

	public static void main(String[] args) {
		Movie m = new Movie();
		Consumer c1 = new Consumer(m);
//		Consumer c2 = new Consumer(m);
		Producer p1 = new Producer(m);
//		Producer p2 = new Producer(m);
		new Thread(p1, "Producer 1").start();
//		new Thread(p2, "Producer 2").start();
		new Thread(c1, "Consumer 1").start();
//		new Thread(c2, "Consumer 2").start();
	}

}

class Movie {
	private String movie;
	private boolean flag;

	public synchronized void save(String movie, String name) {
		if (flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(name + " save " + movie);
		this.movie = movie;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.flag = true;
		this.notify();
	}

	public synchronized void play(String name) {
		if (!flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(name + " play " + movie);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.flag = false;
		this.notify();
	}
}

//生产者
class Consumer implements Runnable {
	private Movie movie;

	public Consumer(Movie movie) {
		this.movie = movie;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			movie.save(i + "", Thread.currentThread().getName());
		}
	}

}

//消费者
class Producer implements Runnable {
	private Movie movie;

	public Producer(Movie movie) {
		this.movie = movie;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			movie.play(Thread.currentThread().getName());
		}
	}

}