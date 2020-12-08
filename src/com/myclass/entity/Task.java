package com.myclass.entity;

import java.sql.Date;


public class Task {
	private int id;
	private String name;
	private Date start_date;
	private Date end_date;
	private int user_id;
	private int job_id;
	private int status_id;
	
	public Task() {}
	

	public Task(int id, String name, Date start_date, Date end_date, int user_id, int job_id, int status_id) {
		super();
		this.id = id;
		this.name = name;
		this.start_date = start_date;
		this.end_date = end_date;
		this.user_id = user_id;
		this.job_id = job_id;
		this.status_id = status_id;
	}

	public Task(String name, Date start_date, Date end_date, int user_id, int job_id, int status_id) {
		super();
		this.name = name;
		this.start_date = start_date;
		this.end_date = end_date;
		this.user_id = user_id;
		this.job_id = job_id;
		this.status_id = status_id;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getJob_id() {
		return job_id;
	}

	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	
	
}
