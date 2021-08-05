package com.wang.GOF23.factory.builder;

public class WaAirShipDriector implements AirShipDriector {

	private AirShipBuilder builder;

	public WaAirShipDriector(AirShipBuilder builder) {
		this.builder = builder;
	}

	@Override
	public AirShip createAirShip() {
		AirShip res = new AirShip();
		Engine engine = builder.buildEngine();
		Escape escape = builder.buildEscape();
		OrbitalModulr orbitalModulr = builder.buildOrbitalModulr();
		res.setEngine(engine);
		res.setEscape(escape);
		res.setOribitalModulr(orbitalModulr);
		return res;
	}

}
