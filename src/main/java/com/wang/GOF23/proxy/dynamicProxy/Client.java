package com.wang.GOF23.proxy.dynamicProxy;

import java.lang.reflect.Proxy;

/**
 * 动态代理
 *
 * 动态生成代理类 生成方法:
 * 1.JDK自带动态代理(java.lang.reflect.Proxy动态生成代理类对象;java.lang.reflect.InvocationHandler处理器接口)
 * 2.javaassist字节码操作库实现 3.CGLIB 4.ASM(底层使用指令,可维护性差)
 *
 */
public class Client {

	public static void main(String[] args) {
		Star realStar = new RealSinger("Jolin");
		StarHandler handler = new StarHandler(realStar);
		Star proxy = (Star) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[] { Star.class },
				handler);
		proxy.sing();
	}

}
