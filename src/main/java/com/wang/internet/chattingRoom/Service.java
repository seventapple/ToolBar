package com.wang.internet.chattingRoom;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

//聊天室
//需求与功能:
//1.服务器负责各个客户端的消息转发
//2.每个客户端有单独的信息发送和接收功能
public class Service {

	public static void main(String[] args) {
		try (// 创建服务器,指定端口(1024以下系统占用)
				ServerSocket server = new ServerSocket(8888);) {// 接收客服端连接 阻塞式
			while (true) {
				Socket accept = server.accept();
//				new Thread(new Send(accept)).start();
//				new Thread(new Receive(accept)).start();
				while (true) {
					OutputStream os = accept.getOutputStream();
					// 4.接收客户端数据
					DataInputStream dis = new DataInputStream(accept.getInputStream());
					String readUTF = dis.readUTF();
					System.out.println("get msg from client : " + readUTF);
					// 3.发送数据to客服端
					DataOutputStream dos = new DataOutputStream(accept.getOutputStream());
					dos.writeUTF("Service->" + readUTF);
					dos.flush();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
