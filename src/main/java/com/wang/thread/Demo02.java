package com.wang.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Demo02 {

	public static void main(String[] args) {
		ExecutorService ser = Executors.newFixedThreadPool(1);
		Race rabbit = new Race(1);
		Future<Integer> result = ser.submit(rabbit);
		try {
			Integer intRes = result.get();
			System.out.println("get Result : " + intRes);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		//停止服务
		ser.shutdownNow();
		System.out.println("shutdown");
	}

}

//Callable接口实现多线程
//有返回值
//规定重写方法call
//1.创建Callable接口,重写call()
//2.执行ExecutorService,获取Future对象
class Race implements Callable<Integer> {
	public Race() {

	}

	int step;

	public Race(int step) {
		super();
		this.step = step;
	}

	@Override
	public Integer call() throws Exception {
		return step;
	}

}
