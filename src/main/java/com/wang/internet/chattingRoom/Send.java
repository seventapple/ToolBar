package com.wang.internet.chattingRoom;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.wang.io.FileUtil;

public class Send implements Runnable {
	//管道输出流
	private DataOutputStream dos;
	//控制台输入流
	private BufferedReader br;
	//控制线程
	private boolean isRunnable = true;
	//名称
	private String name;

	public Send() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	public Send(Socket client, String name) {
		this();
		this.name = name;
		try {
			dos = new DataOutputStream(client.getOutputStream());
			send(this.name);
		} catch (IOException e) {
			isRunnable = false;
			FileUtil.close(dos, br);
		}
	}

	/**
	 * 从控制台获取入力参数
	 */
	public String sendMsgFromConsole() {
		String msgString = "";
		try {
			msgString = br.readLine();
		} catch (IOException e) {
		}
		return msgString;
	}

	/**
		 * 1、从控制台接收数据
	 * 2、发送数据
	 */
	public void send(String msg) {
		if (null != msg && msg.length() > 0) {
			try {
				dos.writeUTF(msg);
				dos.flush();//强制刷新
			} catch (IOException e) {
				isRunnable = false;
				FileUtil.close(dos, br);
			}
		}
	}

	@Override
	public void run() {
		while (isRunnable) {
			send(sendMsgFromConsole());
		}
	}

}
