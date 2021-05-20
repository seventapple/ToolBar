package com.wang.internet;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.sun.org.apache.bcel.internal.generic.NEW;

//UDP 以数据为中心 非面向连接 不安全 数据可能丢失 效率高
//DatagramSocket DategramPacket
//服务器端
//1.创建DategramSocket+指定接收端口
//2.准备接收容器 字节数组 封装DategramPacket
//3.接受数据
//4.分析数据
//5.关闭资源
//客户端
//1.创建DatagramSocket+指定发送端口
//2.准备数据 字节数组
//3.打包DategramPacket+服务器地址,端口
//4.发送
//5.关闭资源
public class UPDService {

	public static void main(String[] args) throws IOException {
		// 启动服务,接收默认字节数组
		// start();
		// 启动服务,接收其它类型数据
		// start2();
		// 启动服务,一直接收数据,直到结束符号
		start3();
	}

	public static void start3() throws IOException {
		boolean flag = true;
		System.out.println("start service 8888");
		DatagramSocket service = new DatagramSocket(8888);
		while (flag) {
			byte[] container = new byte[1024];
			DatagramPacket packet = new DatagramPacket(container, container.length);
			service.receive(packet);
			byte[] data = packet.getData();
			String result = new String(data, 0, data.length);
			System.out.println("get msg : " + result);
			if ("end".equals(result)) {
				flag = false;
			}
		}
		service.close();
	}

	public static void start() throws IOException {
		System.out.println("start service 8888");
		DatagramSocket service = new DatagramSocket(8888);
		byte[] container = new byte[1024];
		DatagramPacket packet = new DatagramPacket(container, container.length);
		service.receive(packet);
		byte[] data = packet.getData();
		System.out.println(new String(data, 0, data.length));
		service.close();
	}

	public static void start2() throws IOException {
		System.out.println("start service 8888");
		DatagramSocket service = new DatagramSocket(8888);
		byte[] container = new byte[1024];
		DatagramPacket packet = new DatagramPacket(container, container.length);
		service.receive(packet);
		byte[] data = packet.getData();
		decode(data);
		service.close();
	}

	public static void decode(byte[] data) throws IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bis);
		int readInt = dis.readInt();
		System.out.println("get msg : " + readInt);
	}

}
