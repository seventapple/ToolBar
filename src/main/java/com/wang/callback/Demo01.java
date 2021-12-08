package com.wang.callback;

public class Demo01 {

	public static void main(String[] args) {
		Student stu=new Ricky();
		Teacher tea=new Teacher(stu);
		tea.askQuestion();
	}

}
