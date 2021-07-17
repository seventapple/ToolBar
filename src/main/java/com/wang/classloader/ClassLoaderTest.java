package com.wang.classloader;

public class ClassLoaderTest {
	public void base() {
		System.out.println(System.getProperty("sun.boot.class.path"));
		System.out.println(System.getProperty("java.ext.dirs"));
		System.out.println(System.getProperty("java.class.path"));
		ClassLoader cl = ClassLoaderTest.class.getClassLoader();
		System.out.println("ClassLoader is : " + cl.toString());
		System.out.println("ClassLoader' parent is : " + cl.getParent().toString());
	}
}
