package com.wang.classloader;

public class ClassLoadOrder {

	static {
		System.out.println("CL load.");
	}

	public static void main(String[] args) {
		// 类的主动引用(一定发生初始化)
		// 1.new 一个类
		// A a = new A();
		// 2.调用类的静态成员或方法
		// System.out.println(A.size);
		// 3.虚拟机启动,main方法所在的类
		// 4.初始化一个类,若其父类未初始化,优先初始化父类
		// 类的被动引用(不会发生初始化)
		// 1.引用常量
		// System.out.println(A.MAX);
		// 2.通过数组定义类的引用
		// A[] as = new A[1];
		// 3.访问的静态域,只有真正声明这个域的类才会初始化
		System.out.println(B.size);
	}

}

class B extends A {
	static {
		System.out.println("B load.");
	}
}

class A extends A_Father {
	public static int size = 10;
	public final static int MAX = 100;
	static {
		size = 20;
		System.out.println("A load.");
	}

	public A() {
		System.out.println("new A() create.");
	}
}

class A_Father {
	static {
		System.out.println("A_Father load.");
	}
}
