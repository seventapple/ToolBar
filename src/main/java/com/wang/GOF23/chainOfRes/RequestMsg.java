package com.wang.GOF23.chainOfRes;

public class RequestMsg {
	private String name;
	private int days;
	private String msg;

	public RequestMsg(String name, int days, String msg) {
		super();
		this.name = name;
		this.days = days;
		this.msg = msg;
	}

	public String getName() {
		return name;
	}

	public int getDays() {
		return days;
	}

	public String getMsg() {
		return msg;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	};

}
