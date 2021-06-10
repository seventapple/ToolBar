package com.wang.internet.httpserver;

/**
 * Servlet抽象类
 * 
 * @author 王李点儿
 *
 */
public abstract class Servlet {
	public abstract void service(Request request, Response response);

	public abstract void doGet(Request request, Response response);

	public abstract void doPost(Request request, Response response);
}
