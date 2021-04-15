package com.wang.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class Demo04 {

	/**
	 * 字符流：只处理纯文本(.txt .html .java) 节点流：Reader FileReader Writer FileWriter
	 */
	public static void main(String[] args) {
		fileCopy("E:/TestFile/11", "E:/TestFile/12");
	}

	public static void readAndWrite() {
		// 文件读
		File src = new File("E:\\TestFile\\111.txt");
		try (Reader reader = new FileReader(src);) {
			char[] temp = new char[10];
			int len = 0;
			while ((len = reader.read(temp)) != -1) {
				String str = new String(temp);
				System.out.println(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 文件写(true-追加;false/不加-覆盖)
		try (Writer fw = new FileWriter(src, true);) {
			char[] temp = new char[3];
			temp[0] = 'a';
			temp[1] = 'b';
			temp[2] = 'c';
			fw.write(temp);
			fw.write("test\r\n");
			fw.append("test append");
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文件拷贝
	 * 只支持纯文件
	 */
	public static void fileCopy(String srcPath, String tarPath) {
		File src = new File(srcPath);
		File tar = new File(tarPath);
		try (Reader reader = new FileReader(src); Writer write = new FileWriter(tar)) {
			char[] temp = new char[128];
			int len = 0;
			while ((len = reader.read(temp)) != -1) {
				write.write(temp);
			}
			write.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
