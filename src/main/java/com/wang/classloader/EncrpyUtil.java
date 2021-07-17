package com.wang.classloader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * class文件编码加密用类
 * 
 * @author 王李点儿
 *
 */
public class EncrpyUtil {
	public static void entrpy(String origin, String dest) {
		try (FileInputStream fis = new FileInputStream(origin); FileOutputStream fos = new FileOutputStream(dest)) {
			int temp = -1;
			while ((temp = fis.read()) != -1) {
				fos.write(temp^0xff);
			}
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Encrpy over");
	}

	public static void main(String[] args) {
		entrpy("E:/TestFile/class/com/wang/cl/HelloEncrpy.class", "E:/TestFile/class/com/wang/cl/HelloEncrpy2.class");
	}
}
