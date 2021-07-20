package com.wang.内部类;

import com.wang.内部类.Demo01.Inner;

public class ClassTest01 {
	public static void main(String[] args) {
		// 不相关的类可以直接调用静态内部类-1
		Demo01.StaticInner.method();
		// 不相关的类可以直接调用静态内部类-2
		Demo01.StaticInner si = new Demo01.StaticInner();
		si.method2();
		// 成员内部类的建立依托于外部类(不相关类中)
		Demo01 demo01 = new Demo01();
		System.out.println(demo01);
		Inner in = demo01.new Inner();
		in.showClass();
	}
}

class Demo01 {
	static int i1 = 11;
	int i2 = 12;
	static {
		String msg = "1,2,3...";
	}

	// 静态内部类
	public static class StaticInner {
		static int i3 = i1;// 可以直接调用外部静态资源
		// int i4 = i2; 不可以调用外部非静态资源

		public static void method() {
			System.out.println("static method : " + i3);
		}

		public void method2() {
			System.out.println("normal method : " + i3);
		}
	}

	// 成员内部类
	public class Inner {
		// static int i5;不能有静态成员,除非声明成final
		// static Date date =new Date();并且只能为编译器可以确定的常量表达式
		// 反证法:static Inner inner =new Inner();通过Demo01.Inner.inner
		// 直接获取对象,脱离外部类制约违背了设计初衷
		// 可以使用外部类全部成员
		public void method() {
			System.out.println("use all field:" + i1 + ", " + i2);
		}

		public void showClass() {
			System.out.println("内部类对象本身:" + this);
			System.out.println("外部类对象本身:" + Demo01.this);
		}
	}

	public void method() {
		// 匿名内部类
		Runnable r = new Runnable() {
			@Override
			public void run() {
			}
		};
		// 方法内部类
		class MethodInner {

		}
	}
}