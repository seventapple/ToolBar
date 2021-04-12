package com.wang.io;

public class Voice {
	private int voice = 10;

	public int getVoice() {
		return voice;
	}

	public void setVoice(int voice) {
		this.voice = voice;
	}

	public void say() {
		System.out.println(voice);
	}
}
