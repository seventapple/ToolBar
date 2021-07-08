package com.wang.tool;

import java.io.FileReader;
import java.net.URL;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.wang.javascript.RhinoTest;

public class 百家姓转换 {

	private static String msg = "钱孙李钱孙李";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine engine = sem.getEngineByName("javascript");
		URL url = RhinoTest.class.getClassLoader().getResource("com/wang/javascript/bjx.js");
		FileReader fr = new FileReader(url.getPath());
		engine.eval(fr);
		fr.close();
		Invocable jsInvoke = (Invocable) engine;
		jsInvoke.invokeFunction("toUrl", msg);
	}

}
