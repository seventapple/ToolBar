package com.wang.internet.chattingRoom;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.sun.istack.internal.Pool;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.wang.io.FileUtil;

//聊天室
//需求与功能:
//1.服务器负责各个客户端的消息转发
//2.每个客户端有单独的信息发送和接收功能
public class Service {
	public ArrayList<MyChannel> pool = new ArrayList<MyChannel>();

	public static void main(String[] args) {
		new Service().start();
	}

	public void start() {
		try (// 创建服务器,指定端口(1024以下系统占用)
				ServerSocket server = new ServerSocket(8888);) {// 接收客服端连接 阻塞式
			while (true) {
				Socket accept = server.accept();
				MyChannel channel = new MyChannel(accept);
				pool.add(channel);
				new Thread(channel).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private class MyChannel implements Runnable {

		private DataInputStream dis;
		private DataOutputStream dos;
		private boolean isRunning = true;

		public MyChannel(Socket client) {
			try {
				dis = new DataInputStream(client.getInputStream());
				dos = new DataOutputStream(client.getOutputStream());
			} catch (IOException e) {
				isRunning = false;
				pool.remove(this);
				FileUtil.close(dis, dos);
			}
		}

		private String getMsg() {
			String msgString = "";
			try {
				msgString = dis.readUTF();
			} catch (IOException e) {
				isRunning = false;
				pool.remove(this);
				FileUtil.close(dis, dos);
			}
			return msgString;
		}

		private void Send(String msg) {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				isRunning = false;
				pool.remove(this);
				FileUtil.close(dis, dos);
			}
		}

		private void SendOthers(String msg) {
			for (MyChannel chl : pool) {
				if (chl == this) {
					continue;
				}
				chl.Send(msg);
			}
		}

		@Override
		public void run() {
			while (isRunning) {
				SendOthers(getMsg());
			}
		}

	}
}
