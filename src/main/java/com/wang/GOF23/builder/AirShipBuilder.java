package com.wang.GOF23.builder;

//构造器接口
//StringBuilder DomBuilder SaxBuilder
public interface AirShipBuilder {
	Engine buildEngine();

	OrbitalModulr buildOrbitalModulr();

	Escape buildEscape();
}
