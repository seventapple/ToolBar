package com.wang.GOF23.mediator;

import java.util.HashMap;
import java.util.Map;

public interface MyMediator {
	void register(String dname, Department department);

	void command(String dname);
}

class Present implements MyMediator {

	private Map<String, Department> map = new HashMap<String, Department>();

	@Override
	public void register(String dname, Department department) {
		System.out.println("中介注册");
		map.put(dname, department);
	}

	@Override
	public void command(String dname) {
		Department department = map.get(dname);
		if (department != null) {
			System.out.println("中介安排");
			department.selfAction();
		}
	}

}
