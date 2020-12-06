package com.myclass.service;

import java.util.List;

import com.myclass.entity.Job;
import com.myclass.repository.JobRepository;


public class JobService {

	private JobRepository jobRepository = null;

	public JobService() {
		jobRepository = new JobRepository();
	}

	public List<Job> getAll() {
		List<Job> list = jobRepository.findAll();
		return list;
	}

}
