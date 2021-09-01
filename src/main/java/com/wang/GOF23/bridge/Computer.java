package com.wang.GOF23.bridge;

public class Computer {
	protected Brand brand;

	public Computer(Brand brand) {
		this.brand = brand;
	}

	public void sale() {
		this.brand.sale();
	}
}

class Laptop extends Computer {

	public Laptop(Brand brand) {
		super(brand);
	}

	public void sale() {
		super.sale();
		System.out.println("sale laptop.");
	}

}

class Pad extends Computer {

	public Pad(Brand brand) {
		super(brand);
	}

	public void sale() {
		super.sale();
		System.out.println("sale pad.");
	}

}