package com.wang.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.wang.module.ObjBean;

//其他流
//引用类型(实现Seralizable接口)
//反序列化 输入流ObjectInputStream readObject()
//序列化 输出流 ObjectOutputStream writeObject()
//不需要序列化的字段用transient修饰
public class Demo07 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		writeObj("E:\\TestFile\\objStream.obj");
		readObj("E:\\TestFile\\objStream.obj");
	}

	public static void readObj(String filePath) {
		File file = new File(filePath);
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			Object robj = ois.readObject();
			if (robj instanceof ObjBean) {
				System.out.println((ObjBean) robj);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("read finish.");
	}

	public static void writeObj(String filePath) {
		File file = new File(filePath);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));) {
			ObjBean param = new ObjBean();
			param.setStr1("test1");
			param.setStr2("test2");
			oos.writeObject(param);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("write finish.");
	}

}
