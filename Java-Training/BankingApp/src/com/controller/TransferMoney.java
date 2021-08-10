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
 * Servlet implementation class TransferMoney
 */
public class TransferMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String transferAcc = req.getParameter("transferAcc");
		String amount = req.getParameter("amount");

		int transferAccInt = Integer.parseInt(transferAcc);
		int amountInt = Integer.parseInt(amount);

		HttpSession sess = req.getSession();
		Object accno = sess.getAttribute("accno");
		int accnoInt = (int) accno;

		Model m = new Model();
		m.setTransferAccNo(transferAccInt);
		m.setAmount(amountInt);
		m.setAccno(accnoInt);

		String transferStatus = m.transferMoney();
		if (transferStatus.equals("success")) {
			String fromEmail = Credentials.email; // sender's mail id.
			String pwd = Credentials.password; // sender's mail pwd.
			String toEmail = (String) sess.getAttribute("email"); // reciever's mail id.

			String subject = "DO NOT REPLY: Transaction Confirmation Email"; // mail subject line
			String msg = "Hi " + sess.getAttribute("name") + "\n Your transaction has been completed successfully.\n $"
					+ amount + " has been transferred to account no: " + transferAcc + "\n Your current balance is: $" + m.getBalance(); // mail
			// body

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
			resp.sendRedirect("/BankingApp/successfulTransfer.html");
		} else if (transferStatus.equals("insufficient")) {
			resp.sendRedirect("/BankingApp/insufficientFunds.html");
		} else if (transferStatus.equals("error")) {
			resp.sendRedirect("/BankingApp/errorTransaction.html");
		} else {
			resp.sendRedirect("/BankingApp/asdsd.html");
		}
	}
}
