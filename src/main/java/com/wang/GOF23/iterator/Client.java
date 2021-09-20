package com.wang.GOF23.iterator;

public class Client {
	public static void main(String[] args) {
		MyList list = new MyList();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		list.add("dd");
		list.add(1);
		MyIterator iterator = list.getIterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
