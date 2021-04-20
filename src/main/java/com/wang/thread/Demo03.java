package com.wang.thread;

//通过外部干涉停止线程
public class Demo03 {

	public static void main(String[] args) {
		StopTest demo = new StopTest();
		Thread t = new Thread(demo);
		t.start();
		for (int i = 0; i < 100; i++) {
			if (i == 50) {
				// 4.一定条件下,外部调用
				demo.stop();
			}
			System.out.println("main run..." + i);
		}
	}

}

class StopTest implements Runnable {

	// 1.线程中定义标记
	private boolean flg;

	@Override
	public void run() {
		// 2.线程体中使用标记
		while (!flg) {
			System.out.println("thread run...");
		}
	}

	// 3.对外提供方法改变标识
	public void stop() {
		this.flg = true;
	}
}