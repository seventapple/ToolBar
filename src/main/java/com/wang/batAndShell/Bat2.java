package com.wang.batAndShell;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

//利用ProcessBuilder调用其他程序
public class Bat2 {

	public static void main(String[] args) {
		BufferedReader br = null;
		Process process = null;
		try {
			ProcessBuilder builder = null;
			//本项目路径取得(临时文件生成/删除todo)
			String projectPath = System.getProperty("user.dir");
			//操作系统
			String osName = System.getProperty("os.name").toLowerCase();
			//Bat1项目的环境变量取得
			String targetPath = System.getenv("TEST_BAT1");
			System.out.println("user.dir = " + projectPath);
			System.out.println("os.name = " + osName);
			System.out.println("TEST_BAT1 = " + targetPath);
			if (osName.startsWith("windows")) {
				System.out.println("Windows!!!");
				String desk = targetPath.substring(0, 1);
				//跨盘的情况下,先调转至Bat1所在盘
				builder = new ProcessBuilder("cmd", "/c", desk + ":", "&&", "cd", targetPath, "&&", "bat1.bat");
			} else {
				System.out.println("Linus!!!");
				builder = new ProcessBuilder("sh", targetPath + File.separator + "bat1.sh");
			}
			if (args != null) {
				List<String> command = builder.command();
				for (String str : args) {
					command.add(str);
				}
				for(String com:command) {
					System.out.println("command = "+com);
				}
				builder.command(command);
			}
			process = builder.start();
			//Bat1有输出的情况下,避免阻塞造成执行结果无法取得,接受Bat1的输出(必要时,一并接受异常输出)
			br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String msg;
			while ((msg = br.readLine()) != null) {
				System.out.println("msg = " + msg);
			}
			System.out.println("waitfor result = " + process.waitFor());
			System.out.println("exit result = " + process.exitValue());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					;
				}
			}
			if (process != null) {
				process.destroy();
			}
		}
	}

}
