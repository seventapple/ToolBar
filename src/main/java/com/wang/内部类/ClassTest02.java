package com.wang.内部类;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * 方法内部类 
 * 地位类似于局部变量,不能使用修饰符public,private,protected,static,transient 
 * 只在声明的方法中可见
 * 方法内部类不能访问方法中的局部变量,只能包含非静态成员
 *
 * @author 王李点儿
 *
 */
public class ClassTest02 {
	public static void main(String[] args) {
		new ClassTest02().method();
	}

	public void method() {
		//匿名内部类声明方法一:继承
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("继承");
			}
		}).start();
		//匿名内部类声明方法二:接口式
		new Car() {
			@Override
			public void run() {
				System.out.println("接口式new car run...");
			}
		}.run();
		//匿名内部类声明方法三:参数式
		method(new Car() {
			@Override
			public void run() {
				System.out.println("参数式new car run...");
			}
		});
	}
	//声明方法三:参数式
	public void method(Car c) {
		c.run();
	}
	
}
class Car{
	public void run() {
		System.out.println("run");
	}
}
