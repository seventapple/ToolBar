package com.wang.GOF23.state;

public class Client {
	public static void main(String[] args) {
		HomeContext target = new HomeContext(new FreeState());
		target.setState(new BookedState());
	}
}
