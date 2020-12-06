package com.myclass.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.constants.SessionConstants;
import com.myclass.constants.UrlConstant;
import com.myclass.entity.Role;
import com.myclass.entity.User;
import com.myclass.repository.RoleRepository;
import com.myclass.repository.UserRepository;
import com.myclass.service.RoleService;
import com.myclass.service.UserService;

@WebServlet(name = "userServlet", urlPatterns = { "/user", "/user/add", "/user/edit", "/user/delete","/user/info", "/user/detail", "/user/profile_edit"})
public class UserController extends HttpServlet {

	private RoleRepository roleRepository = null;
	private UserRepository userRepository = null;
	
	private UserService userService = null;

	public UserController() {
		roleRepository = new RoleRepository();
		userRepository = new UserRepository();
		
		userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getServletPath();

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
			int id = Integer.valueOf(req.getParameter("id"));
			User user = userRepository.findById(id);
			req.setAttribute("user", user);
			req.setAttribute("roles", roleRepository.findAll());
			req.getRequestDispatcher("/WEB-INF/views/user/user_edit.jsp").forward(req, resp);
			break;
		case "/user/delete":
			int idDelete = Integer.valueOf(req.getParameter("id"));
			userRepository.deleteUser(idDelete);
			resp.sendRedirect(req.getContextPath() + "/user");
			break;
		case "/user/info":
			
			req.getRequestDispatcher("/WEB-INF/views/user/profile.jsp").forward(req, resp);
			break;
			
		case "/user/detail":
			
			req.getRequestDispatcher("/WEB-INF/views/user/user_detail.jsp").forward(req, resp);
			break;
			
		case "/user/profile_edit":
			
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
		
		String fullname = req.getParameter("fullname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String avatar = req.getParameter("avatar");
		int roleId = Integer.valueOf(req.getParameter("roleId"));


		switch (action) {
		case "/user/add":
			String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
			User user = new User(email, hashed, fullname, avatar, roleId);
			userRepository.save(user);
			resp.sendRedirect(req.getContextPath() + "/user");
			break;
		case "/user/edit":
			int id = Integer.valueOf(req.getParameter("id"));
			User userEdit = userRepository.findById(id);
			userEdit.setEmail(email);
			userEdit.setFullname(fullname);
			userEdit.setAvatar(avatar);
			userEdit.setRoleId(roleId);

			if (password != null && !password.isEmpty()) {
				String hashed2 = BCrypt.hashpw(password, BCrypt.gensalt(12));
				userEdit.setPassword(hashed2);
			}
			userRepository.update(userEdit);
			resp.sendRedirect(req.getContextPath() + "/user");
			break;
		default:
			break;
		}
	}
}
