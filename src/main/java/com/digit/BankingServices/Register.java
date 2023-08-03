package com.digit.BankingServices;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class Register extends HttpServlet{
	private Connection con;
	private PreparedStatement pstmt;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int bank_id = Integer.parseInt(req.getParameter("bank_id"));
		String bank_name = req.getParameter("bank_name");
		String ifsc = req.getParameter("ifsc");
		int acc_no = Integer.parseInt(req.getParameter("acc_no"));
		int pin = Integer.parseInt(req.getParameter("pin"));
		int customer_id = Integer.parseInt(req.getParameter("customer_id"));
		String customer_name = req.getParameter("customer_name");
		int balance = Integer.parseInt(req.getParameter("balance"));
		String email = req.getParameter("email");
		long phone = Long.parseLong(req.getParameter("phone"));
		
		String url = "jdbc:mysql://localhost:3306/BankingApplication";
		String user = "root";
		String pass = "Logesh88823";
		
		//Database connection
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pass);
			pstmt = con.prepareStatement("insert into Register values (?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, bank_id);
			pstmt.setString(2, bank_name);
			pstmt.setString(3, ifsc);
			pstmt.setInt(4, acc_no);
			pstmt.setInt(5, pin);
			pstmt.setInt(6, customer_id);
			pstmt.setString(7, customer_name);
			pstmt.setInt(8, balance);
			pstmt.setString(9, email);
			pstmt.setLong(10, phone);
			int x = pstmt.executeUpdate();
			
			if(x>0) {
				resp.sendRedirect("/BankingApplication-Servlet/Success.html");
			}
		}
		catch(Exception e) {
			resp.sendRedirect("/BankingApplication-Servlet/Failed.html");
			e.printStackTrace();
		}
	}
}
