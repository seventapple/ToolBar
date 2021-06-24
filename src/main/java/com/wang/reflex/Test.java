package com.wang.reflex;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.sun.xml.internal.ws.util.StringUtils;

public class Test {

	public static void main(String[] args) {
//		test1();
//		test2();
//		test3();
//		test4();
	}

	/*
	 * 测试反射效率性
	 */
	public static void test4() {
		User u1 = new User();
		long t1 = getTime();
		for (int i = 0; i < 1000000000; i++) {
			u1.getAge();
		}
		long t2 = getTime();
		System.out.println("normal method call : " + (t2 - t1) + " ms");
		try {
			String path = "com.wang.reflex.User";
			Class clazz = Class.forName(path);
			Constructor con = clazz.getDeclaredConstructor(null);
			User u2 = (User) con.newInstance(null);
			Method mth1 = clazz.getDeclaredMethod("getAge", null);
//			mth1.setAccessible(true);
			long t3 = getTime();
			for (int i = 0; i < 1000000000; i++) {
				mth1.invoke(u2, null);
			}
			long t4 = getTime();
			System.out.println("reflex method call : " + (t4 - t3) + " ms");
			Method mth2 = clazz.getDeclaredMethod("getAge", null);
			mth2.setAccessible(true);
			long t5 = getTime();
			for (int i = 0; i < 1000000000; i++) {
				mth2.invoke(u2, null);
			}
			long t6 = getTime();
			System.out.println("reflex(no check) method call : " + (t6 - t5) + " ms");
		} catch (Exception e) {
		}

	}

	public static long getTime() {
		return System.currentTimeMillis();
	}

	/*
	 * 通过反射获取实例,调用方法,设置字段(设置跳过Java检查机制)
	 * 
	 */
	public static void test3() {
		String path = "com.wang.reflex.User";
		try {
			System.out.println("获取实例");
			// 获取实例1-有参,private
			Class clazz = Class.forName(path);
			Constructor con = clazz.getDeclaredConstructor(int.class, String.class);
			// 由于private, 需要设置跳过Java检查
			con.setAccessible(true);
			User user1 = (User) con.newInstance(3, "user1");
			System.out.println(user1);
			// 获取实例2-无参,public
			Class<User> clazz2 = (Class<User>) Class.forName(path);
			User user2 = clazz2.newInstance();
			System.out.println(user2);
			// 获取实例3-无参,public
			Constructor con3 = clazz.getDeclaredConstructor(null);
			User user3 = (User) con3.newInstance();
			System.out.println(user3);
			System.out.println("调用方法private");
			Method mth1 = clazz.getDeclaredMethod("getMsg", String.class, int.class);
			mth1.setAccessible(true);
			String result1 = (String) mth1.invoke(user1, "say hello ", 3000);
			System.out.println(result1);
			// 字段
			System.out.println("设置字段private");
			Field field1 = clazz.getDeclaredField("age");
			field1.setAccessible(true);
			field1.set(user1, 7);
			System.out.println(user1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 通过反射获取构造方法,方法,字段
	 */
	public static void test2() {
		try {
			String path = "com.wang.reflex.User";
			Class clazz = Class.forName(path);
			// 构造方法
			System.out.println("获取全部public的构造方法");
			Constructor[] cons1 = clazz.getConstructors();
			for (Constructor c : cons1) {
				System.out.println(c);
			}
			System.out.println("获取全部的构造方法");
			Constructor[] cons2 = clazz.getDeclaredConstructors();
			for (Constructor c : cons2) {
				System.out.println(c);
			}
			// 方法
			System.out.println("获取全部的方法");
			Method[] mths = clazz.getDeclaredMethods();
			for (Method method : mths) {
				System.out.println(method);
			}
			System.out.println("获取指定的方法");
			Method mth1 = clazz.getDeclaredMethod("getMsg", String.class, int.class);
			System.out.println(mth1);
			// 字段
			System.out.println("获取全部的字段");
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				System.out.println(field);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 获取Class的三种方法
	 */
	public static void test1() {
		try {
			String path = "com.wang.reflex.User";
			// 1
			Class clazz1 = Class.forName(path);
			System.out.println(clazz1);
			System.out.println(clazz1.getSimpleName());// 只获取类名
			// 2
			User user = new User();
			Class clazz2 = user.getClass();
			System.out.println(clazz2);
			// 3
			Class clazz3 = User.class;
			System.out.println(clazz3);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
