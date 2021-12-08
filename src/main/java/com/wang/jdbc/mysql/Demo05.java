package com.wang.jdbc.mysql;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//CLOB & BLOB
public class Demo05 {

	public static void main(String[] args) {
		test1();
	}

	public static void test1() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 存储用SQL
			String sql = "INSERT INTO t_user (id, username, tclob, tblob) VALUES (?,?,?,?) ";
			// 读取用SQL
			String sql2 = "SELECT * FROM t_user WHERE id = ?; ";
			try (Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/testdb?characterEncoding=utf-8&useSSL=false", "root", "123456");
					PreparedStatement stmt = conn.prepareStatement(sql);
					PreparedStatement stmt2 = conn.prepareStatement(sql2);
					FileOutputStream fos = new FileOutputStream(new File("E:\\TestFile\\1-out.txt"));) {
				// 存储
				stmt.setObject(1, 7);
				stmt.setObject(2, "zhang3");
				stmt.setClob(3, new FileReader(new File("E:\\TestFile\\1.txt")));
				stmt.setBlob(4, new FileInputStream(new File("E:\\TestFile\\1.txt")));
				stmt.execute();
				// 读取
				stmt2.setObject(1, 7);
				ResultSet rs = stmt2.executeQuery();
				while (rs.next()) {
					Clob clob = rs.getClob("tclob");
					Reader reader = clob.getCharacterStream();
					int temp;
					while ((temp = reader.read()) != -1) {
						System.out.print((char) temp);
					}
					System.out.println("=============================");
					Blob blob = rs.getBlob("tblob");
					InputStream is = blob.getBinaryStream();
					byte[] data = new byte[128];
					while ((temp = is.read(data)) != -1) {
						fos.write(data, 0, temp);
					}
					fos.flush();
					System.out.println("read over");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
}
