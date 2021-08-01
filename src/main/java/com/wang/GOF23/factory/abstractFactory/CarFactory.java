package com.wang.GOF23.factory.abstractFactory;

public interface CarFactory {
	Seat createSeat();

	Engine createEngine();
}

class LowCarFactory implements CarFactory {

	@Override
	public Seat createSeat() {
		return new LowSeat();
	}

	@Override
	public Engine createEngine() {
		// TODO Auto-generated method stub
		return new LowEngine();
	}

}

class LuxuryCarFactory implements CarFactory {

	@Override
	public Seat createSeat() {
		return new LuxurySeat();
	}

	@Override
	public Engine createEngine() {
		// TODO Auto-generated method stub
		return new LuxuryEngine();
	}

}