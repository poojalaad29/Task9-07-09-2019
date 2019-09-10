package com.wp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SearchBookServlet")
public class SearchBookServlet extends HttpServlet {
	private Connection con;
	private PreparedStatement ps;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
         String Subject=request.getParameter("name");
        System.out.println(Subject);
       try {
    	   
		 Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","root");
		String sql="Select * from books where Subject like ?";
		ps=con.prepareStatement(sql);
		ps.setString(1,'%'+Subject+'%');
	    ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String id=rs.getString(1);
				String title=rs.getString(2);
				String author=rs.getString(3);
				String subject=rs.getString(4);
				String price=rs.getString(5);
				out.println("<html><body>");
				out.println("<h3>Book-Details</h3>");
				out.println("<hr>");
				out.println("<table>");
				out.println("<tr>");
				out.println("<td>Title</td>");
				out.println("<td>"+title+"</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>Author</td>");
				out.println("<td>"+author+"</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>Subject</td>");
				out.println("<td>"+subject+"</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>Price</td>");
				out.println("<td>"+price+"</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<hr></hr>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<hr>");
				out.println("<a href=CartManager?code="+id+">Add-To-Cart</a><br>");
				out.println("<a href=SubjectPageServlet>Subject-Page</a><br>");
				out.println("<a href=BuyerPage.jsp>Buyer-Page</a><br>");
				out.println("</body></html>");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
		
