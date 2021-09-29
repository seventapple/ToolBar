package com.wang.GOF23.state;

public class HomeContext {
	private State state;

	public HomeContext() {
	};

	public HomeContext(State state) {
		this.state = state;
		state.handle();
	}

	public void setState(State state) {
		this.state = state;
		state.handle();
	}
}
