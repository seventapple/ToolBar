package com.wang.callback;

public class Teacher implements Callback {

	private Student student;

	public Teacher(Student student) {
		super();
		this.student = student;
	}

	public void askQuestion() {
		student.resolveQuestion(this);
	}

	@Override
	public void tellAnswer(int answer) {
		System.out.println("You answer is " + answer);
	}

}
