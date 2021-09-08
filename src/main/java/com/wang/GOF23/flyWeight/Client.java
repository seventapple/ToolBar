package com.wang.GOF23.flyWeight;

public class Client {
	public static void main(String[] args) {
		ChessFlyWeight chess1 = ChessFlyWeightFactory.getChess("black");
		ChessFlyWeight chess2 = ChessFlyWeightFactory.getChess("black");
		System.out.println(chess1);
		System.out.println(chess2);
		chess1.display(new Coordinate(10, 10));
		chess2.display(new Coordinate(10, 10));
		System.out.println(chess1);
		System.out.println(chess2);
	}
}
