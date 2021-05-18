package com.wang.internet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class UPDClient {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		start("test 1, 2, 3...");
	}

	public static void start(String input) throws IOException {
		DatagramSocket client = new DatagramSocket(6666);
		byte[] data = input.getBytes();
		DatagramPacket packet = new DatagramPacket(data, data.length, new InetSocketAddress("localhost", 8888));
		client.send(packet);
		System.out.println("client send success.");
		client.close();
	}

}
