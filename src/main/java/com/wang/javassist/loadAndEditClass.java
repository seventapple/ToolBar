package com.wang.javassist;

import java.io.DataOutputStream;
import java.lang.reflect.Method;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;

public class loadAndEditClass {
	public static void main(String[] args) throws Exception {
		// 获取类的基本参数
		// test1();
		// 添加类的方法
		// test2();
		// 修改类的即存方法
		test3();
		// 创建属性(参考前文)
		// 构造方法
		// 注释
	}

	/**
	 * 修改类的即存方法
	 * 
	 * @throws Exception
	 */
	public static void test3() throws Exception {
		// 获取实例
		ClassPool pool = ClassPool.getDefault();
		// 取得即存一个类
		CtClass cc = pool.get("com.wang.javassist.Example");
		CtMethod method = cc.getDeclaredMethod("getMsg", new CtClass[] { CtClass.intType, CtClass.intType });
		method.insertBefore("System.out.println(\"insert before start, param 1:\"+$args[0]);");
		method.insertAt(9, "int b = 3; $1=b;System.out.println(\"change p1->3.\");");
		method.insertAfter("System.out.println(\"insert after end, param 2:\"+$2);");
		Class clazz = cc.toClass();
		Object obj = clazz.newInstance();
		Method med = clazz.getDeclaredMethod("getMsg", int.class, int.class);
		Object ret = med.invoke(obj, 1, 10);
		System.out.println(ret);
	}

	/**
	 * 添加类的方法
	 * 
	 * @throws Exception
	 */
	public static void test2() throws Exception {
		// 获取实例
		ClassPool pool = ClassPool.getDefault();
		// 取得即存一个类
		CtClass cc = pool.get("com.wang.javassist.Example");
		// 生成新方法-1
		System.out.println("生成新方法-1");
		cc.addMethod(CtMethod.make("public void newM(){System.out.println(\"Is new method\");}", cc));
		// 生成新方法-2
		System.out.println("生成新方法-2");
		CtMethod m2 = new CtMethod(CtClass.intType, "newM2",
				new CtClass[] { CtClass.intType, pool.get("java.lang.String") }, cc);
		m2.setModifiers(Modifier.PUBLIC);
		m2.setBody("{System.out.println(\"Is new method II, param 1:\"+$args[1]);return $1;}");
		cc.addMethod(m2);
		Class clazz2 = cc.toClass();
		Object obj2 = clazz2.newInstance();
		Method method = clazz2.getDeclaredMethod("newM", null);
		method.invoke(obj2, null);
		Method method2 = clazz2.getDeclaredMethod("newM2", int.class, String.class);
		Object ret2 = method2.invoke(obj2, 100, "check1,2,3");
		System.out.println("method2 result : " + ret2);

	}

	/**
	 * 获取类的基本参数
	 * 
	 * @throws Exception
	 */
	public static void test1() throws Exception {
		// 获取实例
		ClassPool pool = ClassPool.getDefault();
		// 取得即存一个类
		CtClass cc = pool.get("com.wang.javassist.Example");
		String name = cc.getName();// 获取类名(带路径)
		System.out.println("获取类名(带路径) : " + name);
		String simpleName = cc.getSimpleName();
		System.out.println("获取类名(不带路径) : " + simpleName);// 获取类名(不带路径)
		System.out.println("获取父类 : " + cc.getSuperclass());
		System.out.println("获取接口 : " + cc.getInterfaces());
		byte[] bytecode = cc.toBytecode();// 获得类信息
		System.out.println(new String(bytecode));
		DataOutputStream dos = new DataOutputStream(System.out);
		cc.toBytecode(dos);
	}
}
