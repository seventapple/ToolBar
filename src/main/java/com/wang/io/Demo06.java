package com.wang.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//节点流(程序用于直接操作目标设备所对应的类)
//处理流(程序通过一个间接流类去调用节点流类，以达到更加灵活方便地读写各种类型的数据，这个间接流类就是处理流)
//节点流的类型
//1.File 文件流。对文件进行读、写操作 ：FileReader、FileWriter、FileInputStream、FileOutputStream
//2.1.从/向内存数组读写数据: CharArrayReader与 CharArrayWriter、ByteArrayInputStream与ByteArrayOutputStream[新增方法:toByteArray()]
//2.2.从/向内存字符串读写数据 StringReader、StringWriter、StringBufferInputStream。
//3.Pipe管道流。 实现管道的输入和输出（进程间通信）: PipedReader与PipedWriter、PipedInputStream与PipedOutputStream。
public class Demo06 {

	public static void main(String[] args) throws IOException {
		// 字节数组操作
		readByteStream(writeByteStream());

		// 文件-(文件输入流)->程序-(字节数组输出流)->字节数组
		byte[] bs = getBytesFromFile("E:\\TestFile\\111.txt");
		// 字节数组-(字节数组输入流)->程序-(文件输出流)->文件
		toFileFromByteArray(bs,"E:\\TestFile\\112.txt");
	}

	// 字节数组-(字节数组输入流)->程序-(文件输出流)->文件
	public static void toFileFromByteArray(byte[] src, String destPath) throws IOException {
		// 创建源 目的地
		File dest = new File(destPath);
		// 字节数组输入流
		InputStream is = new BufferedInputStream(new ByteArrayInputStream(src));
		// 文件输出流
		OutputStream os = new BufferedOutputStream(new FileOutputStream(dest));

		// 操作 不断读取字节数组
		byte[] flush = new byte[1024];
		int len = 0;
		while ((len = is.read(flush)) != -1) {
			os.write(flush, 0, len);
		}
		os.flush();
		os.close();
		is.close();
	}

	// 文件-(文件输入流)->程序-(字节数组输出流)->字节数组
	public static byte[] getBytesFromFile(String srcPath) throws IOException {
		// 创建文件
		File src = new File(srcPath);
		// 创建字节数组目的地
		byte[] dest = null;
		// 选择流 文件输入流
		InputStream is = new BufferedInputStream(new FileInputStream(src));
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		// 操作 不断从文件中读取到字节数组流中
		byte[] flush = new byte[1024];
		int len = 0;
		while ((len = is.read(flush)) != -1) {
			bos.write(flush, 0, len);
		}
		bos.flush();
		dest = bos.toByteArray();
		bos.close();
		is.close();
		return dest;
	}

	// 输出流 操作文件输出流 使用新增方法 不能使用多态
	public static byte[] writeByteStream() throws IOException {
		byte[] result;
		String msg = "通过ByteArrayInputStream/ByteArrayOutputStream内存数组读写数据";
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] info = msg.getBytes();
		bos.write(info, 0, info.length);
		result = bos.toByteArray();
		bos.close();
		return result;
	}

	// 输入流 读取字节数组
	public static void readByteStream(byte[] src) throws IOException {
		InputStream is = new BufferedInputStream(new ByteArrayInputStream(src));

		byte[] flush = new byte[1024];
		int len = 0;
		while ((len = is.read(flush)) != -1) {
			System.out.println(new String(flush, 0, len));
		}

		is.close();
	}

}
