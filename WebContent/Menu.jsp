<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@page import="ca.myseneca.model.Employee"%>
<%@page errorPage="/errorPage.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Menu</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="styles/main.css" type="text/css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>

<body>
	<%
		Employee emp = (Employee) request.getAttribute("employee");
	%>

	<nav class="navbar navbar-default navbar-expand-sm">
		<div class="container-fluid">
		    <form action="Menu" method="get">
			    <ul class="nav navbar-nav">
				  <li class="active"><a title="Employee List" href="Menu" >Employee List</a></li>
			      <li><a title="Add New Employee" href="addEmployee">Add New Employee</a></li>
			      <li><a title="Search Employee" href="searchEmployee">Search Employee</a></li>
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
			<h1>Employee List Page</h1>
			<p>Display employees in a department by providing department ID.
				It also allows users to list all employees in all departments.</p>
			<label class="pad_top">Department ID:</label> 
			<input type="number" name="deptId" pattern="[0-1][0-9]0" min="10" max="110"  class="form-control" required> <br>
			
			<input type="submit" name="action" value="Find Employees By Department ID" class="btn btn-primary" >
		</form>
		<br>
		<form action="EmployeeList" method="post">
			<input type="submit" name="action" value="Find All Employees" class="btn btn-primary" >
		</form>
	</div>	
	<br><br>
	<jsp:include page="/footer.html" />
</body>
</html>