package com.wang.tool;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;

public class MergeInputStream {
	public static void merge2() {
		try {
			FileInputStream fis = new FileInputStream("e:/TestFile/111.txt");
			int size = fis.available();
			String msg = "size:" + size + "\r\n";
			ByteArrayInputStream bis = new ByteArrayInputStream(msg.getBytes());
			InputStream sis = new SequenceInputStream(bis, fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(sis));
			String reMsg = br.readLine();
			System.out.println(reMsg);
			FileOutputStream fos = new FileOutputStream("e:/TestFile/444.txt");
			int len;
			while ((len = br.read()) != -1)
				fos.write(len);
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("over");
	}
}
