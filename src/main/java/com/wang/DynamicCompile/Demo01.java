package com.wang.DynamicCompile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class Demo01 {

	public static void main(String[] args) {
		// 动态编译 方法一(可通过IO流将代码输出至本地)
		JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
		int result = jc.run(null, null, null, "E:/TestFile/Hello.java");
		System.out.println(result == 0 ? "success" : "failed");

		// 动态编译 方法二
		try {
			Runtime run = Runtime.getRuntime();
			Process exec = run.exec("java -cp e:/TestFile Hello 1 2 3");
			BufferedReader br = new BufferedReader(new InputStreamReader(exec.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// classloader
		URL[] urls;
		try {
			urls = new URL[] { new URL("file:/" + "E:/TestFile/") };
			URLClassLoader loader = new URLClassLoader(urls);
			Class c = loader.loadClass("Hello");
			Method method = c.getMethod("main", String[].class);
			// 静态方法,第一个参数可为null,第二个参数数组的情况下需要强转为Object,否则会编译认为是三个参数
			// java.lang.IllegalArgumentException: wrong number of arguments
			method.invoke(null, (Object)new String[] { "1", "2", "3" });
		} catch (MalformedURLException | ClassNotFoundException | NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
