package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class Model {
	private String name;
	private String email;
	private String username;
	private String password;
	private String newPassword;
	private int accno;
	private int balance;
	private int amount;
	private int transferAccNo;

	Connection conn;
	PreparedStatement pstmt;
	ResultSet res;

	public Model() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_app", "root", "UTU-r*Tk&p4#2JC");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String transferMoney() {
		try {
			String sql = "SELECT BALANCE FROM ACCOUNTS WHERE ACCNO=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, accno);
			res = pstmt.executeQuery();
			if (res.next() && hasExistingAccount(transferAccNo).next()) {
				balance = res.getInt("balance");
				if (balance >= amount) {
					String sql1 = "UPDATE ACCOUNTS SET BALANCE=(BALANCE - ?) WHERE ACCNO=?";
					pstmt = conn.prepareStatement(sql1);

					pstmt.setInt(1, amount);
					pstmt.setInt(2, accno);

					int x = pstmt.executeUpdate();

					String sql2 = "UPDATE ACCOUNTS SET BALANCE=(BALANCE + ?) WHERE ACCNO=?";
					pstmt = conn.prepareStatement(sql2);
					pstmt.setInt(1, amount);
					pstmt.setInt(2, transferAccNo);

					int y = pstmt.executeUpdate();
					if (x != 0 && y != 0) {
						String sql3 = "SELECT * FROM ACCOUNTS WHERE ACCNO=?";
						pstmt = conn.prepareStatement(sql3);

						pstmt.setInt(1, accno);
						res = pstmt.executeQuery();

						if (res.next()) {
							balance = res.getInt("balance");
							email = res.getString("email");
						} 
						return "success";
					}
				} else {
					return "insufficient";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}

	public boolean addMoney() {
		try {
			String sql = "UPDATE ACCOUNTS SET BALANCE=(BALANCE + ?) WHERE ACCNO=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, amount);
			pstmt.setInt(2, accno);

			int x = pstmt.executeUpdate();
			if (x != 0) {
				String sql1 = "SELECT * FROM ACCOUNTS WHERE ACCNO=?";
				pstmt = conn.prepareStatement(sql1);

				pstmt.setInt(1, accno);
				res = pstmt.executeQuery();

				if (res.next()) {
					balance = res.getInt("balance");
					email = res.getString("email");
				} 
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public boolean checkBalance() {
		try {
			String sql = "SELECT BALANCE FROM ACCOUNTS WHERE EMAIL=? AND PASSWORD=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			res = pstmt.executeQuery();
			if (res.next()) {
				balance = res.getInt("balance");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updatePassword() {
		try {
			String sql = "SELECT * FROM ACCOUNTS WHERE EMAIL=? AND PASSWORD=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, email);
			pstmt.setString(2, password);

			res = pstmt.executeQuery();
			if (res.next()) {
				String sql1 = "UPDATE ACCOUNTS SET PASSWORD=? WHERE EMAIL=?";
				pstmt = conn.prepareStatement(sql1);

				pstmt.setString(1, newPassword);
				pstmt.setString(2, email);
				;

				int x = pstmt.executeUpdate();

				if (x != 0) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean login() {
		try {
			String sql = "SELECT * FROM ACCOUNTS WHERE USERNAME=? AND PASSWORD=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, username);
			pstmt.setString(2, password);

			res = pstmt.executeQuery();

			if (res.next()) {
				name = res.getString("name");
				accno = res.getInt("accno");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public int register() {
		try {
			int newAccNo = generateAccNo();
			res = hasExistingAccount(newAccNo);
			while (res.next()) {
				newAccNo = generateAccNo();
				res = hasExistingAccount(newAccNo);
			}

			if (!res.next()) {
				String sql = "INSERT INTO ACCOUNTS VALUES(?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);

				setAccno(newAccNo);
				setBalance(0);
				pstmt.setString(1, name);
				pstmt.setString(2, email);
				pstmt.setString(3, username);
				pstmt.setString(4, password);
				pstmt.setInt(5, accno);
				pstmt.setInt(6, balance);

				int x = pstmt.executeUpdate();
				return x;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	private int generateAccNo() {
		return 10000 + new Random().nextInt(90000);
	}

	private ResultSet hasExistingAccount(int accNo) throws SQLException {
		String sql = "SELECT * FROM ACCOUNTS WHERE ACCNO = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, accNo);
		return pstmt.executeQuery();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public int getAccno() {
		return accno;
	}

	public void setAccno(int accno) {
		this.accno = accno;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getTransferAccNo() {
		return transferAccNo;
	}

	public void setTransferAccNo(int transferAccNo) {
		this.transferAccNo = transferAccNo;
	}

}
