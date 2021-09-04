package com.wang.GOF23.composite;

public class Client {

	public static void main(String[] args) {
		AbstractFile f1, f2, f3;
		f1 = new TextFile("小说1.txt");
		f2 = new TextFile("小说2.txt");
		f3 = new ImageFile("dianying.mp4");

		f1.killVirus();
		System.out.println("======================");
		// 自带递归
		Folder f4, f5;
		f4 = new Folder("folder1");
		f5 = new Folder("folder2");
		f4.add(f1);
		f4.add(f2);
		f5.add(f3);
		f4.add(f5);
		f4.killVirus();
	}

}
