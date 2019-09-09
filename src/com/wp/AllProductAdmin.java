package com.wp;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AllProductAdmin")
public class AllProductAdmin extends HttpServlet {
	
	private Connection con;
	private PreparedStatement ps;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String code=request.getParameter("code");
		PrintWriter out=response.getWriter();
	try{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","root");
		String sql="Select * from books";
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		out.println("<html><body>");
		out.println("<h3>Already Books</h3>");
		out.println("<hr>");
		while(rs.next()){
			String id=rs.getString(1);
			String title=rs.getString(2);
			String author=rs.getString(3);
			String subject=rs.getString(4);
			String price=rs.getString(5);
			out.println("<table>");
			out.println("<tr>");
			out.println("<td>BCode</td>");
			out.println("<td>"+id+"</td>");
			out.println("<td><a href=UpdateBook?ch=Update&id="+id+">Update</td>");
			out.println("<td><a href=UpdateBook?ch=Delete&id="+id+">Delete</td>");
			out.println("</tr>");
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
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	
    }}
