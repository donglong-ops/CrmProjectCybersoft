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

import com.myclass.constants.UrlConstant;
import com.myclass.entity.Role;
import com.myclass.entity.User;
import com.myclass.repository.RoleRepository;
import com.myclass.repository.UserRepository;
import com.myclass.service.RoleService;
import com.myclass.service.UserService;

@WebServlet(name = "userServlet", urlPatterns = { "/user", "/user/add", UrlConstant.URL_ROLE_EDIT, "/user/delete" })
public class UserController extends HttpServlet {

	private RoleRepository roleRepository = null;
	private UserRepository userRepository = null;
	
	private UserService userService = null;
	private RoleService roleService = null;

	public UserController() {
		roleRepository = new RoleRepository();
		userRepository = new UserRepository();
		
		userService = new UserService();
		roleService = new RoleService();
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
			req.setAttribute("roles", roleService.getAll());
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

		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String fullname = req.getParameter("fullname");
		String avatar = req.getParameter("avatar");
		int roleId = Integer.valueOf(req.getParameter("roleId"));

		switch (action) {
		case "/user/add":
			String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
			User user = new User(email, hashed, fullname, avatar, roleId);
			userRepository.save(user);
			resp.sendRedirect(req.getContextPath() + "/user");
			break;
		case UrlConstant.URL_ROLE_EDIT:
			int id = Integer.valueOf(req.getParameter("id"));
			User userEdit = userRepository.findById(id);
			userEdit.setEmail(email);
			userEdit.setFullname(fullname);
			userEdit.setAvatar(avatar);
			userEdit.setRoleId(roleId);

			// KIỂM TRA XEM NGƯỜI DÙNG NHẬP MẬT KHẨU MỚI KHÔNG
			if (password != null && !password.isEmpty()) {
				// MÃ HÓA MẬT KHẨU
				String hashed2 = BCrypt.hashpw(password, BCrypt.gensalt(12));
				// CẬP NHẬT THÔNG TIN USER (BAO GỒM CẢ KHẨU)
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
