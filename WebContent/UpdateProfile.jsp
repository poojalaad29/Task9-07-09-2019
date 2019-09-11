
<%@ page import="java.sql.Connection" %>  
<%@ page import="java.sql.DriverManager" %>  
<%@ page import="java.sql.PreparedStatement"%> 
<%@ page import="java.sql.ResultSet;" %> 

<%
  String id="",name="",password="",email="",address="",mobile="";
   PreparedStatement ps;
   String user=(String)session.getAttribute("user"); 
   Class.forName("com.mysql.jdbc.Driver");
  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","root");
   String sql="Select * from user where Email=?";
   ps=con.prepareStatement(sql);
   ps.setString(1,user);
   ResultSet rs=ps.executeQuery();
   while(rs.next()){
		id=rs.getString(1);
	    name=rs.getString(2);
	   password=rs.getString(3);
	   address=rs.getString(4);
	   email=rs.getString(5);
	   mobile=rs.getString(6);	   
   }
  %> 
<html>
<body>
<form action="UpdateUserdata">
	<pre>
	<h2>Your Details as per database</h2>
		Username	<input type="text" name="username" value="<%=name%>"/>
		Password	<input type="text" name="password" value="<%=password%>"/>
		Address		<input type="text" name="address" value="<%=address%>"/>
		Email-Id	<input type="text" name="email" value="<%=email%>"/>
		Mobile		<input type="text" name="mobile" value="<%=mobile%>"/>
				<input type="submit" value="Update"/>
				
				
</pre>
</form>
  </body>
</html>