package com.wang.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * 按照指定约规进行解码的类读取
 * 
 * @author 王李点儿
 *
 */
public class DecrpyClassLoader extends ClassLoader {
	private String dir;

	public DecrpyClassLoader(String dir) {
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
		byte[] data = null;
		try (FileInputStream is = new FileInputStream(filePath);
				ByteArrayOutputStream bos = new ByteArrayOutputStream();) {
			int len = -1;
			while ((len = is.read()) != -1) {
				bos.write(len ^ 0xff);
			}
			bos.flush();
			data = bos.toByteArray();
		} catch (Exception e) {
		}
		return data;
	}
}
