package com.wang.jdbc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Batch
public class Demo03 {

	public static void main(String[] args) {
		test1();
	}

	public static void test1() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/testdb?characterEncoding=utf-8&useSSL=false", "root", "123456");
			String sql = "insert into t_user values (20, 'gao')";
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			for(int i=0;i<3;i++) {
				stmt.addBatch(sql);
			}
			int[] ret = stmt.executeBatch();
			conn.commit();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
