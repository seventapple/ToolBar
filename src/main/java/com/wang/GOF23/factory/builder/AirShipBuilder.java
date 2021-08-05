package com.wang.GOF23.factory.builder;

//构造器接口
//StringBuilder DomBuilder SaxBuilder
public interface AirShipBuilder {
	Engine buildEngine();

	OrbitalModulr buildOrbitalModulr();

	Escape buildEscape();
}
