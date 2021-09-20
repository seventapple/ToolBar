package com.wang.thread.volatileTest;

/**
 * happens-before规则
 * FEAT:happen-before规则
1.程序顺序规则(单线程规则):同一线程前面所有的写操作,后面操作可见
2.锁规则:线程1解锁a,线程2锁定a,1解锁a前的所有写操作,2可见(1,2可以为同一线程)
3.volatile变量规则:线程1写入volatile变量v,线程2读取v,1及1之前的写操作,2可见(1,2可以为同一线程)
4.传递性:A happens-before B, B happens-before C, 可得到 A happens-before C 
5.start()规则:线程A执行了线程B的start()方法,A对于共享变量的修改截至B启动之前,对B可见
 * 
 * @author 王李点儿
 *
 */
public class Demo6 {
	int a = 1;
	int b = 1;

	private void write() {
		a = 3;
		b = a;
	}

	private void read() {
		System.out.println("a=" + a + " ,b=" + b);
	}

	public static void main(String[] args) {
		while (true) {
			Demo6 d6 = new Demo6();
			new Thread() {
				@Override
				public void run() {
					d6.write();
				}
			}.start();
			new Thread() {
				@Override
				public void run() {
					d6.read();
				}
			}.start();
		}
	}
}
