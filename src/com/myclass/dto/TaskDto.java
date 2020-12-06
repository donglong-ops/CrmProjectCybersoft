package com.myclass.dto;

import java.sql.Timestamp;

public class TaskDto {
	private int id;
	private String nameTask;
	private String nameJob;
	private String nameUser;
	private Timestamp start_date;
	private Timestamp end_date;
	private String statusName;
	
	public TaskDto() {
		
	}
	
	public TaskDto(int id, String nameTask, String nameJob, String nameUser, Timestamp start_date, Timestamp end_date,String statusName) {
		super();
		this.id = id;
		this.nameTask = nameTask;
		this.nameJob = nameJob;
		this.nameUser = nameUser;
		this.start_date = start_date;
		this.end_date = end_date;
		this.statusName = statusName;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameTask() {
		return nameTask;
	}
	public void setNameTask(String nameTask) {
		this.nameTask = nameTask;
	}
	public String getNameJob() {
		return nameJob;
	}
	public void setNameJob(String nameJob) {
		this.nameJob = nameJob;
	}
	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	public Timestamp getStart_date() {
		return start_date;
	}
	public void setStart_date(Timestamp start_date) {
		this.start_date = start_date;
	}
	public Timestamp getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Timestamp end_date) {
		this.end_date = end_date;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	
	

}
