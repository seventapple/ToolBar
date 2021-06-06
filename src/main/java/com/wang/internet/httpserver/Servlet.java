package com.wang.internet.httpserver;

public class Servlet {
	public void service(Request request, Response response) {
			response.print("<html><head><meta charset=\"utf-8\"><title>html</title></head>");
			response.println("<body>wang<p>Hello ");
			response.print(request.getParam("uname"));
			response.println(" !</p></body></html>");
	}
}
