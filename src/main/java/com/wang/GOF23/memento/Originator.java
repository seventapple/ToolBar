package com.wang.GOF23.memento;

/**
 * 源发器
 * 
 * @author 王李点儿
 *
 */
public class Originator {
	private String name;
	private int salery;

	public Memento save() {
		return new Memento(this);
	}

	public void recovery(Memento meo) {
		this.name = meo.getName();
		this.salery = meo.getSalery();
	}

	public Originator() {
	}

	public Originator(String name, int salery) {
		this.name = name;
		this.salery = salery;
	}

	@Override
	public String toString() {
		return "Originator [name=" + name + ", salery=" + salery + "]";
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
