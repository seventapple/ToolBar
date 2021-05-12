package com.wang.tool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClassCompareTest {
	public static void main(String[] args) {
		//Comparable
		sort1();
        //Comparator
		sort2();
		//三重判断
		sort3();
	}

	// 需求:flag唯一为true,此情况序列为集合最后
	// 其余元素按时间升顺(null最前)
	public static void sort1() {
		CompareOne c1 = new CompareOne(false, "20200101");
		CompareOne c2 = new CompareOne(false, "20200201");
		CompareOne c3 = new CompareOne(false, "20200301");
		CompareOne c4 = new CompareOne(true, "20200301");
		CompareOne c5 = new CompareOne(false, null);
		List<CompareOne> list = new ArrayList<CompareOne>();
		list.add(c3);
		list.add(c2);
		list.add(c5);
		list.add(c4);
		list.add(c1);
		System.out.println("排序前");
		System.out.println(list);
		System.out.println("排序后");
		Collections.sort(list);
		System.out.println(list);
	}

	// 需求:flag唯一为true,此情况序列为集合最后
	// 其余元素按时间升顺(null最前)
	public static void sort2() {
		CompareTwo c1 = new CompareTwo(false, "20200101");
		CompareTwo c2 = new CompareTwo(false, "20200201");
		CompareTwo c3 = new CompareTwo(false, "20200301");
		CompareTwo c4 = new CompareTwo(true, "20200301");
		CompareTwo c5 = new CompareTwo(false, null);
		List<CompareTwo> list = new ArrayList<CompareTwo>();
		list.add(c3);
		list.add(c2);
		list.add(c5);
		list.add(c4);
		list.add(c1);
		System.out.println("排序前");
		System.out.println(list);
		Collections.sort(list, new CompareTwoOrder());
		System.out.println("排序后");
		System.out.println(list);
	}

	// 需求:lastFlg唯一为true,此情况序列为集合最后
	// lastFlgEver复数情况为true,第二优先级别靠后,同级别按时间升顺
	// date按时间升顺(null最前)
	public static void sort3() {
		CompareThree c1 = new CompareThree("c1", false, false, null);
		CompareThree c2 = new CompareThree("c2", false, false, "20200101");
		CompareThree c3 = new CompareThree("c3", false, false, "20200125");
		CompareThree c4 = new CompareThree("c4", false, true, "20200119");
		CompareThree c5 = new CompareThree("c5", false, true, "20200120");
		CompareThree c6 = new CompareThree("c6", false, true, "20200131");
		CompareThree c7 = new CompareThree("c7", true, true, "20200130");
		List<CompareThree> list = new ArrayList<CompareThree>();
		list.add(c6);
		list.add(c3);
		list.add(c1);
		list.add(c7);
		list.add(c5);
		list.add(c4);
		list.add(c2);
		System.out.println("排序前");
		System.out.println(list);
		Collections.sort(list);
		System.out.println("排序后");
		System.out.println(list);
	}
}

class CompareThree implements Comparable<CompareThree> {
	private String name;
	private boolean lastFlg;
	private boolean lastFlgEver;
	private String date;

	public CompareThree(String name, boolean lastFlg, boolean lastFlgEver, String date) {
		super();
		this.name = name;
		this.lastFlg = lastFlg;
		this.lastFlgEver = lastFlgEver;
		this.date = date;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(CompareThree bean) {
		if (this.lastFlg) {
			return 1;
		}
		if (bean.lastFlg) {
			return -1;
		}
		if (this.lastFlgEver && bean.lastFlgEver) {
			return this.date.compareTo(bean.date);
		}
		if (this.lastFlgEver) {
			return 1;
		}
		if (bean.lastFlgEver) {
			return -1;
		}
		if (this.date == null) {
			return -1;
		}
		if (bean.date == null) {
			return 1;
		}
		return this.date.compareTo(bean.date);
	}

}

class CompareTwoOrder implements Comparator<CompareTwo> {

	@Override
	public int compare(CompareTwo o1, CompareTwo o2) {
		if (o1.isFlag()) {
			return 1;
		}
		if (o2.isFlag()) {
			return -1;
		}
		if (o1.getDate() == null) {
			return -1;
		}
		if (o2.getDate() == null) {
			return 1;
		}
		return o1.getDate().compareTo(o2.getDate());
	}

}

class CompareTwo {

	public CompareTwo(boolean flag, String date) {
		super();
		this.flag = flag;
		this.date = date;
	}

	private boolean flag;
	private String date;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return flag + " + " + date;
	}
}

class CompareOne implements Comparable<CompareOne> {

	public CompareOne(boolean flag, String date) {
		super();
		this.flag = flag;
		this.date = date;
	}

	private boolean flag;
	private String date;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return flag + " + " + date;
	}

	@Override
	public int compareTo(CompareOne bean) {
		if (this.flag) {
			return 1;
		}
		if (bean.flag) {
			return -1;
		}
		if (this.date == null) {
			return -1;
		}
		if (bean.date == null) {
			return 1;
		}
		return this.date.compareTo(bean.date);
	}

}
