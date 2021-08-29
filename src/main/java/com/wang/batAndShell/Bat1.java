package com.wang.batAndShell;

public class Bat1 {

	public static void main(String[] args) {
		System.out.println("run bat1 successful.");
		if (args.length != 0) {
			int i = 1;
			for (String str : args) {
				System.out.println("param" + (i++) + ":" + str);
			}
			System.exit(0);
		} else {
			System.out.println("no params");
			System.exit(1);
		}
	}

}
