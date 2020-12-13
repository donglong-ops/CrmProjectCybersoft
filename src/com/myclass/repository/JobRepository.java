package com.myclass.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myclass.db.JDBCConnection;
import com.myclass.dto.JobDto;
import com.myclass.entity.Job;

public class JobRepository {
	
	public List<Job> findAll() {
		List<Job> Jobs = new ArrayList<Job>();
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM Jobs");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Job job = new Job(resultSet.getInt("id"), resultSet.getString("name"),resultSet.getDate("start_date"),resultSet.getDate("end_date"));
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
				job = new Job(resultSet.getInt("id"), resultSet.getString("name"),resultSet.getDate("start_date"),resultSet.getDate("end_date"));
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
			PreparedStatement statement = conn.prepareStatement("INSERT INTO jobs(name, start_date, end_date) VALUES(?, ?, ?)");
			statement.setString(1, job.getName());
			statement.setDate(2, job.getStart_date());
			statement.setDate(3, job.getEnd_date());

			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	public int deleteJob(int idDelete) {
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement("Delete from jobs WHERE id = ? ");
			statement.setInt(1, idDelete);
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	public int updateJob(Job job) {
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(""
					+ "update jobs set name = ?,start_date = ? ,end_date= ? WHERE id = ? ");
			statement.setString(1, job.getName());
			statement.setDate(2, job.getStart_date());
			statement.setDate(3, job.getEnd_date());
			statement.setInt(4, job.getId());
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	public List<JobDto> findTaskByJobNotDone(String job_id) { // các task của dự án chưa thực hiện  theo job_id 
		List<JobDto> Jobs = new ArrayList<JobDto>();
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(
					  "select t.name ,u.fullname as NguoiLam, t.start_date , t.end_date "
					+ "from jobs j join tasks t join users u  on j.id = t.job_id and u.id = t.user_id "
					+ "where t.status_id = 1 and t.job_id = ? ; ");
			statement.setInt(1, Integer.parseInt(job_id));
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				JobDto job = new JobDto(resultSet.getString("name"), resultSet.getString("NguoiLam"),resultSet.getDate("start_date"),resultSet.getDate("end_date"));
				Jobs.add(job);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Jobs;
	}
	
	public List<JobDto> findTaskByJobDoing(String job_id) { // các task của dự án đang thực hiện  theo job_id 
		List<JobDto> Jobs = new ArrayList<JobDto>();
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(
					  "select t.name ,u.fullname as NguoiLam, t.start_date , t.end_date "
					+ "from jobs j join tasks t join users u  on j.id = t.job_id and u.id = t.user_id "
					+ "where t.status_id = 2 and t.job_id = ? ; ");
			statement.setInt(1, Integer.parseInt(job_id));
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				JobDto job = new JobDto(resultSet.getString("name"), resultSet.getString("NguoiLam"),resultSet.getDate("start_date"),resultSet.getDate("end_date"));
				Jobs.add(job);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Jobs;
	}
	
	public List<JobDto> findTaskByJobDone(String job_id) { // các task của dự án đã thực hiện  theo job_id 
		List<JobDto> Jobs = new ArrayList<JobDto>();
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(
					 "select t.name ,u.fullname as NguoiLam, t.start_date , t.end_date "
					+ "from jobs j join tasks t join users u  on j.id = t.job_id and u.id = t.user_id "
					+ "where t.status_id = 3 and t.job_id = ? ;");
			statement.setInt(1, Integer.parseInt(job_id));
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				JobDto job = new JobDto(resultSet.getString("name"), resultSet.getString("NguoiLam"),resultSet.getDate("start_date"),resultSet.getDate("end_date"));
				Jobs.add(job);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Jobs;
	}

}
