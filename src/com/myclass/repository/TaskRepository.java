package com.myclass.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myclass.db.JDBCConnection;
import com.myclass.dto.TaskDto;

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
						rs.getTimestamp("start_date"), rs.getTimestamp("end_date"), rs.getString("statusName"));
				tasks.add(taskDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tasks;
	}
}
