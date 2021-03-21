package com.wang.io;

import java.io.Closeable;

public class FileUtil {

	/**
	 * 1.工具类关闭流(可变参数...只能是形参的最后一位, 处理方式与数组一样)
	 */
	public static void close(Closeable... io) {
		for (Closeable temp : io) {
			try {
				if (null != temp) {
					temp.close();
				}
			} catch (Exception e) {
				;
			}
		}
	}

	/**
	 * 2.使用泛型关闭流 
	 * 3.或者1.7新特性 try-with-resource
	 */
	public static <T extends Closeable> void closeAll(T... io) {
		for (Closeable temp : io) {
			try {
				if (null != temp) {
					temp.close();
				}
			} catch (Exception e) {
				;
			}
		}
	}
}
