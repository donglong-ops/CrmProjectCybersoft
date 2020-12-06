package com.myclass.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.entity.User;
import com.myclass.repository.TaskRepository;
import com.myclass.service.TaskService;

@WebServlet(name = "taskServlet", urlPatterns = { "/task", "/task/add", "/task/edit", "/task/delete", "/blank", "/404" })
public class TaskController extends HttpServlet {

	private TaskRepository taskRepository = null;
	private TaskService taskService = null;

	public TaskController() {
			taskRepository = new TaskRepository();		
			taskService = new TaskService();
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
			req.getRequestDispatcher("/WEB-INF/views/task/task_add.jsp").forward(req, resp);
			break;
		case "/task/edit":
			req.getRequestDispatcher("/WEB-INF/views/task/task_edit.jsp").forward(req, resp);
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
			//viết code
			
			resp.sendRedirect(req.getContextPath() + "/task");
			break;
		case "/task/edit":
			// viết code
			
			resp.sendRedirect(req.getContextPath() + "/task");
			break;
		case "/task/delete":
			// viet code
			
			resp.sendRedirect(req.getContextPath() + "/task");
			break;
		default:
			break;
		}
	}

}
