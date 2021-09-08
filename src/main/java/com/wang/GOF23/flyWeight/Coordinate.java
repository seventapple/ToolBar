package com.wang.GOF23.flyWeight;

/**
 * 外部状态类
 * 
 * @author 王李点儿
 *
 */
public class Coordinate {
	int x, y;

	public Coordinate(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

}
