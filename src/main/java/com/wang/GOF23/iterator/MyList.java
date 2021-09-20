package com.wang.GOF23.iterator;

import java.util.ArrayList;
import java.util.List;

public class MyList {
	private List<Object> values = new ArrayList<Object>();

	public void add(Object obj) {
		values.add(obj);
	}

	public void remove(Object obj) {
		values.remove(obj);
	}

	public MyIterator getIterator() {
		return new ConcreteIterator(values);
	}

	class ConcreteIterator implements MyIterator {

		private int cursor;// 定义游标

		private List<Object> its;

		public ConcreteIterator(List<Object> its) {
			super();
			this.its = its;
			cursor = 0;
		}

		@Override
		public Object next() {
			return its.get(++cursor);
		}

		@Override
		public boolean hasNext() {
			return !(cursor == its.size() - 1);
		}

	}
}
