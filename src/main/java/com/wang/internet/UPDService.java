package com.wang.internet;

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
		// TODO Auto-generated method stub
		start();
	}

	public static void start() throws IOException {
		DatagramSocket service = new DatagramSocket(8888);
		byte[] container = new byte[1024];
		DatagramPacket packet = new DatagramPacket(container, container.length);
		service.receive(packet);
		byte[] data = packet.getData();
		System.out.println(new String(data, 0, data.length));
		service.close();
	}

}
