package com.myclass.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.entity.Role;
import com.myclass.repository.RoleRepository;

@WebServlet(name = "roleServlet", urlPatterns = { "/role", "/role/add", "/role/edit", "/role/delete" })
public class RoleController extends HttpServlet {

	private RoleRepository roleRepository = null;

	public RoleController() {
		roleRepository = new RoleRepository();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = req.getServletPath();

		switch (action) {
		case "/role":
			List<Role> roles = roleRepository.findAll();
			// B5: Chuyển List<Role> qua cho index.jsp để tạo trang Html
			// => Thêm List<Role> vào Request
			req.setAttribute("roles", roles);
			req.getRequestDispatcher("/WEB-INF/views/role/index.jsp").forward(req, resp);
			break;
		case "/role/add":
			req.getRequestDispatcher("/WEB-INF/views/role/add.jsp").forward(req, resp);
			break;
		case "/role/edit":
			req.getRequestDispatcher("/WEB-INF/views/role/edit.jsp").forward(req, resp);
			break;
		case "/role/delete":

			break;
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// B1: Lấy thông tin từ form
		String name = req.getParameter("name");
		String desc = req.getParameter("desc");
		// B2: G�?i hàm save để thực thi câu lệnh thêm mới
		Role role = new Role();
		role.setName(name);
		role.setDescription(desc);

		int result = roleRepository.save(role);

		// TH1: THêm mơí thành công
		// => Chuyển hướng v�? trang danh sách
		if (result != -1) {
			resp.sendRedirect(req.getContextPath() + "/role");
		} else {
			// TH2: Thêm mới thất bại
			// => CHuyển tiếp v�? trang add.jsp => Thông báo cho ngư�?i dùng
			req.setAttribute("message", "Thêm mới thất bại!");
			req.getRequestDispatcher("/WEB-INF/views/role/add.jsp").forward(req, resp);
		}
	}
}
