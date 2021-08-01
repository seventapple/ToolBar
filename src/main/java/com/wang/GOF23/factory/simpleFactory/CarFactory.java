package com.wang.GOF23.factory.simpleFactory;

/**
 * 简单工厂模式 
 * 违反OCP开闭原则,新增类时需要改动即存代码
 * 
 * @author 王李点儿
 *
 */
public class CarFactory {
	//方法一
	public static Car creat(String name) {
		if ("audi".equalsIgnoreCase(name)) {
			return new Audi();
		}
		if ("benz".equalsIgnoreCase(name)) {
			return new Benz();
		}
		return null;
	}
	//方法二
	public static Car creatAudi() {
		return new Audi();
	}
	public static Car createBenz() {
		return new Benz();
	}
}
