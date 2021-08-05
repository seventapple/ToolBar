package com.wang.GOF23.prototype;

import java.util.Date;

public class Sheep implements Cloneable {
	private String name;
	private Date brithday;

	@Override
	public String toString() {
		return "Sheep [name=" + name + ", brithday=" + brithday + "]";
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public Sheep() {
	}

	public Sheep(String name, Date brithday) {
		this.name = name;
		this.brithday = brithday;
	}

	public String getName() {
		return name;
	}

	public Date getBrithday() {
		return brithday;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}

}
