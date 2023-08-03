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
import javax.servlet.http.HttpSession;

@WebServlet("/checkBalance")

public class checkBalance extends HttpServlet {
	private Connection con;
	private ResultSet rs;
	private PreparedStatement pstmt;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		int acc_no = (int)session.getAttribute("acc_no");
		
		//Database connection
		
		String url = "jdbc:mysql://localhost:3306/BankingApplication";
		String user = "root";
		String pass = "Logesh88823";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pass);
			pstmt = con.prepareStatement("Select balance from register where acc_no = ?");
			pstmt.setInt(1, acc_no);
			
			rs = pstmt.executeQuery();
			if(rs.next()== true) {
				session.setAttribute("balance", rs.getInt("Balance"));
				resp.sendRedirect("/BankingApplication-Servlet/Balance.jsp");
				
			}
			else {
				resp.sendRedirect("/BankingApplication-Servlet/Failed.html");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
