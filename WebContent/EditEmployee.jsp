<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@page import="ca.myseneca.model.Employee"%>
<%@page import="ca.myseneca.model.Department"%>
<%@page import="java.util.List"%>
<%@ page errorPage="/errorPage.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Edit Employee Page</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="styles/main.css" type="text/css" />
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
		<%
			Employee emp = (Employee) request.getAttribute("employee");
			@SuppressWarnings("unchecked")
			List<Department> deptlist = (List<Department>) request.getAttribute("deptlist");
			@SuppressWarnings("unchecked")
			List<String> joblist = (List<String>) request.getAttribute("joblist");
		%>
		<h1>Edit Employee Page</h1>
		<p>Please enter employee information</p>
	
	
		<form action="EmployeeList" method="post">
	
			<label>Employee ID:</label>
			<input type="number" name="employeeId" class="form-control" value="<%=emp.getEmployeeId()%>" readonly><br>
			
			<label>First Name:</label>
			<input type="text" name="fname" class="form-control"  value="<%=emp.getFirstName()%>"><br>
			
			
			<label>Last Name:</label>
			<input type="text" name="lname" class="form-control"  value="<%=emp.getLastName()%>"><br>
		
			
			<label>Email:</label>
			<input type="text" name="email" class="form-control"  value="<%=emp.getEmail()%>"><br>
			
			
			<label>Phone Number:</label>
			<input type="text" name="phone" class="form-control"  value="<%=emp.getPhoneNumber()%>"><br>
		
			<label>Hire Date:</label>
			
			<input type="date" name="hireDate" class="form-control"  value="<fmt:formatDate value="<%=emp.getHireDate()%>" pattern="yyyy-MM-dd" />"><br>
			
			<label>Job ID:</label>
			<select name="jobId" class="form-control" >
			  <%
			  	if (joblist != null) {
					for (String jobId : joblist) {
						if(jobId.equalsIgnoreCase(emp.getJobId())){
				%>
					  		<option selected><%=jobId%></option>
				<%
						}else{
				%>
					  		<option><%=jobId%></option>
				<%
						}
					}
			  	}
			  %>
			</select><br>
			<label>Salary:</label>
			<input type="number" name="salary" class="form-control"  value="<%=emp.getSalary()%>"><br>
			
			
			<label>Comm Pct:</label>
			<input type="number" name="commPCT" class="form-control"  value="<%=emp.getCommissionPct()%>"><br>
			
			<label>Manager ID:</label>
			<input type="number" name="managerId" class="form-control"  value="<%=emp.getManagerId()%>"><br>
			
			
			<label>Department:</label>
			<select name="deptId" class="form-control" >
			  <%
			  	if (deptlist != null) {
					for (Department dept : deptlist) {
						if(dept.getDepartmentId() == emp.getDepartmentId()){
				%>
					  		<option selected><%= dept.getDepartmentName() %></option>
				<%
						}else{
				%>
					  		<option><%= dept.getDepartmentName() %></option>
				<%
						}
					}
			  	}
			  %>
			</select><br>
			
			<input type="submit" name="action" value="Update Employee" class="btn btn-primary"> 
			<input type="submit" name="action" value="Delete Employee" class="btn btn-primary">
		</form>
	</div>
	<br><br>
	<jsp:include page="/footer.html" />
</body>
</html>