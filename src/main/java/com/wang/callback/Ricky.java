package com.wang.callback;

public class Ricky implements Student {

	@Override
	public void resolveQuestion(Callback callback) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		callback.tellAnswer(3);
	}

}
