package com.wang.jdbc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//ResultSet
public class Demo02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}

	// executeQuery() select语句 返回ResultSet
	// executeUpdate() insert/update/delete语句 返回执行成功行数
	public static void test1() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/testdb?characterEncoding=utf-8&useSSL=false", "root", "123456");
			String sql = "insert into t_user values (?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setInt(1, 14);
//			ps.setString(2, "wang");
//			int ret = ps.executeUpdate();
//			System.out.println(ret);
			// ResultSet
			String sql2 = "select * from t_user";
			ps = conn.prepareStatement(sql2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(String.format("user = id: %s, name: %s", rs.getInt("id"), rs.getString("username")));
				System.out.println(String.format("user = id: %s, name: %s", rs.getInt(1), rs.getString(2)));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
