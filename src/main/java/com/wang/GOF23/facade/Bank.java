package com.wang.GOF23.facade;

public interface Bank {
	void bussiness();
}

class ICBC implements Bank {

	@Override
	public void bussiness() {
		System.out.println("ICBC is done.");
	}

}
