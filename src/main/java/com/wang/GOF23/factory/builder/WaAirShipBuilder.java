package com.wang.GOF23.factory.builder;

public class WaAirShipBuilder implements AirShipBuilder {

	@Override
	public Engine buildEngine() {
		return new Engine("王牌发动机");
	}

	@Override
	public OrbitalModulr buildOrbitalModulr() {
		return new OrbitalModulr("王牌轨道舱");
	}

	@Override
	public Escape buildEscape() {
		return new Escape("王牌逃生舱");
	}

}
