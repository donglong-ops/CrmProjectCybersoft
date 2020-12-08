package com.myclass.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myclass.db.JDBCConnection;
import com.myclass.entity.Role;

public class RoleRepository {

	public List<Role> findAll() {
		final String QUERY = "SELECT * FROM roles";

		List<Role> roles = new ArrayList<Role>();
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Role role = new Role(resultSet.getInt("id"), resultSet.getString("name"),
						resultSet.getString("description"));
				roles.add(role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roles;
	}

	public Role findById(int id) {
		final String QUERY = "SELECT * FROM roles WHERE id = ?";
		Role role = new Role();
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				role = new Role(resultSet.getInt("id"), resultSet.getString("name"),resultSet.getString("description"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return role;
	}

	public int save(Role role) {
		final String QUERY = "INSERT INTO roles(name, description) VALUES(?, ?)";
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY);
			statement.setString(1, role.getName());
			statement.setString(2, role.getDescription());
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	public int deleteRole(int idDelete) {
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement("Delete from roles WHERE id = ? ");
			statement.setInt(1, idDelete);
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public void updateRole(Role dto) {
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement("Update roles set name = ? , description = ? WHERE id = ? ");
			statement.setString(1, dto.getName());
			statement.setString(2, dto.getDescription());
			statement.setInt(3, dto.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
