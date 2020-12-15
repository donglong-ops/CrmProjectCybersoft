package com.myclass.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myclass.db.JDBCConnection;
import com.myclass.dto.TaskDto;
import com.myclass.entity.Task;
import com.myclass.entity.User;

public class TaskRepository {

	public List<TaskDto> findAllTaskName() {
		List<TaskDto> tasks = new ArrayList<TaskDto>();
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(
					"select  t.id, t.name, j.name as nameJob, u.fullname,  t.start_date, t.end_date , s.name as statusName\r\n"
					+ "from tasks t join users u  join jobs j join status s\r\n"
					+ "on t.job_id = j.id and  t.status_id = s.id and t.user_id = u.id");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				TaskDto taskDto = new TaskDto(rs.getInt("id"),rs.getString("name"), rs.getString("nameJob"),rs.getString("fullname"),
						rs.getDate("start_date"), rs.getDate("end_date"), rs.getString("statusName"));
				tasks.add(taskDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tasks;
	}
	public int save(Task task) {
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(
					"INSERT INTO tasks(name, start_date, end_date, user_id, job_id, status_id) VALUES(?,?,?,?,?,?)");
			statement.setString(1, task.getName());
			statement.setDate(2, task.getStart_date());
			statement.setDate(3, task.getEnd_date());
			statement.setInt(4, task.getUser_id());
			statement.setInt(5, task.getJob_id());
			statement.setInt(6, task.getStatus_id());

			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	public int deleteTask(int idDelete) {
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement("Delete from tasks WHERE id = ? ");
			statement.setInt(1, idDelete);
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public List<TaskDto> findAllTaskByUserID(int user_id) {
		List<TaskDto> tasks = new ArrayList<TaskDto>();
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(
					"select  t.id, t.name, j.name as nameJob,  t.start_date, t.end_date , s.name as statusName\r\n"
					+ "from tasks t join users u  join jobs j join status s\r\n"
					+ "on t.job_id = j.id and  t.status_id = s.id and t.user_id = u.id\r\n"
					+ "where t.user_id = ? ");
			statement.setInt(1, user_id);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				TaskDto taskDto = new TaskDto(rs.getInt("id"),rs.getString("name"), rs.getString("nameJob"),
						rs.getDate("start_date"), rs.getDate("end_date"), rs.getString("statusName"));
				tasks.add(taskDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tasks;
	}
	public TaskDto findById(int id) {
		TaskDto task = new TaskDto();
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(
					"select  t.id, t.name, j.name as nameJob,  t.start_date, t.end_date , s.name as statusName "
					+ "	from tasks t join users u  join jobs j join status s "
					+ "	on t.job_id = j.id and  t.status_id = s.id and t.user_id = u.id "
					+ "	where t.id = ? ");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				task = new TaskDto(rs.getInt("id"),rs.getString("name"), rs.getString("nameJob"),
						rs.getDate("start_date"), rs.getDate("end_date"), rs.getString("statusName"));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return task;
	}
	public void updateTaskById(int id_task, int status_id) {
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement("update tasks set status_id = ? where id = ? ");
			statement.setInt(1, status_id);
			statement.setInt(2, id_task);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public TaskDto findTaskById(int id) {
		TaskDto task = new TaskDto();
		try {
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(
					"select  t.id, t.name, j.name as nameJob, u.fullname,  t.start_date, t.end_date , s.name as statusName "
					+ "	from tasks t join users u  join jobs j join status s "
					+ "	on t.job_id = j.id and  t.status_id = s.id and t.user_id = u.id "
					+ "	where t.id = ? ");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				task = new TaskDto(rs.getInt("id"),rs.getString("name"), rs.getString("nameJob"),rs.getString("fullname"),
						rs.getDate("start_date"), rs.getDate("end_date"), rs.getString("statusName"));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return task;
	}
	public int updateTask(Task task) {
		int count = 4;
		try {
			Connection conn = JDBCConnection.getConnection();
			String query = "Update tasks set name = ? , start_date = ? , end_date = ? ";
			
			if(task.getJob_id() != 0) {
				query = query + " ,job_id = ? "; 
			}	
			if(task.getUser_id() != 0) {
				query = query + " ,user_id = ?  ";
			}
			query = query + " where id = ? ";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, task.getName());
			statement.setDate(2, task.getStart_date());
			statement.setDate(3, task.getEnd_date());
			
			if(task.getJob_id() != 0) {
				statement.setInt(count, task.getJob_id());
				count++;
			}	
			if(task.getUser_id() != 0) {
				statement.setInt(count, task.getUser_id());
				count++;
			}
			statement.setInt(count, task.getId());
			
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
}
