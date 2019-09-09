package com.wp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
	private Connection con;
	private PreparedStatement ps;
	
	public void init(){
		try{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","root");
		String sql="insert into books values(?,?,?,?,?)";
		ps=con.prepareStatement(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void destroy(){
		try{
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("inside get");
		
		PrintWriter out=response.getWriter();

		String title=request.getParameter("title");
		String price=request.getParameter("price");
		String author=request.getParameter("author");
		String subject=request.getParameter("subject");
		try{
			ps.setInt(1, 8);
			ps.setString(2, title);
			ps.setString(3, price);
			ps.setString(4, author);
			ps.setString(5, subject);
			int n=ps.executeUpdate();
			out.println("Book inserted Completed");
			
			response.sendRedirect("AdminPage.jsp");
		}catch(Exception e){
			out.println(e);
	
		}}
	}


