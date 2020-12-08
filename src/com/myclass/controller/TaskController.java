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

import com.myclass.dto.TaskDto;
import com.myclass.entity.Job;
import com.myclass.entity.Task;
import com.myclass.repository.TaskRepository;
import com.myclass.service.JobService;
import com.myclass.service.TaskService;
import com.myclass.service.UserService;

@WebServlet(name = "taskServlet", urlPatterns = { "/task", "/task/add", "/task/edit", "/task/delete", "/blank",
		"/404" })
public class TaskController extends HttpServlet {

	private TaskRepository taskRepository = null;
	private TaskService taskService = null;
	private UserService userService = null;
	private JobService jobService = null;

	public TaskController() {
		taskRepository = new TaskRepository();
		taskService = new TaskService();
		userService = new UserService();
		jobService = new JobService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = req.getServletPath();

		switch (action) {
		case "/task":
			req.setAttribute("tasks", taskService.getAllTask());
			req.getRequestDispatcher("/WEB-INF/views/task/task.jsp").forward(req, resp);
			break;
		case "/task/add":
			req.setAttribute("users", userService.getAllUserRole());
			req.setAttribute("jobs", jobService.getAll());
			req.getRequestDispatcher("/WEB-INF/views/task/task_add.jsp").forward(req, resp);
			break;
		case "/task/edit":
			int id = Integer.valueOf(req.getParameter("id"));
			TaskDto task = taskRepository.findTaskById(id);
			req.setAttribute("taskDto", task);
			req.setAttribute("users", userService.getAllUserRole());
			req.setAttribute("jobs", jobService.getAll());
			req.getRequestDispatcher("/WEB-INF/views/task/task_edit.jsp").forward(req, resp);
			break;
		case "/task/delete":
			int idD = Integer.valueOf(req.getParameter("id"));
			try {
				taskRepository.deleteTask(idD);
			} catch (Exception e) {
				System.out.println("Can't Delete Task: " + e.getMessage());
			}
			resp.sendRedirect(req.getContextPath() + "/task");
			break;
		case "/blank":
			req.getRequestDispatcher("/WEB-INF/views/error/blank.jsp").forward(req, resp);
			break;
		case "/404":
			req.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(req, resp);
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
		case "/task/add":
			String taskName = req.getParameter("nameTask");
			int user_id = Integer.parseInt(req.getParameter("userId"));
			int job_id = Integer.parseInt(req.getParameter("job_id"));
			Date start_date = null;
			Date end_date = null;
			if (taskName.length() > 0 && req.getParameter("start_date").length() > 0&& req.getParameter("end_date").length() > 0) {
				try {
					long time_start = ((java.util.Date) new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("start_date"))).getTime();
					start_date = new Date(time_start);

					long time_end = ((java.util.Date) new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("end_date"))).getTime();
					end_date = new Date(time_end);
				} catch (ParseException e) {
					System.out.println("Error Date: " + e.getMessage());
				}
				int result = taskRepository.save(new Task(taskName, start_date, end_date, user_id, job_id, 1));
				if (result != -1) {
					resp.sendRedirect(req.getContextPath() + "/task");
				} else {
					req.setAttribute("message", "Add Failed.Format Date yyyy-MM-dd !!");
					req.getRequestDispatcher("/WEB-INF/views/job/tas_add.jsp").forward(req, resp);
				}
			} else {
				req.setAttribute("message", "Add Failed.Format Date yyyy-MM-dd !!");
				req.getRequestDispatcher("/WEB-INF/views/job/tas_add.jsp").forward(req, resp);
			}
			break;
		case "/task/edit":
//			String taskName = req.getParameter("nameTask");
//			int user_id = Integer.parseInt(req.getParameter("userId"));
//			int job_id = Integer.parseInt(req.getParameter("job_id"));
//			Date start_date = null;
//			Date end_date = null;
//			if (taskName.length() > 0 && req.getParameter("start_date").length() > 0&& req.getParameter("end_date").length() > 0) {
//				try {
//					long time_start = ((java.util.Date) new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("start_date"))).getTime();
//					start_date = new Date(time_start);
//
//					long time_end = ((java.util.Date) new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("end_date"))).getTime();
//					end_date = new Date(time_end);
//				} catch (ParseException e) {
//					System.out.println("Error Date: " + e.getMessage());
//				}
//				int result = taskRepository.save(new Task(taskName, start_date, end_date, user_id, job_id, 1));
//				if (result != -1) {
//					resp.sendRedirect(req.getContextPath() + "/task");
//				} else {
//					req.setAttribute("message", "Add Failed.Format Date yyyy-MM-dd !!");
//					req.getRequestDispatcher("/WEB-INF/views/job/tas_add.jsp").forward(req, resp);
//				}
//			} else {
//				req.setAttribute("message", "Add Failed.Format Date yyyy-MM-dd !!");
//				req.getRequestDispatcher("/WEB-INF/views/job/tas_add.jsp").forward(req, resp);
//			}
			break;
		default:
			break;
		}
	}

}
