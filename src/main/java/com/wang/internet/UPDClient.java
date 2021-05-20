package com.wang.internet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

//客户端
//1.创建DatagramSocket+指定发送端口
//2.准备数据 字节数组
//3.打包DategramPacket+服务器地址,端口
//4.发送
//5.关闭资源
public class UPDClient {

	public static void main(String[] args) throws IOException {
		// 启动客服端,发送默认字节数组
		 start("end");
		// 启动客服端,发送其它类型数据
//		start2(123);
	}

	public static void start(String input) throws IOException {
		DatagramSocket client = new DatagramSocket(6666);
		byte[] data = input.getBytes();
		DatagramPacket packet = new DatagramPacket(data, data.length, new InetSocketAddress("localhost", 8888));
		client.send(packet);
		System.out.println("client send success.");
		client.close();
	}

	public static void start2(int input) throws IOException {
		DatagramSocket client = new DatagramSocket(6666);
		byte[] data = encode(input);
		DatagramPacket packet = new DatagramPacket(data, data.length, new InetSocketAddress("localhost", 8888));
		client.send(packet);
		System.out.println("client send success.");
		client.close();
	}

	public static byte[] encode(int input) throws IOException {
		byte[] data = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		dos.writeInt(input);
		dos.flush();
		data = bos.toByteArray();
		return data;
	}

}
