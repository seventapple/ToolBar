package com.wang.internet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

//URI:统一资源标识符
//URL:统一资源定位器,一种具体的URI,四部分组成(协议,域名,端口(http协议默认端口80),资源文件名)
public class URLTest {
	public static void main(String[] args) throws Exception {
		testURL(false);
		getHomePageStream(true);
	}

	// URL类测试,基本方法
	private static void testURL(boolean show) throws IOException {
		if (show) {
			// 绝对路径构建 URL(String spec)
			URL url = new URL("http://www.baidu.com:80/index.html#aa?uname=wang");
			System.out.println("协议 : " + url.getProtocol());
			System.out.println("域名 : " + url.getHost());
			System.out.println("端口 : " + url.getPort());
			System.out.println("资源 : " + url.getFile());
			System.out.println("相对路径资源 : " + url.getPath());
			System.out.println("锚点 : " + url.getRef());
			System.out.println("参数 : " + url.getQuery());// 存在锚点(#aa),识别为null;锚点不存在,可识别
			// URL(URL context, String spec)

		}
	}

	// 获取主页资源(源代码)
	private static void getHomePageStream(boolean show) throws IOException {
		if (show) {
			// 主页默认资源(html(初始化数据)+css(美化数据)+js(交互数据)+date)
			URL url = new URL("http://www.baidu.com");
			// 获取资源(网络流)
//			InputStream is = url.openStream();
			// 涉及编码解码,使用转换流
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			// 输出至指定文件
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(new File("E:/TestFile/index.html")), "UTF-8"));
			String msg = null;
			while ((msg = br.readLine()) != null) {
				bw.write(msg);
			}
			bw.flush();
			br.close();
			bw.close();
			System.out.println("finished.");
		}
	}
}
