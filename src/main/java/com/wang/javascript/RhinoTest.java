package com.wang.javascript;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class RhinoTest {
	public static void main(String[] args) throws ScriptException, NoSuchMethodException, IOException {
		// 获取脚本引擎对象
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine engine = sem.getEngineByName("javascript");
		// 定义变量,存储于引擎上下文中
		// 执行脚本
		engine.put("msg", "this is the first msg.");
		Object msg1 = engine.get("msg");
		System.out.println(msg1);
		engine.eval("msg='this is the second msg';");
		Object msg2 = engine.get("msg");
		System.out.println(msg2);
		String str = "var user = {name:'wang',age:30,hobby:['novel','movie','music']};";
		str += "print(user.hobby);";
		engine.eval(str);

		// 定义函数
		String func = "function add(a,b) {return a+b;}";
		// 执行js函数
		engine.eval(func);
		Invocable jsInvoke = (Invocable) engine;
		Object result = jsInvoke.invokeFunction("add", new Object[] { 11, 22 });
		System.out.println(result);

		// 导入java包,使用包中的Java类
		// java 1.6
		// String jsCode = "importPackage(java.util); var
		// list=Arrays.asList([\"Abc\",\"dEf\"]);";
		// java 1.8
		String jsCode = "var list=java.util.Arrays.asList([\"Abc\",\"dEf\"]);";
		engine.eval(jsCode);
		List<String> list = (List<String>) engine.get("list");
		System.out.println(list);

		// 读取js文件(项目中)
		URL url = RhinoTest.class.getClassLoader().getResource("com/wang/javascript/a.js");
		System.out.println(url.getPath());
		FileReader fr = new FileReader(url.getPath());
		engine.eval(fr);
		fr.close();
	}
}
