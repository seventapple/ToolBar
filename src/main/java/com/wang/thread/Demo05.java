package com.wang.thread;

//线程基本信息
public class Demo05 {

	public static void main(String[] args) throws InterruptedException {
		MessTest mt1 = new MessTest();
		MessTest mt2 = new MessTest();
		Thread t1 = new Thread(mt1, "t1");
		Thread t2 = new Thread(mt2);
		System.out.println(t1.getName());
		System.out.println(t2.getName());
		// setName
		t1.setName("N1");
		t2.setName("N2");
		// getName
		System.out.println(t1.getName());
		System.out.println(t2.getName());
		System.out.println("====status====");
		System.out.println(t1.getState());
		System.out.println(t1.isAlive());
		// setPriority 设置优先级
		t1.setPriority(1);
		t2.setPriority(10);
		t1.start();
		t2.start();
		Thread.sleep(2000);
		mt1.stop();
		mt2.stop();
		System.out.println(t1.getState()+""+t1.isAlive());
		System.out.println(t2.getState()+""+t2.isAlive());
		Thread.sleep(2000);
		System.out.println(t1.getState()+""+t1.isAlive());
		System.out.println(t2.getState()+""+t2.isAlive());
	}

}

class MessTest implements Runnable {

	private boolean flag = true;

	int cnt = 0;

	@Override
	public void run() {
		while (flag) {
			try {
				Thread.sleep(50);
				// Thread.currentThread() 获取当前线程,在哪个线程体,取得当前线程
				System.out.println(Thread.currentThread().getName() + "-->" + (cnt++));
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	public void stop() {
		this.flag = false;
	}
}