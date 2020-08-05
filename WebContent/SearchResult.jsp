<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="ca.myseneca.model.Employee"%>
<%@page import="ca.myseneca.model.Department"%>
<%@ page errorPage="/errorPage.jsp"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Result</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="styles/main.css" type="text/css" />
</head>
<body>
<div class="container" style="margin-top:50px">
		<a title="Return" href="searchEmployee" class="return btn btn-primary">Return</a>
		<%
			@SuppressWarnings("unchecked")
			List<Object[]> resultList = (List<Object[]>) request.getAttribute("resultList");
			String search = (String) request.getAttribute("search");	
		%>
		<h1>Search for Employee</h1>
		<p> Search for Employee by typing: " <%= search %> ", it show in any part of name, email address, phone number or department name.</p>
		<form action="EmployeeList" method="post">
			<table class="table table-striped">
				<tr>
					<th>Name</th>
					<th>Department</th>
					<th>Job ID</th>
					<th>Salary</th>
					<th>Email</th>
					<th>Phone</th>			
				</tr>
			<%		
				for (Object[] emp : resultList) {
			%>
				
				<tr>
					<td><%=emp[0]%> <%=emp[1]%></td>
					<td><%=emp[6]%></td>
					<td><%=emp[4]%></td>
					<td><%=emp[3]%></td>
					<td><a href="mailto:<%=emp[2]%>"><%=emp[2]%></a></td>
					<td><%=emp[5]%></td>
				</tr>
			<%
				}
			%>
			</table>
		</form>
	</div>
	<br><br>
	<jsp:include page="/footer.html" />
</body>
</html>