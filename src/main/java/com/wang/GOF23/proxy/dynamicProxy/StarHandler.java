package com.wang.GOF23.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class StarHandler implements InvocationHandler {
	private Star realStar;

	public StarHandler(Star realStar) {
		super();
		this.realStar = realStar;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object object =null;
		
		object = method.invoke(realStar, args);
		System.out.println(method.getName()+" is running.");
		return object;
	}

}
