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
import com.myclass.dto.UserDto;
import com.myclass.entity.Role;
import com.myclass.entity.User;
import com.myclass.repository.RoleRepository;
import com.myclass.repository.UserRepository;
import com.myclass.service.UserService;

@WebServlet(urlPatterns = { "/login", "/logout" })
public class AuthController extends HttpServlet {
	private UserService userService = null;
	public AuthController() {
		userService = new UserService();
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

		UserDto userDto = userService.checkLogin(email, pass);
		if (userDto == null) {
			req.setAttribute("message", "Email hoặc mật khẩu không đúng!");
			req.getRequestDispatcher("/WEB-INF/views/auth/loginPage.jsp").forward(req, resp);
			return;
		}
		HttpSession session = req.getSession();
		session.setAttribute(SessionConstants.USER_LOGIN, userDto);
		resp.sendRedirect(req.getContextPath() + "/home");
	}
}
