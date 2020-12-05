package com.myclass.entity;

import java.sql.Timestamp;

public class Job {
	private int id;
	private String name;
	private Timestamp start_date;
	
	public Job() {}

	public Job(int id, String name, Timestamp start_date) {
		super();
		this.id = id;
		this.name = name;
		this.start_date = start_date;
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

	public Timestamp getStart_date() {
		return start_date;
	}

	public void setStart_date(Timestamp start_date) {
		this.start_date = start_date;
	}
	
	

}
