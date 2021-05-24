package com.wang.internet.chattingRoom;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.wang.io.FileUtil;

public class Receive implements Runnable {
	private DataInputStream dis;
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

	@Override
	public void run() {
		while (isRunnable) {
			try {
				String readUTF = dis.readUTF();
				System.out.println(readUTF);
			} catch (IOException e) {
				isRunnable = false;
				FileUtil.close(dis);
			}
		}
	}

}
