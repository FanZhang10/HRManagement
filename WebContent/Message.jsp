<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@page import="ca.myseneca.model.Employee"%>
<%@page errorPage="/errorPage.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Message</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link rel="stylesheet" href="styles/main.css" type="text/css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>

<body>

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
		<h1>Message Page</h1>
			<%
				String message = (String) request.getAttribute("message");
			%>
			<div class="alert alert-success">
				<strong>Success!</strong> <%=message%>.
			</div>
	
			<a title="Employee List" href="Menu" class="btn btn-primary">Employee List</a>
	</div>
	<br>
	<br>
	<jsp:include page="/footer.html" />
</body>
</html>