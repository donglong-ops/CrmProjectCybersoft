package com.myclass.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.constants.SessionConstants;
import com.myclass.dto.UserDto;
import com.myclass.entity.Role;
import com.myclass.entity.User;
import com.myclass.repository.RoleRepository;
import com.myclass.repository.UserRepository;
import com.myclass.service.UserService;

@WebServlet(name = "authServlet", urlPatterns = { "/login", "/logout" })
public class AuthController extends HttpServlet {
	private UserService userService = null;
	private UserRepository userRepository = null;
	private RoleRepository roleRepository = null;

	public AuthController() {
		userService = new UserService();
		userRepository = new UserRepository();
		roleRepository = new RoleRepository();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = req.getServletPath();
		switch (action) {
		case "/login":
			req.getRequestDispatcher("/WEB-INF/views/auth/loginPage.jsp").forward(req, resp);
			break;
		case "/logout":
			HttpSession session = req.getSession();
			if (session.getAttribute(SessionConstants.USER_LOGIN) != null) {
				session.removeAttribute(SessionConstants.USER_LOGIN);
			}
			resp.sendRedirect(req.getContextPath() + "/login");
			break;
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String pass = req.getParameter("password");
		System.out.println(email  + pass);
		if (email.length() > 0 && pass.length() > 2) {
			User dto = userRepository.checkLogin(email, pass);	
			
			if (dto != null) {
				Role role = roleRepository.findById(dto.getRoleId());
				UserDto test = new UserDto();
				test.setId(dto.getId());
				test.setEmail(dto.getEmail());
				test.setAvatar(dto.getAvatar());
				test.setFullname(dto.getFullname());
				test.setRoleName(role.getName());
				HttpSession session = req.getSession();
				session.setAttribute(SessionConstants.USER_LOGIN, dto);
				resp.sendRedirect(req.getContextPath() + "/home");
			} else {
				req.setAttribute("message", "Invalid Email or Password!");
				req.getRequestDispatcher("/WEB-INF/views/auth/loginPage.jsp").forward(req, resp);
			}
		} else {
			req.setAttribute("message", "Invalid Email or Password!");
			req.getRequestDispatcher("/WEB-INF/views/auth/loginPage.jsp").forward(req, resp);
		}
	}
}
