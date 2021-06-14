package com.wang.internet.httpserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class WebHandler extends DefaultHandler {

	private List<ServletEntity> entitys;
	private List<ServletMapping> maps;
	private String tag;
	private boolean isMap;
	private ServletEntity entity;
	private ServletMapping map;

	@Override
	public void startDocument() throws SAXException {
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (null != qName) {
			tag = qName;
			if ("web-app".equals(qName)) {
				entitys = new ArrayList<ServletEntity>();
				maps = new ArrayList<ServletMapping>();
			}
			if ("servlet".equals(qName)) {
				entity = new ServletEntity();
				isMap = false;
			}
			if ("servlet-mapping".equals(qName)) {
				map = new ServletMapping();
				isMap = true;
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String value = new String(ch, start, length);
		if (null != tag) {
			if ("servlet-name".equals(tag)) {
				if (isMap) {
					map.setName(value);
				} else {
					entity.setName(value);
				}
			}
			if ("servlet-class".equals(tag)) {
				entity.setClz(value);
			}
			if ("url-pattern".equals(tag)) {
				map.getUrls().add(value);
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("servlet".equals(qName)) {
			entitys.add(entity);
		}
		if ("servlet-mapping".equals(qName)) {
			maps.add(map);
		}
		tag = null;
	}

	@Override
	public void endDocument() throws SAXException {
	}

	public List<ServletEntity> getEntitys() {
		return entitys;
	}

	public void setEntitys(List<ServletEntity> entitys) {
		this.entitys = entitys;
	}

	public List<ServletMapping> getMaps() {
		return maps;
	}

	public void setMaps(List<ServletMapping> maps) {
		this.maps = maps;
	}

}
