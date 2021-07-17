package com.wang.classloader;

public class RunFileLoader {

	public static void main(String[] args) throws ClassNotFoundException {
		FileClassLoader fcl = new FileClassLoader("E:/TestFile/class");
		Class<?> c = fcl.findClass("com.wang.cl.Hello");
		Class<?> c2 = fcl.findClass("com.wang.cl.Hello");
		System.out.println(c.hashCode());
		System.out.println(c2.hashCode());
		FileClassLoader fcl2 = new FileClassLoader("E:/TestFile/class");
		Class<?> c3 = fcl2.findClass("com.wang.cl.Hello");
		Class<?> c4 = fcl2.findClass("java.lang.String");
		System.out.println(c3.hashCode());
		// 查看类由谁加载
		System.out.println(c3.getClassLoader());// 自定义
		System.out.println(c4.getClassLoader());// 引导BootstrapClassLoader
	}

}
