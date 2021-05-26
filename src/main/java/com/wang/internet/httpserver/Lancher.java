package com.wang.internet.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket编程:编写服务器(SocketServer) html http协议 服务器编写 反射 XML解析
 */
public class Lancher {

	private ServerSocket server;

	public static void main(String[] args) {
		Lancher server = new Lancher();
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
			// 接收客户端请求信息
			StringBuffer msg = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String temp = null;
			if ((temp = br.readLine()).length()>0) {
				msg.append(temp).append("\r\n");
			}
			System.out.println(msg.toString());
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
