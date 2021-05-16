package com.wang.internet;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class InetAddressWa {

	public static void main(String[] args) throws UnknownHostException {
		// InetAddress:计算机ip地址和DNS,无封装端口
		inetAddressTest();
		// InetSocketAddress:InetAddress的基础上,添加端口(用于Socket通讯)
		inetSocketTest();
	}

	public static void inetSocketTest() throws UnknownHostException {
		// 创建对象
		// InetSocketAddress(int port)
		// InetSocketAddress(String hostname, int port)
		InetSocketAddress isa = new InetSocketAddress("127.0.0.1", 9999);
		System.out.println(isa.getHostName());
		System.out.println(isa.getPort());
		InetAddress addr = InetAddress.getLocalHost();
		// InetSocketAddress(InetAddress addr, int port)
		InetSocketAddress isa2 = new InetSocketAddress(addr, 9998);
		System.out.println(isa2.getHostName());
		System.out.println(isa2.getPort());
		InetSocketAddress isa3 = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 9997);
		System.out.println(isa3.getHostName());
		System.out.println(isa3.getPort());
	}

	public static void inetAddressTest() throws UnknownHostException {
		// 创建对象getLocalHost()
		InetAddress addr = InetAddress.getLocalHost();
		// 获取本地IP
		System.out.println(addr.getHostAddress());
		// 获取本地计算机名
		System.out.println(addr.getHostName());
		// 根据域名获取对象
		InetAddress addr2 = InetAddress.getByName("www.163.com");
		System.out.println(addr2.getHostAddress());
		System.out.println(addr2.getHostName());
		// 根据IP获取对象
		InetAddress addr3 = InetAddress.getByName("162.125.18.133");
		System.out.println(addr3.getHostAddress());
		// 返回IP,若不存在的IP,直接返回
		System.out.println(addr3.getHostName());
	}

}
