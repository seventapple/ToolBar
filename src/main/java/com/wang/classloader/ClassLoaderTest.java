package com.wang.classloader;

public class ClassLoaderTest {
	public void base() {
		// -Xbootclasspath/a:E:\001Git\BatRun\bat1\lib\bat1.jar
		System.out.println("1.Bootstrap ClassLoader:");
		System.out.println(System.getProperty("sun.boot.class.path"));
		/**
		 * java命令引入jar时可以-cp参数，但-cp只能指定一个固定jar包，不能用通配符(多个jar时要一个个写,不能*.jar)，
		 * 通常情况jar都在同一目录，且多于1个。可以使用-Djava.ext.dirs。
		 * -Djava.ext.dirs=E:\Java\jre1.8.0_25\lib\ext
		 * 可以多个指定(-Djava.ext.dirs="libs\;%JAVA_HOME%\jre\lib\ext;E:\001Git\BatRun\bat1\lib\bat1.jar")
		 */
		System.out.println("2.Extention ClassLoader:");
		System.out.println(System.getProperty("java.ext.dirs"));
		System.out.println("3.AppClassLoader:");
		System.out.println(System.getProperty("java.class.path"));
//		try {
//			Class.forName("com.bat.StartUp");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
		ClassLoader cl = ClassLoaderTest.class.getClassLoader();
		System.out.println("ClassLoader is : " + cl.toString());
		System.out.println("ClassLoader' parent is : " + cl.getParent().toString());
	}

	public static void main(String[] args) {
			new ClassLoaderTest().base();
	}
}
