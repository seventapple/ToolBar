package com.wang.internet.httpserver;

public class ServletEntity {
	private String name;
	private String clz;

	public ServletEntity() {
	}

	public ServletEntity(String name, String clz) {
		super();
		this.name = name;
		this.clz = clz;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClz() {
		return clz;
	}

	public void setClz(String clz) {
		this.clz = clz;
	}

	@Override
	public String toString() {
		return "ServletEntity [name=" + name + ", clz=" + clz + "]";
	}

}
