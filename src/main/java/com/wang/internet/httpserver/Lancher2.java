package com.wang.internet.httpserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Socket编程:编写服务器(SocketServer) html http协议 服务器编写 反射 XML解析
 */
public class Lancher2 {

	private ServerSocket server;

	public static void main(String[] args) {
		Lancher2 server = new Lancher2();
		server.start();
	}

	/**
	 * 启动方法
	 */
	public void start() {
		try {
			server = new ServerSocket(8888);
			this.receive();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 接收客户端
	 */
	private void receive() {
		try {
			Socket client = server.accept();
			byte[] msg = new byte[20480];
			int read = client.getInputStream().read(msg);
			System.out.println(new String(msg, 0, read));

			Response res = new Response(client);
			res.print("<html><head><title>html</title></head>");
			res.println("<body>wang<p>Hello</p></body></html>");
			res.pushToClient(200);
			res.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 停止服务器
	 */
	public void stop() {

	}

}
