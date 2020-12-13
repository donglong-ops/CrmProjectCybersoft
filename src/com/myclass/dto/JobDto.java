package com.myclass.dto;

import java.sql.Date;

public class JobDto {
	private String name;
	private String userDo;
	private Date start_date;
	private Date end_date;
	
	public JobDto() {
		// TODO Auto-generated constructor stub
	}

	public JobDto(String name, String userDo, Date start_date, Date end_date) {
		super();
		this.name = name;
		this.userDo = userDo;
		this.start_date = start_date;
		this.end_date = end_date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserDo() {
		return userDo;
	}

	public void setUserDo(String userDo) {
		this.userDo = userDo;
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
	

}
