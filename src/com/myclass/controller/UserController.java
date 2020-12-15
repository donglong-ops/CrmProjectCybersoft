package com.myclass.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.constants.SessionConstants;
import com.myclass.dto.TaskDto;
import com.myclass.dto.UserDto;
import com.myclass.entity.User;
import com.myclass.repository.JobRepository;
import com.myclass.repository.RoleRepository;
import com.myclass.repository.StatusRepository;
import com.myclass.repository.TaskRepository;
import com.myclass.repository.UserRepository;
import com.myclass.service.UserService;

@WebServlet(name = "userServlet", urlPatterns = { "/user", "/user/add", "/user/edit", "/user/delete", "/user/info",
		"/user/detail", "/user/profile_edit", "/user/view"})
public class UserController extends HttpServlet {

	private RoleRepository roleRepository = null;
	private UserRepository userRepository = null;
	private TaskRepository taskRepository = null;
	private StatusRepository statusRepository = null;
	private JobRepository jobRepository  = null;

	private UserService userService = null;

	public UserController() {
		roleRepository = new RoleRepository();
		userRepository = new UserRepository();
		taskRepository = new TaskRepository();
		statusRepository = new StatusRepository();
		jobRepository = new JobRepository();

		userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = req.getServletPath();
		HttpSession session = req.getSession();

		switch (action) {
		case "/user":
			req.setAttribute("users", userService.getAllUserRole());
			req.getRequestDispatcher("/WEB-INF/views/user/user_table.jsp").forward(req, resp);
			break;
		case "/user/add":
			req.setAttribute("roles", roleRepository.findAll());
			req.getRequestDispatcher("/WEB-INF/views/user/user_add.jsp").forward(req, resp);
			break;
		case "/user/edit":
			int id = Integer.parseInt(req.getParameter("id"));
			UserDto user = userRepository.findUserById(id);
			req.setAttribute("user", user);
			req.setAttribute("roles", roleRepository.findAll());
			req.getRequestDispatcher("/WEB-INF/views/user/user_edit.jsp").forward(req, resp);
			break;
		case "/user/view":
			UserDto Dto = (UserDto) session.getAttribute("USER_LOGIN");
			User userView = userRepository.findById(Dto.getId());
			req.setAttribute("user", userView);
			req.setAttribute("roles", roleRepository.findAll());
			req.getRequestDispatcher("/WEB-INF/views/user/user_profile_edit.jsp").forward(req, resp);
			break;	
		case "/user/delete":
			int idDelete = Integer.parseInt(req.getParameter("id"));
			userRepository.deleteUser(idDelete);
			resp.sendRedirect(req.getContextPath() + "/user");
			break;
		case "/user/info":
			UserDto userDto = (UserDto) session.getAttribute(SessionConstants.USER_LOGIN);
			if (userDto == null) {
				resp.sendRedirect(req.getContextPath() + "/login");
				break;
			} else {
				int allNumber = userRepository.countTaskUser(userDto.getId());
				int done = userRepository.countTaskUserByStatusDone(userDto.getId());
				int doing = userRepository.countTaskUserByStatusDoing(userDto.getId());

				req.setAttribute("done", done);
				req.setAttribute("doing", doing);
				req.setAttribute("waiting", (allNumber - (done + doing)));
				req.setAttribute("taskofUser", taskRepository.findAllTaskByUserID(userDto.getId()));
				req.getRequestDispatcher("/WEB-INF/views/user/profile.jsp").forward(req, resp);
				break;
			}
		case "/user/detail":
			String job_id = req.getParameter("job_id");
				req.setAttribute("TaskNotDone", jobRepository.findTaskByJobNotDone(job_id));
				req.setAttribute("TaskDoing", jobRepository.findTaskByJobDoing(job_id));
				req.setAttribute("TaskDone", jobRepository.findTaskByJobDone(job_id));
			
			req.getRequestDispatcher("/WEB-INF/views/user/user_detail.jsp").forward(req, resp);
			break;
		case "/user/profile_edit":
			int idEdit = Integer.parseInt(req.getParameter("id"));
			TaskDto task = taskRepository.findById(idEdit);
			req.setAttribute("taskEdit", task);
			req.setAttribute("listStatus", statusRepository.findAll());
			req.getRequestDispatcher("/WEB-INF/views/user/profile_edit.jsp").forward(req, resp);
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
			String fullname = req.getParameter("fullname");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String avatar = req.getParameter("avatar");
			if (fullname.length() > 0 && email.length() > 0 && password.length() > 0) {
				int roleId = Integer.valueOf(req.getParameter("roleId")); // tự điền k cần
				String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
				User user = new User(email, hashed, fullname, avatar, roleId);
				userRepository.save(user);
				resp.sendRedirect(req.getContextPath() + "/user");
			} else {
				req.setAttribute("roles", roleRepository.findAll());
				req.setAttribute("errorAccount", "Can't Create Account!! . Check Again !! ");
				req.getRequestDispatcher("/WEB-INF/views/user/user_add.jsp").forward(req, resp);
			}
			break;
		case "/user/edit":
			String fullName = req.getParameter("fullname");
			String Email = req.getParameter("email");
			String Avatar = req.getParameter("avatar");
			if (fullName.length() > 0 && Email.length() > 0) {
				//int RoleId = Integer.valueOf(req.getParameter("roleId"));
				int id = Integer.valueOf(req.getParameter("id"));
				User userEdit = userRepository.findById(id);
				userEdit.setEmail(Email);
				userEdit.setFullname(fullName);
				userEdit.setAvatar(Avatar);
				int role_id = Integer.valueOf(req.getParameter("role_Id"));
				if(role_id != 0) {
					userEdit.setRoleId(role_id);
				}
				userRepository.update(userEdit);
				resp.sendRedirect(req.getContextPath() + "/user");
			} else {
				req.setAttribute("roles", roleRepository.findAll());
				req.setAttribute("errorAccount", "Can't Update Account!! . Check Again !! ");
				req.getRequestDispatcher("/WEB-INF/views/user/user_add.jsp").forward(req, resp);
			}
			break;
		case "/user/profile_edit":
			int id_task = Integer.valueOf(req.getParameter("task_id"));
			int status_id = Integer.valueOf(req.getParameter("status_id"));
			taskRepository.updateTaskById(id_task, status_id);
			resp.sendRedirect(req.getContextPath() + "/user/info");
			break;
		default:
			break;
		}
	}
}
