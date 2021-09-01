package com.wang.GOF23.bridge;

public class Client {

	public static void main(String[] args) {
		Computer lenovoLaptop = new Laptop(new lenovo());
		lenovoLaptop.sale();
		Computer lenovoPad = new Pad(new lenovo());
		lenovoPad.sale();
	}

}
