package com.wang.internet.multiScoket.business;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) {
		Socket client = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		FileInputStream fis = null;
		try {
			// 1.创建客户端 服务器+端口 立即开始连接
			client = new Socket("127.0.0.1", 44908);
			dos = new DataOutputStream(client.getOutputStream());
			dis = new DataInputStream(client.getInputStream());
			fis = new FileInputStream("E:\\TestFile\\222.txt");
			int size = fis.available();
			dos.writeUTF("ADD");
			dos.writeUTF("E:\\TestFile\\11\\2.txt");
			dos.writeInt(size);
			byte[] data = new byte[128];
			int len;
			while ((len = fis.read(data)) != -1) {
				dos.write(data, 0, len);
			}
			dos.flush();
			String ret = dis.readUTF();
			System.out.println(ret);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (dos != null) {
					dos.close();
				}
				if (fis != null) {
					fis.close();
				}
				if (dis != null) {
					dis.close();
				}
				if (client != null) {
					client.close();
				}
			} catch (Exception e) {

			}
		}
	}

	/**
	 * send file by Bean
	 */
	public void test1() {
		try (// 1.创建客户端 服务器+端口 立即开始连接
				Socket client = new Socket("127.0.0.1", 8888);) {
			// 2.ready data
			Bean bean = new Bean();
			bean.setName("abc");
			FileInputStream fis = new FileInputStream(new File("E:\\TestFile\\111.txt"));
			bean.setBody(fis);
			// 3.发送数据to服务器
			ObjectOutputStream dos = new ObjectOutputStream(client.getOutputStream());
			dos.writeObject(bean);
			dos.flush();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
