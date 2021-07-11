package com.wang.javassist;

public class Example {
	private int age;
	private String name;

	public int getMsg(int a, int b) {
		System.out.println("method start");
		return a + b;
	}

	public void getMsg() {
		System.out.println("getMsg");
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}

}
