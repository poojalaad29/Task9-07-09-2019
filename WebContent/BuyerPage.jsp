<%
	String user=(String)session.getAttribute("user");
	if(user==null){
		response.sendRedirect("index.jsp");
	}
%>
<html>
<body>
 	<h3>DashBoard-For-<%=user %></h3>
	<hr>
	<pre>
		<a href="SubjectPageServlet">Explore-Store</a>
		<a href="ExploreWithChkBox.jsp">Explore-Store multiple subject</a>
		<a href="SearchBook.jsp">Search-Book</a>
		<a href="">View-Cart</a>
		<a href="">Trace-Order</a>
		<a href="UpdateProfile.jsp">Update profile</a>
		<a href="">Logout</a>
	</pre>		
	<hr>
</body>
</html>