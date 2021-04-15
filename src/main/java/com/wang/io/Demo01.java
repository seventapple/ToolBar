package com.wang.io;

import java.io.File;

public class Demo01 {

	public static void main(String[] args) {
		// 路径分隔符
		System.out.println(File.pathSeparator);
		// 名称分隔符
		System.out.println(File.separator);
		// 绝对路径构建文件
		File f11 = new File("E:\\TestFile\\1.txt");
		System.out.println(f11.getAbsolutePath());
		File f22 = new File("E:/TestFile/1.txt");
		System.out.println(f22.getAbsolutePath());
		// 相对路径
		File f3 = new File("E:/TestFile", "1.txt");
		System.out.println(f3.getAbsolutePath());
		File f4 = new File(new File("E:/TestFile"), "1.txt");
		System.out.println(f4.getAbsolutePath());
		// 使用user.dir构建(当前路径)
		File f5 = new File("1.txt");
		System.out.println(f5.getAbsolutePath());
		File f6 = new File(".");
		System.out.println(f6.getAbsolutePath());
		System.out.println("===========获取文件名============");
		File f1 = new File("1.txt");
		System.out.println(f1.getName());// 文件名，路径名
		System.out.println(f1.getPath());// 路径名
		System.out.println(f1.getAbsolutePath());// 绝对路径
		System.out.println(f1.getAbsoluteFile());// 绝对路径对应的File对象
		System.out.println(f1.getParent());// 上一级目录,如果为相对路径,返回null
		System.out.println("===========判定文件属性============");
		File f2 = new File("e:/TestFile/1.txt");
		System.out.println(f2.exists());// 文件是否存在
		System.out.println(f2.canRead());// 文件是否可读
		System.out.println(f2.canWrite());// 文件是否可写
		System.out.println(f2.isFile());// 文件判断
		System.out.println(f2.isDirectory());// 文件夹判断
		System.out.println(f2.isAbsolute());// 判断路径是否相对路径
		System.out.println("===========长度============");
		System.out.println(f2.length());// 文件长度(字节数) 文件夹为0
		System.out.println("===========创建和删除============");
		// boolean res = f2.createNewFile(); 文件创建,成功返回true; 文件已存在,返回false
		// boolean res = f2.delete(); 删除 成功true
		// File temp = File.createTempFile("test",
		// ".tmp");//默认路径 C:\Users\LiDianWang\AppData\Local\Temp
//		File temp = File.createTempFile("www", ".tmp", new File("e:/TestFile"));// 创建临时文件
//		temp.deleteOnExit();// 程序结束，删除临时文件
	}

}
