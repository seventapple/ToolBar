package com.wang.regex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebSpider {

	public static void main(String[] args) {
		String url = getUrl("https://www.sina.com.cn", "utf-8");
		anylaze(url);
	}

	public static void anylaze(String target) {
		// Pattern p = Pattern.compile("<a[\\s\\S]+?</a>");// 取到超链接所有内容
//		Pattern p = Pattern.compile("href=\".+?\"");// 取到超链接地址(带href)
		Pattern p = Pattern.compile("href=\"(.+?)\"");// 取到超链接地址
		Matcher m = p.matcher(target);
		while (m.find()) {
			System.out.println(m.group(1));
		}
	}

	public static String getUrl(String urlStr, String encode) {
		StringBuffer ret = new StringBuffer();
		try {
			URL url = new URL(urlStr);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName(encode)));
			String line;
			while ((line = br.readLine()) != null) {
				ret.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret.toString();
	}

}
