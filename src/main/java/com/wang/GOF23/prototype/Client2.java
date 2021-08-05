package com.wang.GOF23.prototype;

import java.util.Date;

//原型模式
//深克隆(对象元素地址改变,彻底谁也不影响谁)
public class Client2 {
	public static void main(String[] args) throws CloneNotSupportedException {
		Date date = new Date(100000000000L);
		Sheep2 s1 = new Sheep2("doore", date);
		System.out.println(s1);
		Sheep2 s2 = (Sheep2) s1.clone();
		date.setTime(800000000000L);
		System.out.println(s1);
		System.out.println(s2);

	}
}
