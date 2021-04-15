package com.wang.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * 缓冲流（处理在字节流之前,提升性能，使用流外部包上缓冲流） 字节缓冲流：BufferedInputStream BufferedOutputStream
 * 字符缓冲流
 * 
 * @author 王李点儿
 *
 */
public class Demo05 {

	public static void main(String[] args) throws UnsupportedEncodingException {
//		code();
		encodeAndDecode();
	}

	// 字节缓冲流的使用
	private void fileCopy() throws IOException {
		File src = new File("");
		File tar = new File("");
		try (InputStream fis = new BufferedInputStream(new FileInputStream(src));
				OutputStream fos = new BufferedOutputStream(new FileOutputStream(tar))) {
			byte[] bs = new byte[1024];
			int result = 0;
			while ((result = fis.read(bs, 0, 1024)) != -1) {
				fos.write(bs);
			}
			fos.flush();
		} finally {
			System.out.println("file " + src.getName() + " copy completed.");
		}
	}

	// 字符缓冲流的使用(使用新方法，不能多态，但这种方法在文本元编码和IDE编码不一致时，会出现乱码现象，引入转换流)
	private void fileCopyByChar(String srcPath, String tarPath) {
		File src = new File(srcPath);
		File tar = new File(tarPath);
		try (BufferedReader reader = new BufferedReader(new FileReader(src));
				BufferedWriter write = new BufferedWriter(new FileWriter(tar))) {
			String line = null;
			// 新增操作(行读取和写入)
			while (null != (line = reader.readLine())) {
				write.write(line);
				write.newLine();// 换行符号=\r\n
			}
			write.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 转换流 字节流转为字符流，处理编码集(字符->二进制)，解码集(二进制->字符)
	private static void code() throws UnsupportedEncodingException {
		// 解码 byte ->program->char
		String str = "中国";// UTF-8
		// 编码 char -> byte
		byte[] data = str.getBytes();
		// 编码与解码字符集一致
		System.out.println(new String(data));
		byte[] data2 = str.getBytes("GBK");// 设定不一致字符集
		/* 乱码出现原因1：字符集不统一 */
		// 编码与解码字符集不一致，出现乱码
		System.out.println(new String(data2));
		// 编码与解码字符集一致
		System.out.println(new String(data2, "GBK"));
		/* 乱码出现原因2：字节缺失 */
		System.out.println(new String(data, 0, 4));
	}

	// 转换流 字节转化为字符
	// 输入流 InputStreamReader 解码
	// 输出流 OutputStreamWriter 编码
	private static void encodeAndDecode() {
		File f1 = new File("E:\\TestFile\\utf-8.txt");
		File f2 = new File("E:\\TestFile\\Unicode.txt");
		File f3 = new File("E:\\TestFile\\encode.txt");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f1), "UTF-8"));
				BufferedReader reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(f2), "Unicode"));
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f3), "GBK"))) {
			// 输入流 InputStreamReader 解码
			String line = null;
			while (null != (line = reader.readLine())) {
				System.out.println(line);
				// 输出流 OutputStreamWriter 编码
				writer.write(line);
			}
			writer.flush();
			// 输入流 InputStreamReader 解码
			String line2 = null;
			while (null != (line2 = reader2.readLine())) {
				System.out.println(line2);
				// 输出流 OutputStreamWriter 编码
				writer.write(line2);
			}
			writer.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
