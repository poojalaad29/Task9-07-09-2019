<%@ page import="java.sql.PreparedStatement"%> 
<%@ page import="java.sql.ResultSet" %> 
<%@ page import="java.sql.Connection" %>  
<%@ page import="java.sql.DriverManager" %>

<h3>Select The Desired Subject</h3>
<%

  PreparedStatement ps;
   String user=(String)session.getAttribute("user"); 
   Class.forName("com.mysql.jdbc.Driver");
  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","root");
   String sql="Select distinct Subject from books";
    ps = con.prepareStatement(sql);
	ResultSet rs = ps.executeQuery();
	while (rs.next()) {
   %>
   
<html>
<body>
<form action="BookServlet">
<%=rs.getString(1)%><input type="checkbox" name="Subject" value=<%=rs.getString(1)%>/><br>
 
<%} %>
<input type="Submit" value="Search"/>

<%
con.close();
%>
</form>
</body>
</html>