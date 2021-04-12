package com.wang.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Vector;

//文件合并
public class Demo11 {

	public static void main(String[] args) throws IOException {
//		merge1("E:\\TestFile\\split", "E:\\TestFile\\merge.txt");
		merge2("E:\\TestFile\\split", "E:\\TestFile\\merge2.txt");
	}

	// 使用BufferedInputStream,BufferedOutputStream
	public static void merge1(String destPath, String output) {
		File dest = new File(destPath);
		File[] files = dest.listFiles();
		try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(output), true));) {
			for (File f : files) {
				try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));) {
					byte[] te = new byte[512];
					int len = 0;
					while (-1 != (len = bis.read(te))) {
						bos.write(te, 0, len);
					}
					bos.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 使用SequenceInputStream
	public static void merge2(String destPath, String output) throws IOException {
		File dest = new File(destPath);
		File[] files = dest.listFiles();
		// 创建容器
		Vector<InputStream> vi = new Vector<InputStream>();
		for (File f : files) {
			vi.add(new BufferedInputStream(new FileInputStream(f)));
		}
		// SequenceInputStream汇总流
		SequenceInputStream sis = new SequenceInputStream(vi.elements());
		try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(output), true));) {
			byte[] te = new byte[512];
			int len = 0;
			while (-1 != (len = sis.read(te))) {
				bos.write(te, 0, len);
			}
			bos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
