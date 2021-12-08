package com.wang.jdbc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//Date
public class Demo04 {

	public static void main(String[] args) {
		test1();
	}

	public static long str2Date(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = sdf.parse(dateStr);
			return date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static void test1() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/testdb?characterEncoding=utf-8&useSSL=false", "root", "123456");
			String sql = "select * from t_user where tdate >= ? and tdate <= ?;";
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setObject(1, new Date(str2Date("2021-11-15 00:00:00")));
			stmt.setObject(2, new Date(str2Date("2021-11-15 23:00:00")));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println(String.format("user = id:%s, name:%s, tdate:%s", rs.getInt("id"),
						rs.getString("username"), rs.getDate("tdate")));
			}
			System.out.println("======================================");
			String sql2 = "select * from t_user where ttimestamp > ? and ttimestamp < ?;";
			PreparedStatement stmt2 = conn.prepareStatement(sql2);
			stmt2.setObject(1, new Timestamp(str2Date("2021-11-15 16:00:00")));
			stmt2.setObject(2, new Timestamp(str2Date("2021-11-15 21:00:00")));
			ResultSet rs2 = stmt2.executeQuery();
			while (rs2.next()) {
				System.out.println(String.format("user = id:%s, name:%s, timestamp:%s", rs2.getInt("id"),
						rs2.getString("username"), rs2.getTimestamp("ttimestamp")));
			}
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
