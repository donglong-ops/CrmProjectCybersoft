package com.myclass.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.entity.User;

@WebServlet(name = "homeServlet", urlPatterns = {"/home"})
public class HomeController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/home/homePage.jsp").forward(req, resp);
	}
}
