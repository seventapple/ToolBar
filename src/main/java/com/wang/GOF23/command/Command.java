package com.wang.GOF23.command;

public interface Command {
	//可根据不同需求,设计多个方法
	public void execute();
}

class RealCommand implements Command {

	private Receiver receiver;

	public RealCommand(Receiver receiver) {
		super();
		this.receiver = receiver;
	}

	@Override
	public void execute() {
		receiver.action();
	}

}
