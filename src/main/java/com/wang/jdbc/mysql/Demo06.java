package com.wang.jdbc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//ORM Object Relationship Mapping
public class Demo06 {

	public static void main(String[] args) {
		test1();
		test2();
		test3();
	}

	public static void test1() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 读取用SQL
			String sql = "SELECT * FROM t_user WHERE id > ?; ";
			try (Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/testdb?characterEncoding=utf-8&useSSL=false", "root", "123456");
					PreparedStatement ps = conn.prepareStatement(sql);) {
				// 读取
				ps.setObject(1, 11);
				ResultSet rs = ps.executeQuery();
				List<Object[]> list = new ArrayList<Object[]>();
				while (rs.next()) {
					Object[] objs = new Object[4];
					objs[0] = rs.getObject(1);
					objs[1] = rs.getObject(2);
					objs[2] = rs.getObject(3);
					objs[3] = rs.getObject(4);
					list.add(objs);
				}
				System.out.println("使用Object数组");
				for (Object[] objs : list) {
					System.out.println(String.format("%s,%s,%s,%s", objs[0], objs[1], objs[2], objs[3]));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	public static void test2() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 读取用SQL
			String sql = "SELECT * FROM t_user WHERE id > ?; ";
			try (Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/testdb?characterEncoding=utf-8&useSSL=false", "root", "123456");
					PreparedStatement ps = conn.prepareStatement(sql);) {
				// 读取
				ps.setObject(1, 11);
				ResultSet rs = ps.executeQuery();
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				while (rs.next()) {
					ResultSetMetaData metaDate = rs.getMetaData();
					int columnCount = metaDate.getColumnCount();
					String[] columnNames = new String[columnCount];
					for (int i = 1; i <= columnCount; i++) {
						columnNames[i - 1] = metaDate.getColumnName(i);
					}
					Map<String, Object> map = new LinkedHashMap<String, Object>();
					for (int i = 0; i < columnCount; i++) {
						map.put(columnNames[i], rs.getObject(i + 1));
					}
					list.add(map);
				}
				System.out.println("使用Map");
				for (Map<String, Object> map : list) {
					for (String key : map.keySet()) {
						System.out.print(key + ":" + map.get(key));
						System.out.print("  ");
					}
					System.out.print("\r\n");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	public static void test3() {
		System.out.println("通过JavaBean实现,利用反射对应赋值");
	}
}
