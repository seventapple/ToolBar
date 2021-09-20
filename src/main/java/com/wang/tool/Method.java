package com.wang.tool;

public class Method {
	public static void main(String[] args) {
		System.out.println("操作系统:"+System.getProperty("os.name"));
		System.out.println("系统位数:"+System.getProperty("sun.arch.data.model"));
		System.out.println("操作系统的架构:"+System.getProperty("os.arch"));
		System.out.println("用户的账户名称:"+System.getProperty("user.name"));
		System.out.println("用户的当前工作目录:"+System.getProperty("user.dir"));
		System.out.println("Java 运行时环境版本:"+System.getProperty("java.version"));
	}
}
