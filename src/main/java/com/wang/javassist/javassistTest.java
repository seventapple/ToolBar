package com.wang.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;

//字节码操作练习 类库使用Javassist
public class javassistTest {

	public static void main(String[] args) throws Exception {
		createClass();
	}

	public static void createClass() throws Exception {
		// 获取实例
		ClassPool pool = ClassPool.getDefault();
		// 生成一个类
		CtClass cc = pool.makeClass("com.wang.javassist.bean.Emp");
		// 生成字段
		CtField field = CtField.make("private String empname;", cc);
		cc.addField(field);
		// 生成方法
		CtMethod method = CtMethod.make("public String getEmpName(){return empname;}", cc);
		cc.addMethod(method);
		CtMethod method2 = CtMethod.make("public void setEmpName(String name){empname=name;}", cc);
		cc.addMethod(method2);
		// 添加构造器
		// 构造器基本类型使用CtClass.intType;或者无参不写
		CtConstructor cons = new CtConstructor(new CtClass[] { pool.get("java.lang.String") }, cc);
		cons.setBody("{this.empname=empname;}");

		// 输出类至指定位置
		cc.writeFile("e:/TestFile/1");
		System.out.println("output success");
		// 查看class文件(可使用XJad,jd-gui)
	}

}
