package com.wang.GOF23.memento;

/**
 * 备忘录类
 * 
 * @author 王李点儿
 *
 */
public class Memento {
	private String name;
	private int salery;

	public Memento(Originator obj) {
		this.name = obj.getName();
		this.salery = obj.getSalery();
	}

	public String getName() {
		return name;
	}

	public int getSalery() {
		return salery;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSalery(int salery) {
		this.salery = salery;
	}
}
