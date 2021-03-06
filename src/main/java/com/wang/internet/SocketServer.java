package com.wang.internet;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.org.apache.bcel.internal.generic.NEW;

//Socket通信 基于tcp 面向连接 效率相对低
public class SocketServer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		start1();
	}

	public static void start1() throws IOException {
		System.out.println("服务器启动.");

		try (// 创建服务器,指定端口(1024以下系统占用)
				ServerSocket server = new ServerSocket(8888);) {// 接收客服端连接 阻塞式
			Socket accept = server.accept();
			System.out.println("服务器接收到请求.");
			OutputStream os = accept.getOutputStream();
			// 3.发送数据to客服端
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			// 需要有行结束符/r/n 或者 添加bw.newLine();
//			bw.write("get connection\r\n");
			bw.write("get connection");
			bw.newLine();
			bw.flush();
			//4.接收客户端数据
			DataInputStream dis=new DataInputStream(accept.getInputStream());
			String readUTF = dis.readUTF();
			System.out.println("get msg from client : "+readUTF);
		}
	}

}
