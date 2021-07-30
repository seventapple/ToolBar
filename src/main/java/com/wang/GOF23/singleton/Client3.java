package com.wang.GOF23.singleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.util.concurrent.CountDownLatch;

/**
 * 单例模式效率性
 *
 */
public class Client3 {

	private static int threadCnt = 10;
	private static int num = 1000000;

	public static void main(String[] args) throws Exception {
		test1();
		test2();
		test3();
		test4();
		test5();
	}

	public static void test1() throws Exception {
		long start = System.currentTimeMillis();
		final CountDownLatch cdLatch = new CountDownLatch(threadCnt);
		for (int i = 0; i < threadCnt; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < num; j++) {
						Demo01 d = Demo01.getInstance();
					}
					cdLatch.countDown();
				}
			}.start();
		}
		cdLatch.await();
		long end = System.currentTimeMillis();
		System.out.println("饿汉模式 run time : " + (end - start) + "ms");
	}

	public static void test2() throws Exception {
		long start = System.currentTimeMillis();
		final CountDownLatch cdLatch = new CountDownLatch(threadCnt);
		for (int i = 0; i < threadCnt; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < num; j++) {
						Demo02 d = Demo02.getInstance();
					}
					cdLatch.countDown();
				}
			}.start();
		}
		cdLatch.await();
		long end = System.currentTimeMillis();
		System.out.println("双重检测锁模式 run time : " + (end - start) + "ms");
	}

	public static void test3() throws Exception {
		long start = System.currentTimeMillis();
		final CountDownLatch cdLatch = new CountDownLatch(threadCnt);
		for (int i = 0; i < threadCnt; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < num; j++) {
						Demo03 d = Demo03.getInstance();
					}
					cdLatch.countDown();
				}
			}.start();
		}
		cdLatch.await();
		long end = System.currentTimeMillis();
		System.out.println("懒汉模式 run time : " + (end - start) + "ms");
	}

	public static void test4() throws Exception {
		long start = System.currentTimeMillis();
		final CountDownLatch cdLatch = new CountDownLatch(threadCnt);
		for (int i = 0; i < threadCnt; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < num; j++) {
						Demo04 d = Demo04.getInstance();
					}
					cdLatch.countDown();
				}
			}.start();
		}
		cdLatch.await();
		long end = System.currentTimeMillis();
		System.out.println("静态内部类模式 run time : " + (end - start) + "ms");
	}

	public static void test5() throws Exception {
		long start = System.currentTimeMillis();
		final CountDownLatch cdLatch = new CountDownLatch(threadCnt);
		for (int i = 0; i < threadCnt; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < num; j++) {
						Demo05 d = Demo05.INSTANCE;
					}
					cdLatch.countDown();
				}
			}.start();
		}
		cdLatch.await();
		long end = System.currentTimeMillis();
		System.out.println("枚举模式 run time : " + (end - start) + "ms");
	}

}
