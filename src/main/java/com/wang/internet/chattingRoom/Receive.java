package com.wang.internet.chattingRoom;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import com.wang.io.FileUtil;

public class Receive implements Runnable {
	// 输入流
	private DataInputStream dis;
	// 线程标识
	private boolean isRunnable = true;

	public Receive() {
	}

	public Receive(Socket client) {
		this();
		try {
			dis = new DataInputStream(client.getInputStream());
		} catch (IOException e) {
			isRunnable = false;
			FileUtil.close(dis);
		}
	}

//接收数据
	private String recive() {
		try {
			return dis.readUTF();
		} catch (IOException e) {
			isRunnable = false;
			FileUtil.close(dis);
		}
		return "";
	}

	@Override
	public void run() {
		while (isRunnable) {
			System.out.println(recive());
		}
	}

}
