package com.myclass.service;
import java.util.List;

import com.myclass.dto.TaskDto;
import com.myclass.repository.TaskRepository;


public class TaskService {
	private TaskRepository taskRepository = null;
	
	public TaskService() {
		taskRepository = new TaskRepository();
	}
	
	public List<TaskDto> getAllTask(){
		return taskRepository.findAllTaskName();
	}
}
