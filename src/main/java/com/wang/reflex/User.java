package com.wang.reflex;

public class User {
	private int age;
	public String name;

	public User() {
	}

	private User(int age, String name) {
		this.age = age;
		this.name = name;
	}

	public void sendMsg() {
		System.out.println("no param method.");
	}

	private String getMsg(String s, int i) {
		System.out.println("port msg by " + name);
		return s + i;
	}

	@Override
	public String toString() {
		return "User [age=" + age + ", name=" + name + "]";
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
