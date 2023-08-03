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
import javax.servlet.http.HttpSession;


@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
    private Connection con;
    private PreparedStatement pstmt;

 

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "jdbc:mysql://localhost:3306/BankingApplication";
        String user = "root";
        String pwd = "Logesh88823";
        HttpSession session = req.getSession(true);
        int acc_no = (int)session.getAttribute("acc_no");
        String old_pass = req.getParameter("old_pass");
        String new_pass = req.getParameter("new_pass");
        String confirmNew_pass = req.getParameter("confirmNew_pass");
        int new_password = Integer.parseInt(new_pass);

        //Database connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pwd);

            if(new_pass.equals(confirmNew_pass)) {
                pstmt = con.prepareStatement("update register set pin = ? where acc_no = ?");
                pstmt.setInt(1, new_password);
                pstmt.setInt(2, acc_no);          

                int x = pstmt.executeUpdate();
                if(x>0) {
                    resp.sendRedirect("/BankingApplication-Servlet/Success.html");
                }
                else {
                    resp.sendRedirect("/BankingApplication-Servlet/Failed.html");
                }
            }
            else {
            	
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

 