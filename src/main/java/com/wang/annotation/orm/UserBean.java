package com.wang.annotation.orm;

@TableAnno("tbl_user")
public class UserBean {

	@FieldAnno(fieldName = "id", type = "int", length = 10)
	private int id;
	@FieldAnno(fieldName = "user_name", type = "vachar", length = 256)
	private String name;
	@FieldAnno(fieldName = "age", type = "int", length = 3)
	private int age;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
