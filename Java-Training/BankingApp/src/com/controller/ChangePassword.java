package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Model;

/**
 * Servlet implementation class ChangePassword
 */
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String newPassword = req.getParameter("newPassword");
		String confirmPassword = req.getParameter("confirmPassword");

		if (newPassword.equals(confirmPassword)) {
			Model m = new Model();

			m.setEmail(email);
			m.setPassword(password);
			m.setNewPassword(newPassword);

			boolean hasChanged = m.updatePassword();

			if (hasChanged) {
				resp.sendRedirect("/BankingApp/successPasswordChange.html");
			} else {
				resp.sendRedirect("/BankingApp/unsuccessfulPasswordChange.html");
			}
		} else {
			resp.sendRedirect("/BankingApp/unsuccessfulPasswordChange.html");
		}
	}

}
