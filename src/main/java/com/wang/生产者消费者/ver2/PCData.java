package com.wang.生产者消费者.ver2;

public class PCData {
	private final int intData;

	PCData(int d) {
		intData = d;
	}

	public int getIntData() {
		return intData;
	}

	@Override
	public String toString() {
		return "PCData [intData=" + intData + "]";
	}
}
