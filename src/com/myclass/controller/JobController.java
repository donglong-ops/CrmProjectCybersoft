package com.myclass.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myclass.entity.Job;

import com.myclass.repository.JobRepository;
import com.myclass.service.JobService;

@WebServlet(name = "jobServlet", urlPatterns = { "/job", "/job/add", "/job/edit", "/job/delete" })
public class JobController extends HttpServlet {

	private JobRepository jobRepository = null;
	private JobService jobService = null;

	public JobController() {
		jobRepository = new JobRepository();

		jobService = new JobService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = req.getServletPath();

		switch (action) {
		case "/job":
			req.setAttribute("jobs", jobService.getAll());
			req.getRequestDispatcher("/WEB-INF/views/job/job.jsp").forward(req, resp);
			break;
		case "/job/add":
			req.getRequestDispatcher("/WEB-INF/views/job/job_add.jsp").forward(req, resp);
			break;
		case "/job/edit":
			int id = Integer.valueOf(req.getParameter("id"));
			Job job = jobRepository.findById(id);
			req.setAttribute("jobDto", job);
			req.getRequestDispatcher("/WEB-INF/views/job/job_edit.jsp").forward(req, resp);
			break;
		case "/job/delete":
			int idD = Integer.valueOf(req.getParameter("id"));
			try {
				jobRepository.deleteJob(idD);
			} catch (Exception e) {
				System.out.println("Can't Delete Job: " + e.getMessage());
			}

			resp.sendRedirect(req.getContextPath() + "/job");
			break;
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String action = req.getServletPath();

		switch (action) {
		case "/job/add":
			String jobname = req.getParameter("jobName");
			Date start_date = null;
			Date end_date = null;
			if (req.getParameter("startDate").length() > 0 && req.getParameter("endDate").length() > 0 && jobname.length() > 0) {
					try {
						long time_start = ((java.util.Date) new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("startDate"))).getTime();
						start_date = new Date(time_start);

						long time_end = ((java.util.Date) new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("endDate"))).getTime();
						end_date = new Date(time_end);
					} catch (ParseException e) {
						System.out.println("Error Date: " + e.getMessage());
					}
				
					int result = jobRepository.save(new Job(jobname, start_date, end_date));
					if (result != -1) {
						resp.sendRedirect(req.getContextPath() + "/job");
					} else {
						req.setAttribute("message", "Add Failed.Format Date yyyy-MM-dd !!");
						req.getRequestDispatcher("/WEB-INF/views/job/job_add.jsp").forward(req, resp);
					}
				}else {
					req.setAttribute("message", "Add Failed.Format Date yyyy-MM-dd !!");
					req.getRequestDispatcher("/WEB-INF/views/job/job_add.jsp").forward(req, resp);
			}
			break;
		case "/job/edit":
			String job_name = req.getParameter("jobName");
			int job_Id = Integer.valueOf(req.getParameter("job_id"));
			Date startdate = null;
			Date enddate = null;
			if (req.getParameter("startDate").length() > 0 && req.getParameter("endDate").length() > 0 && job_name.length() > 0) {
				try {
					long time_start = ((java.util.Date) new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("startDate"))).getTime();
					startdate = new Date(time_start);

					long time_end = ((java.util.Date) new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("endDate"))).getTime();
					enddate = new Date(time_end);
				} catch (ParseException e) {
					System.out.println("Error Date: " + e.getMessage());
				}
				
				int rs = jobRepository.updateJob(new Job(job_Id, job_name, startdate, enddate));
				if (rs != -1) {
					resp.sendRedirect(req.getContextPath() + "/job");
				} else {
					Job job = jobRepository.findById(job_Id);
					req.setAttribute("jobDto", job);
					req.setAttribute("message", "Update Failed.Format Date yyyy-MM-dd !!");
					req.getRequestDispatcher("/WEB-INF/views/job/job_add.jsp").forward(req, resp);
				}
			}else {
				Job job = jobRepository.findById(job_Id);
				req.setAttribute("jobDto", job);
				req.setAttribute("message", "Update Failed.Format Date yyyy-MM-dd !!");
				req.getRequestDispatcher("/WEB-INF/views/job/job_add.jsp").forward(req, resp);
			}
			break;
		default:
			break;
		}
	}
}
