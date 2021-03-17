package com.wang.module;

import java.io.Serializable;

public class ObjBean implements Serializable {
	private String str1;
	private transient String str2;

	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public String getStr2() {
		return str2;
	}

	public void setStr2(String str2) {
		this.str2 = str2;
	}

	@Override
	public String toString() {
		return "ObjBean [str1=" + str1 + ", str2=" + str2 + "]";
	}

}
