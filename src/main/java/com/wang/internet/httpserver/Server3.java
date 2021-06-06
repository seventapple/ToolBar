package com.wang.internet.httpserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLEncoder;
import java.util.Date;

import com.wang.io.FileUtil;

/**
 * Socket编程:编写服务器(SocketServer) html http协议 服务器编写 反射 XML解析
 */
public class Server3 {

	private ServerSocket server;
	private boolean isShutdown = false;

	public static void main(String[] args) {
		Server3 server = new Server3();
		server.start(8888);
	}

	/**
	 * 启动方法
	 */
	public void start(int port) {
		try {
			server = new ServerSocket(port);
			this.receive();
		} catch (IOException e) {
//			e.printStackTrace();
			stop();
		}
	}

	/**
	 * 接收客户端
	 */
	private void receive() {
		try {
//			while (!isShutdown) {
			new Thread(new Dispatcher(server.accept())).start();
//			}
		} catch (IOException e) {
			stop();
		}
	}

	/**
	 * 停止服务器
	 */
	public void stop() {
		isShutdown = true;
		FileUtil.close(server);
	}

}
