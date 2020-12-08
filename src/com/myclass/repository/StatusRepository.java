package com.myclass.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myclass.db.JDBCConnection;
import com.myclass.entity.Status;

public class StatusRepository {
	
	public List<Status> findAll() {
		List<Status> listStatus = new ArrayList<Status>();
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM status");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Status status = new Status(resultSet.getInt("id"), resultSet.getString("name"));
				listStatus.add(status);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listStatus;
	}

}
