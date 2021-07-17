package com.wang.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FileClassLoader extends ClassLoader {
	private String dir;

	public FileClassLoader(String dir) {
		this.dir = dir;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> c = findLoadedClass(name);
		if (c != null) {
			return c;
		} else {
			ClassLoader parent = this.getParent();
			try {
				c = parent.loadClass(name);
			} catch (Exception e) {
				;
			}
			if (c != null) {
				return c;
			} else {
				byte[] classData = getClassData(name);
				if (classData == null) {
					throw new ClassNotFoundException();
				} else {
					c = defineClass(name, classData, 0, classData.length);
					return c;
				}
			}
		}
	}

	private byte[] getClassData(String name) {
		String filePath = dir + File.separator + name.replace(".", File.separator) + ".class";
		/**
		 * 网络类加载器路径(filePath取得方法类似) 
		 * URL url=new URL(filePath); 
		 * InputStream is = url.openStream();
		 */

		byte[] data = null;
		try (FileInputStream is = new FileInputStream(filePath);
				ByteArrayOutputStream bos = new ByteArrayOutputStream();) {
			byte[] d = new byte[128];
			int len;
			while ((len = is.read(d)) != -1) {
				bos.write(d, 0, len);
				bos.flush();
			}
			data = bos.toByteArray();
		} catch (Exception e) {
		}
		return data;
	}
}
