package com.wang.internet.httpserver;

import java.util.HashMap;
import java.util.Map;

public class ServletContext {
	/**
	 * key-servlet
	 */
	private Map<String, String> servlet;
	/**
	 * url-key(可多对一)
	 */
	private Map<String, String> mapping;

	public ServletContext() {
		servlet = new HashMap<String, String>();
		mapping = new HashMap<String, String>();
	}

	public Map<String, String> getServlet() {
		return servlet;
	}

	public void setServlet(Map<String, String> servlet) {
		this.servlet = servlet;
	}

	public Map<String, String> getMapping() {
		return mapping;
	}

	public void setMapping(Map<String, String> mapping) {
		this.mapping = mapping;
	}

}
