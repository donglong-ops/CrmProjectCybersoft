package com.myclass.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.constants.UrlConstant;
import com.myclass.entity.Job;
import com.myclass.entity.User;
import com.myclass.repository.JobRepository;
import com.myclass.service.JobService;

@WebServlet(name = "jobServlet", urlPatterns = { "/job", "/job/add","/job/edit", "/job/delete" })
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
			req.setAttribute("job", job);
			req.getRequestDispatcher("/WEB-INF/views/user/user_edit.jsp").forward(req, resp);
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
		case "/user/add":
			// viet code
			
			resp.sendRedirect(req.getContextPath() + "/job");
			break;
		case "/job/edit":
			//viet code
			
			resp.sendRedirect(req.getContextPath() + "/job");
			break;
		case "/job/delete":
			int idD = Integer.valueOf(req.getParameter("id"));
			//viết code delete
			
			resp.sendRedirect(req.getContextPath() + "/job");
			break;
		default:
			break;
		}
	}
}
