package com.wang.GOF23.factory.builder;

//建造者模式 构造器与装配解耦
public class Client {
	public static void main(String[] args) {
		AirShipDriector driector = new WaAirShipDriector(new WaAirShipBuilder());
		AirShip air = driector.createAirShip();
		air.run();
	}
}
