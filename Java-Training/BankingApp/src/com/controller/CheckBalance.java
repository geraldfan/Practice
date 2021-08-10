package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Model;

/**
 * Servlet implementation class CheckBalance
 */
public class CheckBalance extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		Model m = new Model();
		m.setPassword(password);
		m.setEmail(email);
		
		boolean hasBalance = m.checkBalance();
		
		if (hasBalance) {
			int balance = m.getBalance();
			HttpSession session = req.getSession();
			session.setAttribute("balance", balance);
			session.setAttribute("email", email);
			resp.sendRedirect("/BankingApp/viewBalance.jsp");
		} else {
			resp.sendRedirect("/BankingApp/invalidCredentials.html");
		}
	}

}
