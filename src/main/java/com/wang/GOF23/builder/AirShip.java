package com.wang.GOF23.builder;

//飞船对象
public class AirShip {
	private OrbitalModulr oribitalModulr;
	private Engine engine;
	private Escape escape;

	public void run() {
		System.out.println(oribitalModulr.getName());
		System.out.println(engine.getName());
		System.out.println(escape.getName());
	}

	public OrbitalModulr getOribitalModulr() {
		return oribitalModulr;
	}

	public Engine getEngine() {
		return engine;
	}

	public Escape getEscape() {
		return escape;
	}

	public void setOribitalModulr(OrbitalModulr oribitalModulr) {
		this.oribitalModulr = oribitalModulr;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public void setEscape(Escape escape) {
		this.escape = escape;
	}

}

//轨道舱
class OrbitalModulr {
	private String name;

	public OrbitalModulr(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

//发动机	
class Engine {
	private String name;

	public Engine(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

//逃生舱
class Escape {
	private String name;

	public Escape(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
