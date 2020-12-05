package com.myclass.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myclass.db.JDBCConnection;
import com.myclass.dto.UserDto;
import com.myclass.entity.User;

public class UserRepository {

	public List<User> findAll() {
		List<User> users = new ArrayList<User>();
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM users");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				User user = new User(resultSet.getInt("id"), resultSet.getString("email"),
						resultSet.getString("password"), resultSet.getString("fullname"),
						resultSet.getString("avatar"), resultSet.getInt("role_id"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public List<UserDto> findAllUserRole() {
		List<UserDto> users = new ArrayList<UserDto>();
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(
					"SELECT u.id, u.email, u.password, u.fullname, u.avatar, r.description FROM users u JOIN roles r ON u.role_id = r.id");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				UserDto userDto = new UserDto(resultSet.getInt("id"), resultSet.getString("email"),
						resultSet.getString("password"), resultSet.getString("fullname"), resultSet.getString("avatar"),
						resultSet.getString("description"));
				users.add(userDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public User findById(int id) {
		User user = new User();
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				user = new User(resultSet.getInt("id"), resultSet.getString("email"), resultSet.getString("password"),
						resultSet.getString("fullname"), resultSet.getString("avatar"), resultSet.getInt("role_id"));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User findByEmail(String email) {
		User user = null;
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM users WHERE email = ?");
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				user = new User(resultSet.getInt("id"), resultSet.getString("email"), resultSet.getString("password"),
						resultSet.getString("fullname"), resultSet.getString("avatar"), resultSet.getInt("role_id"));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public int save(User user) {
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(
					"INSERT INTO users(email, password, fullname, avatar, role_id) VALUES(?, ?, ?, ?, ?)");
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFullname());
			statement.setString(4, user.getAvatar());
			statement.setInt(5, user.getRoleId());
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int update(User user) {
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(
					"UPDATE users SET email = ?, password = ?, fullname = ?, avatar = ?, role_id = ? WHERE id = ? ");
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFullname());
			statement.setString(4, user.getAvatar());
			statement.setInt(5, user.getRoleId());
			statement.setInt(6, user.getId());

			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
