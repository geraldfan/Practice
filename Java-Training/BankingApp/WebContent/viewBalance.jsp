<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css" type="text/css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<title>Home</title>
</head>

<!--Home page after successful login-->

<body>
	<!--Displays a welcome message on successful login-->
	<h1 class="intro">
		<%
			out.println("Your Balance is:  $" + session.getAttribute("balance"));
		%>
	</h1>
	<!--Checkbox button group for the menu-->
	<div class="center">
		<a href="/BankingApp/addMoney.html" class="btn btn-primary">Add Money</a> 
		<a href="/BankingApp/transferMoney.html" class="btn btn-primary">Transfer Money</a> 
		<a href="/BankingApp/checkBalance.html" class="btn btn-primary">Check Balance</a> 
		<a href="/BankingApp/changePassword.html" class="btn btn-warning">Change
			Password</a> 
			<a href="Logout" class="btn btn-danger">Logout</a>


	</div>
	<br>

	<script src="https://unpkg.com/@popperjs/core@2/dist/umd/popper.js"></script>
	<script src="js/bootstrap.js"></script>
</body>

</html>