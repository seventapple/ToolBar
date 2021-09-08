package com.wang.GOF23.flyWeight;

/**
 * 享元类
 * 
 * @author 王李点儿
 *
 */
public interface ChessFlyWeight {

	void setColor(String color);

	String getColor();

	void display(Coordinate cde);
}

class ConcreteChess implements ChessFlyWeight {

	private String color;

	public ConcreteChess(String color) {
		super();
		this.color = color;
	}

	@Override
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public void display(Coordinate cde) {
		System.out.println("color : " + color);
		System.out.println("conrdinate : " + cde.getX() + " - " + cde.getY());
	}

}
