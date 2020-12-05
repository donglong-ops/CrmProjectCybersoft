package com.myclass.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
	
	public static Connection getConnection() {
		final String DRIVER = "com.mysql.cj.jdbc.Driver";
		final String URL = "jdbc:mysql://localhost:3306/projectcrm";
		final String USERNAME = "root";
		final String PASSWORD = "Long0147852369";
		
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("KHÔNG TÌM THẤY DRIVER");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SAI THÔNG TIN KẾT NỐI DB");
		}
		return null;
	}
}
