package com.wang.internet.httpserver;

public class WebApp {
	private static ServletContext context;

	static {
		context = new ServletContext();
		context.getMapping().put("/login", "login");
		context.getServlet().put("login", new LoginServlet());
	}

	public static Servlet getApi(String url) {
		if (null == url || url.length() == 0)
			return null;
		return context.getServlet().get(context.getMapping().get(url));
	}
}
