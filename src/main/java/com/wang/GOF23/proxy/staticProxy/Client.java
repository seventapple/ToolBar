package com.wang.GOF23.proxy.staticProxy;

public class Client {

	public static void main(String[] args) {
		RealSinger star=new RealSinger("Jolin");
		ProxyStar proxy = new ProxyStar(star);
		proxy.confer();
		proxy.signContract();
		proxy.bookTicket();
		proxy.sing();
		proxy.collectMoney();
	}

}
