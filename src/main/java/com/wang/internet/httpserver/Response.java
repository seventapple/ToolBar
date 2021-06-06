package com.wang.internet.httpserver;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

import com.wang.io.FileUtil;

/**
 * 封装响应信息
 * 
 * @author 王李点儿
 *
 */
public class Response {
	private static String CRLF = "\r\n";
	private static String BLANK = " ";
	// 响应头信息
	private StringBuffer headerInfo;
	// 响应正文信息
	private StringBuffer content;
	// 响应正文字节数组长度
	private int len;
	// 推送至客服端用的流
	private BufferedWriter bw;

	private Response() {
		headerInfo = new StringBuffer();
		content = new StringBuffer();
		len = 0;
	}

	public Response(Socket client) {
		this();
		try {
			bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			headerInfo = null;
		}
	}

	public Response print(String info) {
		if (null != info) {
			content.append(info);
			len += info.getBytes().length;
		}
		return this;
	}

	// 推送至客户端
	public void pushToClient(int code) throws IOException {
		if (headerInfo == null) {
			code = 500;
		}
		writeHeaderInfo(code);
		bw.write(headerInfo.toString());
		bw.append(content.toString());
		bw.flush();
		close();
	}

	public void close() {
		FileUtil.close(bw);
	}

	public Response println(String info) {
		if (null != info) {
			content.append(info).append(CRLF);
			len += (info + CRLF).getBytes().length;
		}
		return this;
	}

	private void writeHeaderInfo(int code) {
		// HTTP协议/版本 状态代码 描述
		headerInfo.append("HTTP/1.1").append(BLANK).append(code).append(BLANK);
		switch (code) {
		case 200:
			headerInfo.append("OK");
			break;
		case 404:
			headerInfo.append("NOT FOUND");
			break;
		case 500:
			headerInfo.append("SERVER ERROR");
			break;
		}
		headerInfo.append(CRLF);
		// 响应头
		headerInfo.append("Server:wang Server/0.0.2").append(CRLF);
		headerInfo.append("Date:").append(new Date()).append(CRLF);
		headerInfo.append("Contest-type:text/html;charset=GBK").append(CRLF);
		headerInfo.append("Content-Length:").append(content.toString().getBytes().length).append(CRLF);
		// 必须存在CRLF符号行\r\n
		headerInfo.append(CRLF);
	}
}
