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
public class Server1 {

	private ServerSocket server;
	private static String CRLF = "\r\n";
	private static String BLANK = " ";

	public static void main(String[] args) {
		Server1 server = new Server1();
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
			// HTTP协议请求分3部分
			// -请求方式 URI HTTP协议/版本
			// -请求头
			// 必须存在CRLF符号行\r\n
			// -请求正文(post)
//			StringBuffer msg = new StringBuffer();
//			BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
//			String temp = null;
//			if ((temp = br.readLine())!=null) {
//				msg.append(temp).append("\r\n");
//			}
//			System.out.println(msg.toString().trim());
			byte[] msg = new byte[20480];
			int read = client.getInputStream().read(msg);
			System.out.println(new String(msg, 0, read));

			// 响应
			// 网页内容
			StringBuffer responseContent = new StringBuffer(
					"<html><head><title>html</title></head><body>正文信息<p>Hello</p></body></html>");
			StringBuffer response = new StringBuffer();
			// HTTP协议/版本 状态代码 描述
			response.append("HTTP/1.1").append(BLANK).append(200).append(BLANK).append("ok").append(CRLF);
			// 响应头
			response.append("Date:").append(new Date()).append(CRLF);
			response.append("Contest-type:text/html;charset=GBK").append(CRLF);
			response.append("Content-Length:").append(responseContent.toString().length()).append(CRLF);
			// 必须存在CRLF符号行\r\n
			response.append(CRLF);
			// 响应正文
			response.append(responseContent);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8"));
			bw.write(response.toString());
			bw.flush();
			bw.close();
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
