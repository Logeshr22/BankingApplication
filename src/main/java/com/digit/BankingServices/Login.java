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

@WebServlet("/Login")
public class Login extends HttpServlet{
	private PreparedStatement pstmt;
	private Connection con;
	ResultSet rs ;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int customer_id = Integer.parseInt(req.getParameter("customer_id"));
		int pin = Integer.parseInt(req.getParameter("pin"));
		
		//Database connection
		String url = "jdbc:mysql://localhost:3306/BankingApplication";
		String user = "root";
		String pass = "Logesh88823";
		HttpSession session = req.getSession(true);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pass);
			pstmt = con.prepareStatement("select * from register where customer_id = ? and pin = ?");
			pstmt.setInt(1, customer_id);
			pstmt.setInt(2, pin);
			
			rs = pstmt.executeQuery();
			if(rs.next() == true) {
				session.setAttribute("acc_no", rs.getInt("acc_no"));
				session.setAttribute("customer_name", rs.getString("customer_name"));
				resp.sendRedirect("/BankingApplication/Home.jsp");
			}
			else {
				resp.sendRedirect("/BankingApplication/Failed.html");
			}
		}
		catch(Exception e) {
			resp.sendRedirect("/BankingApplication/Failed.html");
		}
	}
}
