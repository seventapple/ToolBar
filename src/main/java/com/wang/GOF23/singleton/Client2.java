package com.wang.GOF23.singleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

/**
 * 单例模式防止反射与序列化(无须考虑枚举)
 *
 */
public class Client2 {

	public static void main(String[] args) throws Exception {
		boolean reflact = false;
		// 防反射,在私有构造方法上,对实例进行判断,存在的情况下抛出异常
		System.out.println("reflex");
		boolean reflex=false;
		if(reflex) {
			
		Demo02 d1 = Demo02.getInstance();
		System.out.println(d1);
		Class<?> c = Class.forName("com.wang.GOF23.singleton.Demo02");
		Constructor<?> cons = c.getDeclaredConstructor(null);
		cons.setAccessible(true);
		Demo02 d2 = (Demo02) cons.newInstance();
		Demo02 d3 = (Demo02) cons.newInstance();
		System.out.println(d2);
		System.out.println(d3);
		}
		// 防反序列化 类中添加readResolve方法
		System.out.println("serializable");
		Demo03 d4 = Demo03.getInstance();
		Demo03 d5 = Demo03.getInstance();
		System.out.println(d4);
		System.out.println(d5);
		File f = new File("E:\\TestFile\\obj.txt");
		f.createNewFile();
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("E:\\TestFile\\obj.txt"));
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("E:\\TestFile\\obj.txt"));
		oos.writeObject(d5);
		oos.flush();
		oos.close();
		Demo03 d6 = (Demo03) ois.readObject();
		System.out.println(d6);
	}

}
