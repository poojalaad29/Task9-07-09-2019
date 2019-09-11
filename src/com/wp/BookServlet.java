package com.wp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	ArrayList<String> s=new ArrayList<String>();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
		String subject[]=request.getParameterValues("Subject");
		System.out.println(subject.length);
		for(int i=0; i<subject.length; i++){
			System.out.println(subject[i].toString());
	   }

		for(int i=0;i<subject.length;i++)
            {
			s.add(i, subject[i].split("/"));
			}
		
		PrintWriter out=response.getWriter();
			
		Cookie c3 = new Cookie("visit", subject[1]);
		response.addCookie(c3);
		
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","root");
		
		String sql="SELECT id,Title from books";
		PreparedStatement ps=con.prepareStatement(sql);
		//ps.setString(1, subject);
		ResultSet rs=ps.executeQuery();
		
		out.println("<html>");
		out.println("<html><body>");
		out.println("<h3>Select The Desired Title</h3>");
		out.println("<hr>");
		
		while(rs.next()){
			String code=rs.getString(1);
			String title=rs.getString(2);
			
			out.println("<a href=BookDetailServlet?code="+code+"&title="+title+">");
			out.println(title);
			out.println("</a><br>");
		}
		out.println("<hr>");
		out.println("<a href=SubjectPageServlet>Subject-Page</a>");
		out.println("<a href=BuyerPage.jsp>Buyer-Page</a>");
		out.println("</body></html>");
	
		}catch(Exception e){
			out.println(e);
		}

	
	}
}
