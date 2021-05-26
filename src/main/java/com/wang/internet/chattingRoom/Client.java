package com.wang.internet.chattingRoom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 * 创建客户端: 发送数据+接收数据 写出数据：输出流 读取数据：输入流
 * 
 * 输入流 与输出流 在同一个线程内 应该 独立处理，彼此独立 加入名称
 */
public class Client {

	public static void main(String[] args) throws IOException {
		System.out.println("请输入昵称:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String name = br.readLine();
		if (name.equals("")) {
			return;
		}
		try {
			Socket client = new Socket("127.0.0.1", 8888);
			new Thread(new Send(client, name)).start();
			new Thread(new Receive(client)).start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
