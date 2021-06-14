package com.wang.internet.httpserver;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.sun.xml.internal.messaging.saaj.packaging.mime.Header;

public class WebApp {
	private static ServletContext context;

	static {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		try {
			parser = factory.newSAXParser();
			WebHandler hander = new WebHandler();
			parser.parse(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("com/wang/internet/httpserver/web.xml"), hander);

			List<ServletEntity> entitys = hander.getEntitys();
			List<ServletMapping> maps = hander.getMaps();

			context = new ServletContext();
			for (ServletEntity ety : entitys) {
				context.getServlet().put(ety.getName(), ety.getClz());
			}
			for (ServletMapping emg : maps) {
				String name = emg.getName();
				List<String> urls = emg.getUrls();
				for (String url : urls) {
					context.getMapping().put(url, name);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Servlet getApi(String url)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (null == url || url.length() == 0)
			return null;
		return (Servlet) Class.forName(context.getServlet().get(context.getMapping().get(url))).newInstance();
	}
}
