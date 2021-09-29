package com.wang.GOF23.state;

public interface State {
	public void handle();
}

class FreeState implements State {
	@Override
	public void handle() {
		System.out.println("房间空闲");
	}
}
class BookedState implements State {
	@Override
	public void handle() {
		System.out.println("房间已预订");
	}
}