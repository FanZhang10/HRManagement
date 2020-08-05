<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@page import="ca.myseneca.model.Employee"%>
<%@page errorPage="/errorPage.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Search Employee</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="styles/main.css" type="text/css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>

<body>
	<nav class="navbar navbar-default navbar-expand-sm">
		<div class="container-fluid">
		    <form action="Menu" method="get">
			    <ul class="nav navbar-nav">
				  <li><a title="Employee List" href="Menu" >Employee List</a></li>
			      <li><a title="Add New Employee" href="addEmployee">Add New Employee</a></li>
			      <li class="active"><a title="Search Employee" href="searchEmployee">Search Employee</a></li>
			    </ul>
			    <ul class="nav navbar-nav navbar-right">
						<li><span>Hi <%=session.getAttribute("name")%></span></li>	
						<li><input type="submit" name="action" value="Logout" class=" logout btn btn-primary" ></li>	
				</ul>
			</form>	
		</div>
	</nav>
	<div class="container" style="margin-top:50px">
		<form action="EmployeeList" method="post">		
			<h1>Search Employee Page</h1>
			<p> Search for Employee by typing in any part of a name, email address, phone number or department.</p>
	
			<input type="text" name="search" class="form-control" required> <br>
			
			<input type="submit" name="action" value="Go" class="btn btn-primary" >
		</form>
	</div>
	<br><br>
	<jsp:include page="/footer.html" />
</body>
</html>