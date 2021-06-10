package com.wang.internet.httpserver;

public class WebApp {
	private static ServletContext context;

	static {
		context = new ServletContext();
		context.getMapping().put("/login", "login");
		context.getServlet().put("login", "com.wang.internet.httpserver.LoginServlet");
	}

	public static Servlet getApi(String url)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (null == url || url.length() == 0)
			return null;
		return (Servlet) Class.forName(context.getServlet().get(context.getMapping().get(url))).newInstance();
	}
}
