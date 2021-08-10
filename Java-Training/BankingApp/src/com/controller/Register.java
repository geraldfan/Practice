package com.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Model;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirmPassword");

		if (password.equals(confirmPassword)) {
			Model m = new Model();
			m.setName(name);
			m.setEmail(email);
			m.setUsername(username);
			m.setPassword(password);

			int x = m.register();
			if (x == 0) {
				resp.sendRedirect("/BankingApp/didnotreg.html");
			} else {
				String fromEmail = Credentials.email; // sender's mail id.
				String pwd = Credentials.password; // sender's mail pwd.
				String toEmail = email; // reciever's mail id.

				String subject = "DO NOT REPLY: Welcome Email"; // mail subject line
				String msg = "Hi " + name + "\n Welcome to CitBank.\n Your account number is: " + m.getAccno()
						+ "\n Your current balance is: $" + m.getBalance(); // mail
				// body

				HttpSession sess = req.getSession(true);
				// Creating Session Object
				Properties prop = new Properties();
				prop.put("mail.smtp.host", "smtp.gmail.com");
				prop.put("mail.smtp.port", 587);
				prop.put("mail.smtp.auth", "true");
				prop.put("mail.smtp.starttls.enable", "true");

				Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						// sender's mail id and pwd is encapsulated inside "SendersCredentials.java"
						return new PasswordAuthentication(fromEmail, pwd);
					}
				});

				try {
					// Composing the Mail
					MimeMessage mesg = new MimeMessage(session);
					mesg.setFrom(new InternetAddress(fromEmail));
					mesg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
					mesg.setSubject(subject);
					mesg.setText(msg);

					// Sending the Mail
					Transport.send(mesg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				resp.sendRedirect("/BankingApp/successReg.html");
			}
		} else {
			resp.sendRedirect("/BankingApp/wrongReg.html");
		}
	}

}
