package com.wang.thread.pool;

public class Item {
	private int size;
	private Boolean flag;

	public Item(int size) {
		super();
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "Item [size=" + size + ", flag=" + flag + "]";
	}

}
