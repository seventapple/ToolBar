package com.wang.internet.chattingRoom;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.wang.io.FileUtil;

public class Send implements Runnable {

	private DataOutputStream dos;
	private BufferedReader br;
	private boolean isRunnable = true;

	public Send() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	public Send(Socket client) {
		this();
		try {
			dos = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			isRunnable = false;
			FileUtil.close(dos, br);
		}
	}

	public String sendMsgFromConsole() {
		String msgString = "";
		try {
			msgString = br.readLine();
		} catch (IOException e) {
		}
		return msgString;
	}

	@Override
	public void run() {
		while (isRunnable) {
			String msg = sendMsgFromConsole();
			if (null != msg && msg.length() > 0) {
				try {
					dos.writeUTF(msg);
					dos.flush();
				} catch (IOException e) {
					isRunnable = false;
					FileUtil.close(dos, br);
				}
			}
		}
	}

}
