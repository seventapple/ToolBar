package com.wang.GOF23.prototype;

import java.util.Date;

public class Sheep2 implements Cloneable {
	private String name;
	private Date brithday;

	@Override
	public String toString() {
		return "Sheep [name=" + name + ", brithday=" + brithday + "]";
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Sheep2 re = (Sheep2) super.clone();
		re.brithday = (Date)this.brithday.clone();
		return re;
	}

	public Sheep2() {
	}

	public Sheep2(String name, Date brithday) {
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
