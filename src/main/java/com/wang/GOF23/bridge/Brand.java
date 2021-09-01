package com.wang.GOF23.bridge;

public interface Brand {
	public void sale();
}

class lenovo implements Brand {
	@Override
	public void sale() {
		System.out.println("sale lenovo.");
	}
}

class Dell implements Brand {
	@Override
	public void sale() {
		System.out.println("sale Dell.");
	}
}
