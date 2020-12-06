package com.myclass.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myclass.db.JDBCConnection;
import com.myclass.entity.Job;

public class JobRepository {
	
	public List<Job> findAll() {
		List<Job> Jobs = new ArrayList<Job>();
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM Jobs");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Job job = new Job(resultSet.getInt("id"), resultSet.getString("name"),resultSet.getTimestamp("start_date"),resultSet.getTimestamp("end_date"));
				Jobs.add(job);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Jobs;
	}
	public Job findById(int id) {
		Job job = new Job();
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM jobs WHERE id = ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				job = new Job(resultSet.getInt("id"), resultSet.getString("name"),resultSet.getTimestamp("start_date"),resultSet.getTimestamp("end_date"));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return job;
	}
	public int save(Job job) {
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement("INSERT INTO jobs(id, name, start_date, end_date) VALUES(?, ?, ?, ?)");
			statement.setInt(1, job.getId());
			statement.setString(2, job.getName());
			statement.setTimestamp(3, job.getStart_date());
			statement.setTimestamp(4, job.getStart_date());

			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

}
