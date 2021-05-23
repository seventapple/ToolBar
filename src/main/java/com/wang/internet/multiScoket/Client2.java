package com.wang.internet.multiScoket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client2 {

	public static void main(String[] args) {
		try (// 1.创建客户端 服务器+端口 立即开始连接
				Socket client = new Socket("127.0.0.1", 8888);
				// 2.接收服务器数据
				InputStream is = client.getInputStream();) {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String readLine = br.readLine();
			System.out.println(readLine);
			// 3.发送数据to服务器
			DataOutputStream dos = new DataOutputStream(client.getOutputStream());
			dos.writeUTF("发给服务器的信息.");
			dos.flush();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("close client.");
	}

}
