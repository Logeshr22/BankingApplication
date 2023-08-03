package com.digit.BankingServices;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Transfer")
public class Transfer extends HttpServlet {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet result_set;
	private ResultSet result_set2;
	private ResultSet result_set3;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int customer_id = Integer.parseInt(req.getParameter("customer_id"));
		String bank_name = req.getParameter("bank_name");
		String ifsc = req.getParameter("ifsc");
		String receiver_ifsc = req.getParameter("receiver_ifsc");
		int acc_no = Integer.parseInt(req.getParameter("acc_no"));
		int receiver_accno = Integer.parseInt(req.getParameter("receiver_accno"));
		int amount = Integer.parseInt(req.getParameter("amount"));
		int pin = Integer.parseInt(req.getParameter("pin"));

		// Database connection

		String url = "jdbc:mysql://localhost:3306/BankingApplication";
		String user = "root";
		String pass = "Logesh88823";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
			pstmt = con.prepareStatement(
					"select * from register where customer_id = ? and ifsc = ? and acc_no = ? and pin = ?");
			pstmt.setInt(1, customer_id);
			pstmt.setString(2, ifsc);
			pstmt.setInt(3, acc_no);
			pstmt.setInt(4, pin);
			result_set = pstmt.executeQuery();
			if (result_set.next() == true) {
				pstmt = con.prepareStatement("select * from register where ifsc = ? and acc_no = ?");
				pstmt.setString(1, ifsc);
				pstmt.setInt(2, acc_no);
				result_set2 = pstmt.executeQuery();
				if (result_set2.next() == true) {
					pstmt = con.prepareStatement("select balance from register where acc_no = ?");
					pstmt.setInt(1, acc_no);
					result_set3 = pstmt.executeQuery();
					if (result_set3.next() == true) {
						int balance = result_set3.getInt(1);
						if (balance > amount) {
							pstmt = con.prepareStatement("update register set balance = balance - ? where acc_no = ?");
							pstmt.setInt(1, amount);
							pstmt.setInt(2, acc_no);
							int x = pstmt.executeUpdate();
							if (x > 0) {
								pstmt = con.prepareStatement("update register set balance = balance + ? where acc_no = ?");
								pstmt.setInt(1, amount);
								pstmt.setInt(2, receiver_accno);
								int x2 = pstmt.executeUpdate();
								if (x2 > 0) {
									pstmt = con.prepareStatement("insert into transfer_status values (?,?,?,?,?,?,?)");
									pstmt.setInt(1, customer_id);
									pstmt.setString(2, bank_name);
									pstmt.setString(3, ifsc);
									pstmt.setInt(4, acc_no);
									pstmt.setString(5, receiver_ifsc);
									pstmt.setInt(6, receiver_accno);
									pstmt.setInt(7, amount);
									int x3 = pstmt.executeUpdate();
									if (x3 > 0) {
										resp.sendRedirect("/BankingApplication/Success.html");
									}
								} else {
									resp.sendRedirect("/BankingApplication/Failed.html");
								}
							} else {
								resp.sendRedirect("/BankingApplication/Failed.html");
							}
						} else {
							resp.sendRedirect("/BankingApplication/Failed.html");
						}
					} else {
						resp.sendRedirect("/BankingApplication/Failed.html");
					}
				}
			} else {
				resp.sendRedirect("/BankingApplication/Failed.html");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
