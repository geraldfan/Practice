package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Model;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			Model m = new Model();
			
			m.setUsername(username);
			m.setPassword(password);
			
			boolean hasLogin = m.login();
			if (hasLogin) {
				String name = m.getName();
				int accno = m.getAccno();
				HttpSession session = req.getSession(true);
				session.setAttribute("name", name);
				session.setAttribute("accno", accno);
				resp.sendRedirect("/BankingApp/home.jsp");
			} else {
				resp.sendRedirect("/BankingApp/notRegistered.html");
			}
		}
}
