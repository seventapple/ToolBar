package com.wang.GOF23.factory.factoryMethod;

/**
 * 工厂方法模式 符合OCP开闭原则,结构与代码复杂度提升
 * 
 * @author 王李点儿
 *
 */
public class CarAudiFactory {
	public static Car getCar() {
		return new Audi();
	}
}
