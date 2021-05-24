package com.wang.internet.chattingRoom;

import java.io.IOException;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		try {
			Socket client = new Socket("127.0.0.1", 8888);
			new Thread(new Send(client)).start();
			new Thread(new Receive(client)).start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
