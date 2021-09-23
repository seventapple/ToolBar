package com.wang.GOF23.command;

//命令发起者
public class Invoker {
	//也可以是List,批处理
	private Command command;

	public Invoker(Command command) {
		super();
		this.command = command;
	}

	public void call() {
		command.execute();
	}

}
