package com.myclass.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			req.setAttribute("roles", roles);
			req.getRequestDispatcher("/WEB-INF/views/role/role_table.jsp").forward(req, resp);
			break;
		case "/role/add":
			req.getRequestDispatcher("/WEB-INF/views/role/role_add.jsp").forward(req, resp);
			break;
		case "/role/edit":
			int id = Integer.valueOf(req.getParameter("id"));
			Role role = roleRepository.findById(id);
			req.setAttribute("roleDto", role);
			req.getRequestDispatcher("/WEB-INF/views/role/role_edit.jsp").forward(req, resp);
			break;
		case "/role/delete":
			int idDelete = Integer.valueOf(req.getParameter("id"));
			try {
				roleRepository.deleteRole(idDelete);
			} catch (Exception e) {
				System.out.println("Can't Delete This Role");
			}			
			resp.sendRedirect(req.getContextPath() + "/role");
			break;
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = req.getServletPath();

		switch (action) {
		case "/role/add":
			String name = req.getParameter("name");
			String desc = req.getParameter("desc");
			if(name.length() > 0 && desc.length() > 0) {
					int result = roleRepository.save(new Role( name, desc));
					if (result != -1) {
						resp.sendRedirect(req.getContextPath() + "/role");
					} else {
						req.setAttribute("message", "Can't Create Role!");
						req.getRequestDispatcher("/WEB-INF/views/role/role_add.jsp").forward(req, resp);
					}
			}else {
				req.setAttribute("message", "Can't Create Role!");
				req.getRequestDispatcher("/WEB-INF/views/role/role_add.jsp").forward(req, resp);
			}
			break;
		case "/role/edit":
			int id = Integer.valueOf(req.getParameter("id"));
			String nameRole = req.getParameter("name");
			String des = req.getParameter("desc");
			Role dto = new Role(id, nameRole, des);
			if(des.length() > 0 && nameRole.length() > 0) {
				roleRepository.updateRole(dto);
				resp.sendRedirect(req.getContextPath() + "/role");
			}else {
				Role role = roleRepository.findById(id);
				req.setAttribute("roleDto", role);
				req.setAttribute("errorUpdate", "Can't Update");
				req.getRequestDispatcher("/WEB-INF/views/role/role_edit.jsp").forward(req, resp);
			}
			break;
		default:
			break;
		}
	}
}
