package com.myclass.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.constants.SessionConstants;
import com.myclass.dto.UserDto;


@WebFilter(urlPatterns = {"/*"})
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String action = req.getServletPath();
		if(action.equals("/login")) {
			chain.doFilter(request, response);
			return;
		}
		HttpSession session = req.getSession();
		
		if (session.getAttribute(SessionConstants.USER_LOGIN) == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		
//		// -----======== PHÂN QUYỀN =======------
		UserDto user = (UserDto)session.getAttribute(SessionConstants.USER_LOGIN);
	
//		// Dùng roleName để phân quyền
		String roleName = user.getRoleName();
		System.out.println("role hiện tại là : " + roleName);
		if(action.startsWith("/role") && !roleName.equals("ROLE_Manager")) { //leader và member không truy cập role dc
			resp.sendRedirect(req.getContextPath() + "/404");
			return;
		}
		
		if(action.startsWith("/task") && !roleName.equals("ROLE_Leader")) { 
			resp.sendRedirect(req.getContextPath() + "/404");
			return;
		}
		if(action.equals("/user/view") && roleName.equals("ROLE_USER") || action.equals("/user/view") && roleName.equals("ROLE_Leader")  || 
		   action.equals("/user/info") && roleName.equals("ROLE_USER") || action.equals("/user/info") && roleName.equals("ROLE_Leader")  ||
		   action.equals("/user/edit") && roleName.equals("ROLE_USER") || action.equals("/user/edit") && roleName.equals("ROLE_Leader")  ||
		   action.equals("/user/view") && roleName.equals("ROLE_Manager") || action.equals("/user/edit") && roleName.equals("ROLE_Manager")||
		   action.equals("/user/info") && roleName.equals("ROLE_Manager")) {
			chain.doFilter(request, response);
			return;
		}
		if(action.startsWith("/user") && !roleName.equals("ROLE_Manager") && !roleName.equals("ROLE_Leader")) {
			resp.sendRedirect(req.getContextPath() + "/404");
			return;
		}

		if(action.startsWith("/job") && !roleName.equals("ROLE_Manager") && !roleName.equals("ROLE_Leader")) {
			resp.sendRedirect(req.getContextPath() + "/404");
			return;
		}
		if(action.equals("/job/edit") && !roleName.equals("ROLE_Leader") || action.equals("/job/delete")  && !roleName.equals("ROLE_Leader")) { 
			resp.sendRedirect(req.getContextPath() + "/404");
			return;
		}
		if(action.equals("/user/edit") && !roleName.equals("ROLE_Manager") || action.equals("/user/delete")  && !roleName.equals("ROLE_Manager")) {
			resp.sendRedirect(req.getContextPath() + "/404");
			return;
		}
	
	chain.doFilter(request, response);

	}

}
