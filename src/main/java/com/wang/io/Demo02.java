package com.wang.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;

public class Demo02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 创建目录
		String filePath = "E:\\TestFile\\0207\\1";
		File srcFile = new File(filePath);
		// 父级必须存在，不存在返回false
		if (srcFile.mkdir()) {
			System.out.println("mkdir success.");
		} else {
			System.out.println("mkdir failed.");
			// mkdirs(),只要路径正确，逐层创建不存在的文件,最下层文件存在，返回false
			if (srcFile.mkdirs()) {
				System.out.println("mkdirs success");
			} else {
				System.out.println("mkdirs failed");
			}
		}
		// list 获取目录下文件、文件夹名
		File list = new File("E:\\TestFile");
		String[] listName = list.list();
		System.out.println("TestFile下汇总：");
		for (String name : listName) {
			System.out.println(name);
		}
		// listFiles 获取目录下文件、文件夹的File对象
		File[] listFiles = list.listFiles();
		System.out.println("TestFile下File对象汇总：");
		for (File f : listFiles) {
			System.out.println(f.getAbsolutePath());
		}
		// listFiles 带参数 配合Filter
		String[] listFiles2 = list.list(new FilenameFilter() {
			@Override
			/**
			 * dir src name name 目录中对象名
			 */
			public boolean accept(File dir, String name) {
				return name.startsWith("1");
			}
		});
		System.out.println("TestFile下File对象汇总(Filter startWith‘1’)：");
		for (String f : listFiles2) {
			System.out.println(f);
		}
		// 文件的递归
		System.out.println("文件递归：");
		fileSearch(list, 0);
		//系统分区情况统计
		File[] listRoots = File.listRoots();
		System.out.println("系统根路径(盘符)统计：" + Arrays.toString(listRoots));
	}

	/**
	 * 文件夹的递归
	 * 
	 * @param f 递归对象
	 * @param i 层级
	 */

	public static void fileSearch(File f, int i) {
		if (f.exists()) {
			if (f.isDirectory()) {
				System.out.println(createFormat(i, true) + f.getName());
				File[] list = f.listFiles();
				for (File entry : list) {
					if (entry.isDirectory()) {
						fileSearch(entry, i + 1);
					} else {
						System.out.println(createFormat(i, false) + entry.getName());
					}
				}
			}
			if (f.isFile()) {
				System.out.println(createFormat(i, false) + f.getName());
			}
		} else {
			System.out.println(f.getName() + " is not exist.");
		}
	}

	public static String createFormat(int index, boolean flg) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < index; i++) {
			sb.append("   ");
		}
		if (flg) {
			sb.append("|--");
		} else {
			sb.append("   ");
		}
		return sb.toString();
	}

}
