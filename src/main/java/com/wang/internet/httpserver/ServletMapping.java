package com.wang.internet.httpserver;

import java.util.ArrayList;
import java.util.List;

public class ServletMapping {
	private String name;
	private List<String> urls;

	public ServletMapping() {
		urls = new ArrayList<String>();
	}

	public ServletMapping(String name, List<String> urls) {
		super();
		this.name = name;
		this.urls = urls;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	@Override
	public String toString() {
		return "ServletMapping [name=" + name + ", urls=" + urls + "]";
	}

}
