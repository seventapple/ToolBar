package com.wang.internet.httpserver;

import java.io.IOException;
import java.net.Socket;

import com.wang.io.FileUtil;

//对应一个请求 启动一个线程
public class Dispatcher implements Runnable {

	private Socket client;
	private Request req;
	private Response res;
	private int code = 200;

	public Dispatcher(Socket client) {
		this.client = client;
		try {
			this.req = new Request(this.client.getInputStream());
			this.res = new Response(this.client);
		} catch (IOException e) {
			this.code = 500;
		}
	}

	@Override
	public void run() {
		Servlet servlet = new Servlet();
		servlet.service(req, res);
		try {
			res.pushToClient(code);
		} catch (IOException e) {
			try {
				res.pushToClient(500);
			} catch (IOException e1) {
				;
			}
		}
		FileUtil.close(client);
	}

}
