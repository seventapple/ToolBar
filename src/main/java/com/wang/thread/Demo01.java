package com.wang.thread;

public class Demo01 {

	public static void main(String[] args) {
		Demo01Thread dt = new Demo01Thread("rabbit");
		StaticRunnable sr = new StaticRunnable("turtle");
		Thread dt2 = new Thread(sr);
		dt.start();
		dt2.start();
		System.out.println("finish.");
	}

}

//静态代理模式
//1.类实现Runnable接口, 重写run()
//2.创建真实角色
//3.创建代理角色+真实角色引用
//4.start()启动线程
class StaticRunnable implements Runnable {

	public StaticRunnable() {
		super();
	}

	public StaticRunnable(String name) {
		super();
		this.name = name;
	}

	private String name = "";

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println(name + " run No." + i + " step.");
		}
	}

}
