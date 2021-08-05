package com.wang.GOF23.prototype;

import java.util.Date;

//原型模式
//内容不变,地址改变,变化不影响复制源(考试抄别人,自己再改答案)
//浅克隆(对象元素地址不变)
public class Client {
	public static void main(String[] args) throws CloneNotSupportedException {
		Date date = new Date(100000000000L);
		Sheep s1 = new Sheep("doore", date);
		System.out.println(s1);
		Sheep s2 = (Sheep) s1.clone();
		date.setTime(800000000000L);
		System.out.println(s1);
		System.out.println(s2);
		
	}
}
