package com.wang.thread.volatileTest;

/**
 * 指令重排序案例
 * 
 * @author 王李点儿
 *
 */
public class Demo5 {
	private volatile static int a, b, i, j = 0;

	public static void main(String[] args) throws Exception {
		int ret = 0;
		boolean c1 = true;
		boolean c2 = true;
		boolean c3 = true;
		boolean c4 = true;
		int cnt = 0;
		while (true) {
			cnt++;
			a = 0;
			b = 0;
			i = 0;
			j = 0;
			Thread t1 = new Thread() {
				@Override
				public void run() {
					a = 1;
					i = b;
				}
			};
			Thread t2 = new Thread() {
				@Override
				public void run() {
					b = 1;
					j = a;
				}
			};
			t1.start();
			t2.start();
			t1.join();
			t2.join();
			if (ret == 4) {
				break;
			}
			if (c1 && i == 1 && j == 1) {
				ret++;
				c1 = false;
				System.out.println("i=" + i + ", j=" + j);
			}
			if (c2 && i == 0 && j == 1) {
				ret++;
				c2 = false;
				System.out.println("i=" + i + ", j=" + j);
			}
			if (c3 && i == 1 && j == 0) {
				ret++;
				c3 = false;
				System.out.println("i=" + i + ", j=" + j);
			}
			if (c4 && i == 0 && j == 0) {
				ret++;
				c4 = false;
				System.out.println("i=" + i + ", j=" + j);
			}
		}
		System.out.println(cnt);
	}
}
