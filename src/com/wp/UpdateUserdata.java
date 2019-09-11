package com.wp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.PreparedStatement;


@WebServlet("/UpdateUserdata")
public class UpdateUserdata extends HttpServlet {
	HttpSession session;
	PreparedStatement ps;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		session=request.getSession();
		String user=(String) session.getAttribute("user");
		
		try {
			 Class.forName("com.mysql.jdbc.Driver");
		     Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","root");
	        
		     String username=request.getParameter("username");
				String password=request.getParameter("password");
				String address=request.getParameter("address");
				String email=request.getParameter("email");
				String mobile=request.getParameter("mobile");

			String sql="Update user set Username='"+username+"',Password='"+password+"',Address='"+address+"',Email='"+email+"',Mobile='"+mobile+"' where Email='"+user+"'";
	        ps=con.prepareStatement(sql);
	        
	        PrintWriter out=response.getWriter();
	        out.println(sql);
				int n=ps.executeUpdate();
				
				out.println("Updated Completed");
				
	    } catch (Exception e) {
			e.printStackTrace();
		 }
	}
}

