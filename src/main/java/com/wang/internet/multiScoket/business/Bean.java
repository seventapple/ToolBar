package com.wang.internet.multiScoket.business;

import java.io.FileInputStream;
import java.io.Serializable;

public class Bean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private FileInputStream body;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FileInputStream getBody() {
		return body;
	}

	public void setBody(FileInputStream body) {
		this.body = body;
	}

}
