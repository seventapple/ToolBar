package com.wang.jdbc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}

	public static void test1() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/testdb?characterEncoding=utf-8&useSSL=false", "root", "123456");
			//一般方法处理sql
			try (Statement stmt = conn.createStatement()) {
				stmt.execute("insert into t_user values (11, 'li')");
			}
			//预处理
			String sql = "insert into t_user values (?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, 12);
			ps.setString(2, "wang");
			ps.execute();
			ps.setObject(1, 13);
			ps.setObject(2, "wei");
			ps.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
