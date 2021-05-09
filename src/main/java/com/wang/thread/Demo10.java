package com.wang.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 任务调度 
 * 定期执行 
 * Timer TimerTask schedule(TimerTask task, long delay)
 * schedule(TimerTask task, Date firstTime, long period) 
 * 关联框架:quartz
 * 
 * @author 王李点儿
 *
 */
public class Demo10 {

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			private int cnt = 0;

			@Override
			public void run() {
				System.out.println("count : " + cnt++);
			}
		}, new Date(System.currentTimeMillis() + 1000), 1000);
	}

}
