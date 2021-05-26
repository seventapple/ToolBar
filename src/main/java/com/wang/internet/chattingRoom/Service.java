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
				pool.add(channel);// 统一管理
				new Thread(channel).start(); // 一条道路
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 一个客户端 一条道路 1、输入流 2、输出流 3、接收数据 4、发送数据
	 */
	private class MyChannel implements Runnable {

		private DataInputStream dis;
		private DataOutputStream dos;
		private boolean isRunning = true;
		private String name;

		public MyChannel(Socket client) {
			try {
				dis = new DataInputStream(client.getInputStream());
				dos = new DataOutputStream(client.getOutputStream());
				this.name = getMsg();
				this.send("登陆成功...");
				sendOthers("欢迎 " + this.name + " 进入聊天室!", true);
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

		private void send(String msg) {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				isRunning = false;
				pool.remove(this);
				FileUtil.close(dis, dos);
			}
		}

		private void sendOthers(String msg, boolean system) {
			// 私密信息判断
			if (msg.startsWith("@") && msg.contains(":")) {
				String target = msg.substring(1, msg.indexOf(":"));
				String content = msg.substring(msg.indexOf(":") + 1);
				for (MyChannel chl : pool) {
					if (chl == this) {
						continue;
					}
					if (chl.name.equals(target)) {
						chl.send(this.name + "悄悄的和你说:" + content);
					}
				}
			} else {
				for (MyChannel chl : pool) {
					if (chl == this) {
						continue;
					}
					// 系统通知
					if (system) {
						chl.send("系统" + msg);
					} else {
						// 群聊消息
						chl.send(this.name + ":" + msg);
					}
				}
			}
		}

		@Override
		public void run() {
			while (isRunning) {
				sendOthers(getMsg(), false);
			}
		}

	}
}
