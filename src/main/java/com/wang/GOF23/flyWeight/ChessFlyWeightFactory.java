package com.wang.GOF23.flyWeight;

import java.util.HashMap;
import java.util.Map;

public class ChessFlyWeightFactory {
	private static Map<String, ChessFlyWeight> map = new HashMap<String, ChessFlyWeight>();

	public static ChessFlyWeight getChess(String key) {
		ChessFlyWeight value = map.get(key);
		if (value == null) {
			value = new ConcreteChess(key);
			map.put(key, value);
		}
		return value;
	}
}
