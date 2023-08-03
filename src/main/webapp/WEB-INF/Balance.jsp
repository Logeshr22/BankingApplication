<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="app.css">
<meta charset="ISO-8859-1">
<title>Balance</title>
</head>
<body>
	<div class="formContainer">
		<div class="list">
			<h3 class="header">
				<%
				session = request.getSession();
				String name = (String)session.getAttribute("customer_name");
				int s1 = (int) session.getAttribute("balance");
				out.println("Hi "+name+",");
				out.println("Your Balance is : " + s1);
				%>
			</h3>
			<br>
			<div class="LinkButton">
				<a href="Home.jsp">Click to redirect</a>
			</div>

		</div>
	</div>



</body>
</html>