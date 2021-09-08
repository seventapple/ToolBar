package com.wang.GOF23.facade;

public interface Ju {
	void bussiness();
}

class GongShangJu implements Ju {
	@Override
	public void bussiness() {
		System.out.println("GongShangJu is done.");
	}
}
