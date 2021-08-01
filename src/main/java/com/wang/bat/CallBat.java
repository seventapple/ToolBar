package com.wang.bat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CallBat {
//	cmd /c dir 是执行完dir命令后关闭命令窗口.
//	cmd /b dir 是弹框执行完dir命令.
//	cmd /k dir 是执行完dir命令后不关闭命令窗口.
//	cmd /c start dir 会打开一个新窗口后执行dir指令, 原窗口会关闭.
//	cmd /k start dir 会打开一个新窗口后执行dir指令, 原窗口不会关闭.
//	Runtime.getRuntime().exec("cmd /c E:\\001Git\\BatRun\\bat\\run.bat");
	public static void main(String[] args) {
		try {
			System.out.println("获取环境变量:" + System.getenv("JAVA_HOME"));
			System.out.println("获取操作系统:" + System.getProperty("os.name"));
			String[] param = new String[] { "123", "234" };
			String cmdBase = "cmd /c E:\\001Git\\BatRun\\bat\\run.bat \"%s\" \"%s\" ";
			String cmd = String.format(cmdBase, param);
			System.out.println("cmd:" + cmd);
//			String[] cmds= {"cmd","/c","E:\\001Git\\BatRun\\bat\\run.bat","1","2"};
			Process exec = Runtime.getRuntime().exec(cmd);
			BufferedReader reader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
			String msgString;
			while ((msgString = reader.readLine()) != null) {
				System.out.println("msg : " + msgString);
			}
			exec.waitFor();
			int exitValue = exec.exitValue();
			System.out.println("resultInt : " + exitValue);

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
