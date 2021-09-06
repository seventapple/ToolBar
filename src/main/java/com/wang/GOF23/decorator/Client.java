package com.wang.GOF23.decorator;

public class Client {
	public static void main(String[] args) {
		Car car = new Car();
		car.move();
		System.out.println("新增飞翔功能---------------");
		FlyCar flyCar = new FlyCar(car);
		flyCar.move();
		System.out.println("新增游泳功能---------------");
		WaterCar waterCar = new WaterCar(car);
		waterCar.move();
		System.out.println("新增飞翔功能基础上,增加游泳---------------");
		WaterCar waterCar2 = new WaterCar(flyCar);
		waterCar2.move();
		System.out.println("新增飞翔功能基础上,增加游泳(写法二)---------------");
		WaterCar waterCar3 =new WaterCar(new FlyCar(car));
		waterCar3.move();
	}
}
