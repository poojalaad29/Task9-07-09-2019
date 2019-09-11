package com.wp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SubjectPageServlet")
public class SubjectPageServlet extends HttpServlet {
	String visit;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie ck[] = request.getCookies();
		
		PrintWriter out = response.getWriter();
		for (int i = 0; i < ck.length; i++) {
			if (ck[i].getName().equals("visit"))
		  visit = ck[i].getValue();
		
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");
			String sql = "SELECT distinct Subject from books";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			out.println("<html>");
			out.println("<html><body>");
			if(visit==null) {
			out.println("<marquee>Last time you visited all books</marquee>");
			}
			else {
				out.println("<marquee>Last time you visited "+visit+"</marquee>");
				}
			out.println("<h3>Select The Desired Subject</h3>");
			out.println("<hr>");
			while (rs.next()) {
				String sub = rs.getString(1);
				out.println("<a href=BookListServlet?subject=" + sub + ">");
				out.println(sub);
				out.println("</a><br>");
			}
			out.println("<hr>");
			out.println("<a href=BuyerPage.jsp>Buyer-Page</a>");
			out.println("</body></html>");
			
		} catch (Exception e) {
			out.println(e);
		}
	}

}
