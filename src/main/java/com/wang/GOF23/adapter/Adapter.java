package com.wang.GOF23.adapter;

/**
 * 适配器(相当于usb和ps/2的转换器)
 * 
 * @author 王李点儿
 *
 */
//与被引用对象建立练习,方法一:继承[extends Adaptee] 
public class Adapter extends Adaptee implements Target {
	@Override
	public void handleReq() {
		// 方法一
		super.request();
	}
}
