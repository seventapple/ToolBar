package com.wang.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorTest {

	/**
	 * 线程池ExecutorService练习
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, Map<Integer, Integer>> data = new HashMap<String, Map<Integer, Integer>>();
		makeData(data);
		ExecutorService executor = Executors.newFixedThreadPool(2);
		// 避免提交阻塞
		CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);
		for (Entry<String, Map<Integer, Integer>> entry : data.entrySet()) {
			completionService.submit(callableTask(entry));
		}
		int size = data.size();
		for (int i = 0; i < size; i++) {
			try {
				Future<String> future = completionService.take();
				if (future.isDone()) {
					System.out.println(future.get());
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

	}

	private static Callable<String> callableTask(Entry<String, Map<Integer, Integer>> entry) {
		Callable<String> task = new Callable<String>() {

			@Override
			public String call() throws Exception {
				StringBuffer result = new StringBuffer(entry.getKey());
				Map<Integer, Integer> values = entry.getValue();
				for (Entry<Integer, Integer> ent : values.entrySet()) {
					Thread.sleep(ent.getKey() * 100);
					int cnt = ent.getKey() * ent.getValue();
					result.append("\r\n");
					result.append(ent.getKey()).append(" * ").append(ent.getValue());
					result.append(" = ").append(cnt);
				}
				return result.toString();
			}

		};

		return task;
	}

	public static void makeData(Map<String, Map<Integer, Integer>> data) {
		data.put("A", new HashMap<Integer, Integer>());
		data.put("B", new HashMap<Integer, Integer>());
		data.put("C", new HashMap<Integer, Integer>());
		data.get("A").put(1, 3);
		data.get("A").put(2, 3);
		data.get("A").put(3, 3);
		data.get("B").put(1, 4);
		data.get("B").put(2, 4);
		data.get("B").put(3, 4);
		data.get("B").put(4, 4);
		data.get("C").put(1, 2);
		data.get("C").put(2, 2);
	}
}
