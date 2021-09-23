package com.wang.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunFileLoader {

	public static void main(String[] args) throws Exception {
		FileClassLoader fcl = new FileClassLoader("E:/TestFile/class");
		System.out.println("===读取class文件,调用其方法say()===");
		Class<?> loadClass = fcl.loadClass("com.wang.cl.Hello");
		Object obj = loadClass.newInstance();
		Method method = loadClass.getDeclaredMethod("say", null);
		method.invoke(obj, null);
		System.out.println("===读取对象比较,使用一个CL类(单例实现)===");
		Class<?> c = fcl.findClass("com.wang.cl.Hello");
		Class<?> c2 = fcl.findClass("com.wang.cl.Hello");
		System.out.println(c.hashCode());
		System.out.println(c2.hashCode());
		FileClassLoader fcl2 = new FileClassLoader("E:/TestFile/class");
		Class<?> c3 = fcl2.findClass("com.wang.cl.Hello");
		Class<?> c4 = fcl2.findClass("java.lang.String");
		System.out.println("===读取对象比较,两个CL类===");
		System.out.println(c3.hashCode());
		// 查看类由谁加载
		System.out.println("===查看类===");
		System.out.println(c3.getClassLoader());// 自定义
		System.out.println(c4.getClassLoader());// 引导BootstrapClassLoader
		// 直接加载加密的类
		System.out.println("===直接读取加密类===");
		try {
			Class<?> c5 = fcl.findClass("com.wang.cl.HelloEncrpy");
			System.out.println(c5);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		// 对加密的类使用自定义类加载器加载
		DecrpyClassLoader dLoader = new DecrpyClassLoader("E:/TestFile/class");
		System.out.println("===读取加密类===");
		Class<?> dc = dLoader.findClass("com.wang.cl.HelloEncrpy");
		System.out.println(dc);
		// 线程上下文类加载器
		System.out.println("===线程上下文类加载器===");
		System.out.println("current thread cl : " + Thread.currentThread().getContextClassLoader());
		// 设置线程的类加载器(通过设置自定义加载器,可抛弃双亲委派加载链模式)
		System.out.println("===设置线程的类加载器===");
		Thread.currentThread().setContextClassLoader(dLoader);
		System.out.println("update thread cl : " + Thread.currentThread().getContextClassLoader());
		Class<?> c6 = Thread.currentThread().getContextClassLoader().loadClass("com.wang.cl.HelloEncrpy");
		System.out.println(c6.getClassLoader());
		// 由于DecrpyClassLoader中未舍弃双亲委派,所以实际会往上依赖,找到指定类,这时找到类的类加载器为实际找到类的加载器
		Class<?> c7 = Thread.currentThread().getContextClassLoader().loadClass("com.wang.classloader.EncrpyUtil");
		System.out.println(c7.getClassLoader());
	}

}
