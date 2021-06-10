package com.wang.internet.httpserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Request {
	// 请求方式
	private String method;
	// 请求资源
	private String url;

	// 请求资源
	private Map<String, List<String>> params;
	private String CRLF = "\r\n";
	private static String BLANK = " ";
	private String requestInfo;
	private InputStream is;

	private Request() {
		this.method = "";
		this.url = "";
		this.params = new HashMap<String, List<String>>();
	}

	public Request(InputStream is) {
		this();
		this.is = is;
		byte[] b = new byte[20480];
		try {
			int read = is.read(b);
			requestInfo = new String(b, 0, read);
		} catch (IOException e) {
			return;
		}
		parseRequestInfo();
	}

	// 分析头信息
	public void parseRequestInfo() {
		if (null == requestInfo || (requestInfo = requestInfo.trim()).equals("")) {
			return;
		}
		String paramString = "";
		// 从首行分解出请求方法 请求路径 ?请求参数(get)
		// 从末行得请求参数(post)
		// 1.请求方式
		String firstLine = requestInfo.substring(0, requestInfo.indexOf(CRLF));
		int idx = firstLine.indexOf("/");// /的位置
		this.method = firstLine.substring(0, idx).trim();
		// 2.utl+param
		int idx2 = firstLine.indexOf("HTTP/");
		if ("get".equalsIgnoreCase(this.method)) {
			String tempUrl = firstLine.substring(idx, idx2).trim();
			if (tempUrl.contains("?")) {
				String[] urlArr = tempUrl.split("\\?");
				this.url = urlArr[0];
				anaylzerParam(urlArr[1]);
			} else {
				this.url = tempUrl;
			}
		} else if ("post".equalsIgnoreCase(this.method)) {
			this.url = firstLine.substring(idx, idx2).trim();
			String lastLine = requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim();
			anaylzerParam(lastLine);
		}
//		showRequset();
	}

	public void showRequset() {
		System.out.println("method:" + method);
		System.out.println("url:" + url);
		for (Entry<String, List<String>> entry : params.entrySet()) {
			System.out.println("params key:" + entry.getKey());
			System.out.println("params value:" + entry.getValue());
		}
	}

	// 针对中文进行URLDecode
	private String decode(String msg, String code) {
		String decodeMsg;
		try {
			decodeMsg = URLDecoder.decode(msg, code);
		} catch (UnsupportedEncodingException e) {
			decodeMsg = null;
		}
		return decodeMsg;
	}

	// 获取值列表
	public List<String> getParams(String key) {
		return params.get(key);
	}

	// 获取值
	public String getParam(String key) {
		List<String> values = params.get(key);
		return values == null ? null : values.get(0);
	}

	// a=1&b=2
	public void anaylzerParam(String getStr) {
		StringTokenizer token = new StringTokenizer(getStr, "&");
		while (token.hasMoreTokens()) {
			String key;
			String value;
			String[] pv = token.nextToken().split("=");
			key = pv[0];
			if (pv.length == 2) {
				value = decode(pv[1], "GBK");
			} else {
				value = null;
			}
			if (params.containsKey(pv[0])) {
				params.get(key).add(value);
			} else {
				List<String> tempValues = new ArrayList<String>();
				tempValues.add(value);
				params.put(key, tempValues);
			}
		}
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
