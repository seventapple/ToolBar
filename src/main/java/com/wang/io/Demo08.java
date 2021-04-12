package com.wang.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

//打印流
public class Demo08 {

	public static void main(String[] args) throws IOException {
//		testOut();
//		testIn();
//		testSetOut();
		testSetIn();
	}
	//封装控制台输入,使用BufferedReader,可方便的处理行
	public static void testSetIn() throws IOException {
		InputStream is = System.in;
		BufferedReader br= new BufferedReader(new InputStreamReader(is));
		String msg = br.readLine();
		System.out.println(msg);
	}

	// 打印流的重定向
	// setIn(),setOut()
//    FileDescriptor.in 控制台输入
//    FileDescriptor.out 控制台输出
	public static void testSetOut() throws FileNotFoundException {
		// 输出流:控制台->文件  
		System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("E:\\TestFile\\1.txt", true))));
		System.out.println("Reset out......................................................");
		System.out.flush();
		//回到控制台 autoFlush
		System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.out)),true));
		System.out.println("back console");
	}

	// 输入流
	// 常量System.in
	public static void testIn() throws FileNotFoundException {
		InputStream is = System.in;// 键盘输入
		Scanner scanner = new Scanner(is);
		System.out.println("please input:");
		String nextStr = scanner.next();
		System.out.println(nextStr);
		// 文件输入
		is = new BufferedInputStream(new FileInputStream(new File("E:\\TestFile\\1.txt")));
		Scanner scanner2 = new Scanner(is);
		System.out.println("file input:");
		String nextStr2 = scanner2.next();
		System.out.println(nextStr2);
	}

	// 输出流
	// 常量System.out
	// 常量System.error(console有颜色,文本无区别)
	public static void testOut() throws FileNotFoundException {
		System.out.println("test12");
		PrintStream ps = System.out;
		ps.println(123);
		// 输出到文件
		ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("E:\\TestFile\\1.txt"), true)));
		ps.println("test");
		ps.flush();
	}

}
