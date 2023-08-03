<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="app.css">
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	<div class="formContainer">
	<div class="list">
	<h1 class="header" >Welcome to the Bank</h1>
	<%
	session = request.getSession();
	String s1 = (String)session.getAttribute("customer_name");
	%>
	<h2 class="header">
	<%= s1%>  , welcome to your account. Please select an operation to perform
	</h2>
	
	<table>
	<tr>
	</tr>
	<tr>
	
	</table>
	<a class="LinkButton" href="checkBalance">Check Balance</a>
	<a class="LinkButton" href="ChangePassword.html">Change Password</a>
	<a class="LinkButton" href="ApplyLoan.jsp">Apply Loan</a>
	<a class="LinkButton" href="Transfer.html">Transfer Amount</a>
	
	</div>
	</div>
</body>
</html>