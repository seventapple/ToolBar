package com.wang.GOF23.decorator;

/**
 * 抽象组件
 * 
 * @author 王李点儿
 *
 */
public interface ICar {
	public abstract void move();
}

/**
 * 具体构件对象(真实对象)
 * 
 * @author 王李点儿
 *
 */
class Car implements ICar {
	@Override
	public void move() {
		System.out.println("小汽车开始跑.");
	}
}

/**
 * 装饰器角色
 * 
 * @author 王李点儿
 *
 */
class SuperCar implements ICar {

	private ICar car;

	public SuperCar(ICar car) {
		this.car = car;
	}

	@Override
	public void move() {
		car.move();
	}

}

class FlyCar extends SuperCar {

	public FlyCar(ICar car) {
		super(car);
	}

	public void fly() {
		System.out.println("I belive I can fly.");
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		super.move();
		fly();
	}
}

class WaterCar extends SuperCar {

	public WaterCar(ICar car) {
		super(car);
	}

	public void swim() {
		System.out.println("I belive I can swim.");
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		super.move();
		swim();
	}
}