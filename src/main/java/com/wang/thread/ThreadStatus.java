package com.wang.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadStatus {

	public static void main(String[] args) {
		ThreadStatus demo = new ThreadStatus();
		// sub thread sleep, sub thread throw InterruptedException
		demo.testThrow();
	}

	private BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

	private String lock = "lock";

	private void testThrow() {
		ThrowExe thrExe = new ThrowExe();
		thrExe.start();
		ThrowExe thrExe2 = new ThrowExe();
		thrExe2.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thrExe2.close();
	}

	class ThrowExe extends Thread {

		@Override
		public void run() {
			try {
				// sleep : InterruptedException
				// this.sleep(5000);
				// wait : IllegalMonitorStateException
				// this.wait(2000);
				// queue.take() : InterruptedException
				// queue.take();
				synchronized (lock) {
					System.out.println("test lock");
					this.sleep(10000);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("error");
				return;
			}
			System.out.println("end");
		}

		void close() {
			super.interrupt();
		}

	}
}
