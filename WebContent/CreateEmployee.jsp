<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@page import="ca.myseneca.model.Employee"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="ca.myseneca.model.Department"%>
<%@ page errorPage="/errorPage.jsp"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Edit Employee Page</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="styles/main.css" type="text/css" /><link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>

<body>

	<nav class="navbar navbar-default navbar-expand-sm">
		<div class="container-fluid">
		    <form action="Menu" method="get">
			    <ul class="nav navbar-nav">
				  <li><a title="Employee List" href="Menu" >Employee List</a></li>
			      <li class="active"><a title="Add New Employee" href="addEmployee">Add New Employee</a></li>
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
		<%
			@SuppressWarnings("unchecked")
			List<Department> deptlist = (List<Department>) request.getAttribute("deptlist");
			@SuppressWarnings("unchecked")
			List<String> joblist = (List<String>) request.getAttribute("joblist");
			int newEmpId = (int) request.getAttribute("newEmpId");
		%>
		<h1>Create Employee Page</h1>
		<p>Please enter employee information</p>
	
	
		<form action="EmployeeList" method="post">
	
			<label>Employee ID:</label>
			<input type="number" name="employeeId" class="form-control" value="<%=newEmpId%>" readonly><br>
			
			<label>First Name:</label>
			<input type="text" name="fname" class="form-control"><br>
			
			
			<label>Last Name:</label>
			<input type="text" name="lname" class="form-control"><br>
		
			
			<label>Email:</label>
			<input type="text" name="email" class="form-control"><br>
			
			
			<label>Phone Number:</label>
			<input type="text" name="phone" class="form-control"><br>
		
			<label>Hire Date:</label>
			<input type="date" name="hireDate" class="form-control"><br>
			
			<label>Job ID:</label>
			<select name="jobId" class="form-control" >
			  <%
				  if (joblist != null) {
						for (String jobId : joblist) {
					%>
						  <option><%=jobId%></option>
					<%
						}
				  }
			  %>
			</select><br>
			
			<label>Salary:</label>
			<input type="number" name="salary" class="form-control"><br>
			
			
			<label>Comm Pct:</label>
			<input type="number" name="commPCT" class="form-control"><br>
			
			<label>Manager ID:</label>
			<input type="number" name="managerId" class="form-control"><br>
			
			
			<label>Department:</label>
			<select name="deptId" class="form-control">
			  <%
			  	if (deptlist != null) {
					for (Department dept : deptlist) {
				%>
					  <option><%= dept.getDepartmentName() %></option>
				<%
					}
			  	}
			  %>
			</select><br>
			
			<input type="submit" name="action" value="Save Employee" class="btn btn-primary"> 
			<input type="reset" value="Reset" class="btn btn-primary"/>
			
		</form>
		<br><br>
		<a title="Return" href="Menu" class="return btn btn-primary">Return</a>
	</div>
	<br><br>
	<jsp:include page="/footer.html" />
</body>
</html>