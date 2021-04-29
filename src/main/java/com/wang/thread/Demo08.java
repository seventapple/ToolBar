package com.wang.thread;

//死锁
//多线程多次调用synchronized资源时 易发生死锁
public class Demo08 {

	public static void main(String[] args) {
		Object goods = new Object();
		Object money = new Object();
		Buyer buy = new Buyer(goods, money);
		Shoper shop = new Shoper(goods, money);
		new Thread(buy).start();
		new Thread(shop).start();
	}

}

class Buyer implements Runnable {
	Object goods;
	Object money;

	public Buyer(Object goods, Object money) {
		super();
		this.goods = goods;
		this.money = money;
	}

	@Override
	public void run() {
		synchronized (money) {
			System.out.println("Buyer ready for the money");
			try {
				Thread.sleep(1000);
				synchronized (goods) {
					System.out.println("Buyer ready for the goods");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("business is done.");
	}
}

class Shoper implements Runnable {
	Object goods;
	Object money;

	public Shoper(Object goods, Object money) {
		super();
		this.goods = goods;
		this.money = money;
	}

	@Override
	public void run() {
		synchronized (goods) {
			System.out.println("Shoper ready for the goods");
			try {
				Thread.sleep(1000);
				synchronized (money) {
					System.out.println("Shoper ready for the money");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("business is done.");
	}
}