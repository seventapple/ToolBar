package com.wang.GOF23.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

//原型模式
//深克隆(对象元素地址改变,彻底谁也不影响谁)
//使用原型模式优点:省去了对象实例的时间(大量且重复的情况下,尤其明显)
public class Client2 {
	public static void main(String[] args) throws Exception {
		createObjBySerial();
	}

	public static void createObjBySerial() throws Exception {
		Date date = new Date(100000000000L);
		Sheep2 s1 = new Sheep2("doore", date);
		System.out.println(s1);

		// 序列化-object to bytes
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(s1);
		byte[] data = bos.toByteArray();
		// 反序列化-获取实例
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		ObjectInputStream ois = new ObjectInputStream(bis);
		Sheep2 s2 = (Sheep2) ois.readObject();

		date.setTime(800000000000L);
		System.out.println(s1);
		System.out.println(s2);
	}
}
