<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="ca.myseneca.model.Employee"%>
<%@ page errorPage="/errorPage.jsp"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee List</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
	<div class="container" style="margin-top:50px">
		<a title="Return" href="Menu" class="return btn btn-primary">Return</a>
	
		<h1>Employee List</h1>
		
		<form action="EmployeeList" method="post">
			<table class="table table-striped">
				<tr>
					<th>Employee ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Phone</th>
					<th>Hare Date</th>
					<th>Job ID</th>
					<th>Salary</th>
					<th>Commission PCT</th>
					<th>Manager ID</th>
					<th>Department ID</th>
				</tr>
				<%
					@SuppressWarnings("unchecked")
				List<Employee> emplist = (List<Employee>) request.getAttribute("emplist");
			
				if (emplist != null) {
					for (Employee emp : emplist) {
				%>
				<tr>
					<td><a href="EmployeeList?selectedEmpId=<%=emp.getEmployeeId()%>"><%=emp.getEmployeeId()%></a></td>
					<td><%=emp.getFirstName()%></td>
					<td><%=emp.getLastName()%></td>
					<td><a href="mailto:<%=emp.getEmail()%>"><%=emp.getEmail()%></a></td>
					<td><%=emp.getPhoneNumber()%></td>
					<td><fmt:formatDate value="<%=emp.getHireDate()%>" pattern="yyyy-MM-dd" /></td>
					<td><%=emp.getJobId()%></td>
					<td><%=emp.getSalary()%></td>
					<td><%=emp.getCommissionPct()%></td>
					<td><%=emp.getManagerId()%></td>
					<td><%=emp.getDepartmentId()%></td>
				</tr>
				<%
					}
				}
				%>
			</table>
		</form>
	</div>
	<br><br>
	<jsp:include page="/footer.html" />
</body>
</html>