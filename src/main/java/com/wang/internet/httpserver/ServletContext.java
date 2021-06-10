package com.wang.internet.httpserver;

import java.util.HashMap;
import java.util.Map;

public class ServletContext {
	/**
	 * key-servlet
	 */
	private Map<String, Servlet> servlet;
	/**
	 * url-key(可多对一)
	 */
	private Map<String, String> mapping;

	public ServletContext() {
		servlet = new HashMap<String, Servlet>();
		mapping = new HashMap<String, String>();
	}

	public Map<String, Servlet> getServlet() {
		return servlet;
	}

	public void setServlet(Map<String, Servlet> servlet) {
		this.servlet = servlet;
	}

	public Map<String, String> getMapping() {
		return mapping;
	}

	public void setMapping(Map<String, String> mapping) {
		this.mapping = mapping;
	}

}
