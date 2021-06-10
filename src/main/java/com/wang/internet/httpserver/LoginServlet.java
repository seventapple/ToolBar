package com.wang.internet.httpserver;

/*
 * Servlet实现类 login方法
 */
public class LoginServlet extends Servlet {

	@Override
	public void doGet(Request request, Response response) {
		response.print("<html><head><meta charset=\"utf-8\"><title>html get</title></head>");
		response.println("<body>Hello ");
		response.print(request.getParam("uname"));
		response.println(" !</p></body></html>");
	}

	@Override
	public void doPost(Request request, Response response) {
		response.print("<html><head><meta charset=\"utf-8\"><title>html post</title></head>");
		response.println("<body>Hello ");
		response.print(request.getParam("uname"));
		response.println(" !</p></body></html>");
	}

	@Override
	public void service(Request request, Response response) {
		String method = request.getMethod();
		if ("get".equalsIgnoreCase(method)) {
			doGet(request, response);
		}
		if ("post".equalsIgnoreCase(method)) {
			doPost(request, response);
		}
	}

}
