package com.wang.regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo01 {

	public static void main(String[] args) {
		testNormal();
		testGroup();
		testTi();
	}

	// 测试简单字符串
	public static void testNormal() {
		System.out.println("===简单测试===");
		String target = "123targer.456zzz";
		// java 1\ 变 2\\
		Pattern p = Pattern.compile("\\w+");
		Matcher m = p.matcher(target);
		// show result
		System.out.println("显示结果:");
		System.out.println(m.matches());
		System.out.println(target.matches("\\w+"));
		// matches()会消耗匹配次数
		Matcher m2 = p.matcher(target);
		System.out.println("查找与该模式匹配的子序列:");
		int i = 1;
		while (m2.find()) {
			System.out.println("第" + (i++) + "次结果:" + m2.group());
		}
	}

	// 正则中的分组处理
	public static void testGroup() {
		System.out.println("===分组===");
		Pattern p = Pattern.compile("([a-z]+)([0-9]+)");
		Matcher m = p.matcher("aaa232*sss445*sdsd1212");
		int i = 1;
		while (m.find()) {
			System.out.println("第" + (i++) + "次结果:" + m.group());
			System.out.println("第" + (i) + "次组1结果:" + m.group(1));
			System.out.println("第" + (i) + "次组2结果:" + m.group(2));
		}
	}
	
	// 正则中的替换
	public static void testTi() {
		System.out.println("===替换===");
		String tar="aaa232sss445sdsd1212";
		Pattern p = Pattern.compile("[0-9]");
		Matcher m = p.matcher(tar);
		System.out.println("替换前:"+m.replaceAll("*"));
		System.out.println("替换前:"+tar);
		System.out.println("===分割===");
		String tar2="aaa232sss445sdsd1212";
		System.out.println("分割:"+Arrays.toString(tar2.split("\\d+")));
	}
}
