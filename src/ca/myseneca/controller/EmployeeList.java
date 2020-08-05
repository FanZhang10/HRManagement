package ca.myseneca.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.myseneca.model.DBHelper;
import ca.myseneca.model.Department;
import ca.myseneca.model.Employee;

@WebServlet("/EmployeeList")
public class EmployeeList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DBHelper dbhelper = new DBHelper();

	public EmployeeList() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getServletPath();
		
		if ("/addEmployee".equals(path)) {
			forwardAddNewEmployeePage(request, response);
		}
		
		if ("/searchEmployee".equals(path)) {
			forwardSearchEmployeePage(request, response);
		}
		
		String selectedEmpID = request.getParameter("selectedEmpId");
		if (selectedEmpID != null) {
			System.out.println("selectedEmpID is " + selectedEmpID);
			showSelectedEmployee(request, response);
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		
		if ("Find Employees By Department ID".equals(action)) {
			showEmployeeListByDeptAction(request, response);
		}
		if ("Find All Employees".equals(action)) {
			showAllEmployeeListAction(request, response);
		}
		
		
		if ("Update Employee".equals(action)) {
			try {
				updateEmployeeAction(request, response);
			} catch (ServletException | IOException | ParseException e) {
				System.err.println("unable to update employee");
				e.printStackTrace();
			}
		}
		if ("Delete Employee".equals(action)) {
			try {
				deleteEmployeeAction(request, response);
			} catch (ServletException | IOException | ParseException e) {
				System.err.println("unable to delete employee");
				e.printStackTrace();
			}
		}
		if ("Save Employee".equals(action)) {
			try {
				createEmployeeAction(request, response);
			} catch (ServletException | IOException | ParseException e) {
				System.err.println("unable to create employee");
				e.printStackTrace();
			}
		}
		
		if ("Go".equals(action)) {
			try {
				searchAction(request, response);
			} catch (ServletException | IOException | ParseException e) {
				System.err.println("unable to create employee");
				e.printStackTrace();
			}
		}

	}

	/**
	 * When user click Find All Employee Button
	 * Page redirect to Employee List page
	 * List all employees
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showAllEmployeeListAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("User clicked Find All Employee");

		List<Employee> emplistAll = dbhelper.findAllEmployee();

		RequestDispatcher dispatcherEmpList = request.getRequestDispatcher("EmployeeList.jsp");
		request.setAttribute("emplist", emplistAll);
		dispatcherEmpList.forward(request, response);
	}

	/**
	 * When user click Find Employee By Department Button
	 * PPage redirect to Employee List page
	 * List all employees belong to the department
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showEmployeeListByDeptAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("User clicked Find Employee By Dept");
		// get request parameters for deptID

		int deptId = Integer.parseInt(request.getParameter("deptId"));
		List<Employee> emplistDeptId = dbhelper.findEmployeeByDepartmentId(deptId);

		RequestDispatcher dispatcherEmpList = request.getRequestDispatcher("EmployeeList.jsp");
		request.setAttribute("emplist", emplistDeptId);
		dispatcherEmpList.forward(request, response);

	}

	/**
	 * Show the select employee information
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showSelectedEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int empid = Integer.parseInt(request.getParameter("selectedEmpId"));
		System.out.println("User select employee " + empid);
		Employee emp = dbhelper.findEmpByID(empid);
		List<String> joblist = dbhelper.findAllJobId();
		List<Department> deptlist = dbhelper.findAllDepartment();
		RequestDispatcher dispatcher = request.getRequestDispatcher("EditEmployee.jsp");
		request.setAttribute("deptlist", deptlist);
		request.setAttribute("joblist", joblist);
		request.setAttribute("employee", emp);
		dispatcher.forward(request, response);
	}

	/**
	 * Get the information from page and update DB
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void updateEmployeeAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		System.out.println("User clicked Update Employee button");

		String orgdate = request.getParameter("hireDate");
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = f.parse(orgdate);
		int deptId = dbhelper.getDeptIDByName(request.getParameter("deptId"));
		
		Employee emp = new Employee();
		emp.setEmployeeId(Integer.valueOf(request.getParameter("employeeId")));
		emp.setFirstName(request.getParameter("fname"));
		emp.setLastName(request.getParameter("lname"));
		emp.setEmail(request.getParameter("email"));
		emp.setPhoneNumber(request.getParameter("phone"));
		emp.setHireDate(date);
		emp.setJob(request.getParameter("jobId"));
		emp.setSalary(Double.valueOf(request.getParameter("salary")));
		emp.setCommissionPct(Double.valueOf(request.getParameter("commPCT")));
		emp.setManagerId(Integer.valueOf(request.getParameter("managerId")));
		emp.setDepartmentId(deptId);

		dbhelper.updateEmployee(emp);
		
		String message = "Success update an emplyee";
		redirectToMessagePage(request, response, message);
	}

	/**
	 * Delete the selected employee from DB
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void deleteEmployeeAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		System.out.println("User clicked Delete Employee button");
		int empid = Integer.parseInt(request.getParameter("employeeId"));
		System.err.println("User select employee " + empid + " to delete");
		dbhelper.deleteEmployee(empid);
		
		String message = "Success delete an emplyee";
		redirectToMessagePage(request, response, message);

	}

	/**
	 * Forward to Add New Employee Page when user click the link
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void forwardAddNewEmployeePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("addEmployee link click");
		int newEmpId = dbhelper.findNewEmpId();
		System.out.println("new empId is " + newEmpId);
		List<Department> deptlist = dbhelper.findAllDepartment();
		List<String> joblist = dbhelper.findAllJobId();
		RequestDispatcher dispatcherEmpList = request.getRequestDispatcher("CreateEmployee.jsp");
		request.setAttribute("deptlist", deptlist);
		request.setAttribute("joblist", joblist);
		request.setAttribute("newEmpId", newEmpId);
		dispatcherEmpList.forward(request, response);
	}
	
	/**
	 * Forward to Search Employee Page when user click the link
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void forwardSearchEmployeePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Search link click");
		RequestDispatcher dispatcherEmpList = request.getRequestDispatcher("SearchEmployee.jsp");
		dispatcherEmpList.forward(request, response);
	}

	/**
	 * When user click the Save button, create the employee
	 * Get the information from page
	 * Insert the employee into DB
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void createEmployeeAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		System.out.println("User clicked Create Employee link");

		String orgdate = request.getParameter("hireDate");
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = f.parse(orgdate);
	
		int deptId = dbhelper.getDeptIDByName(request.getParameter("deptId"));
		System.out.println("deptId "+ deptId);
		Employee emp = new Employee();
		emp.setEmployeeId(Integer.valueOf(request.getParameter("employeeId")));
		emp.setFirstName(request.getParameter("fname"));
		emp.setLastName(request.getParameter("lname"));
		emp.setEmail(request.getParameter("email"));
		emp.setPhoneNumber(request.getParameter("phone"));
		emp.setHireDate(date);
		emp.setJob(request.getParameter("jobId"));
		emp.setSalary(Double.valueOf(request.getParameter("salary")));
		emp.setCommissionPct(Double.valueOf(request.getParameter("commPCT")));
		emp.setManagerId(Integer.valueOf(request.getParameter("managerId")));
		emp.setDepartmentId(deptId);

		dbhelper.addEmployee(emp);
		
		String message = "Success create an emplyee";
		redirectToMessagePage(request, response, message);
	}
	
	/**
	 * When user click Go button
	 * Get the information from page
	 * Search the key word from table
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void searchAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		System.out.println("User clicked Search button");
		String search = request.getParameter("search");
		List<Object[]> resultList = (List<Object[]>) dbhelper.searchItem(search);
		RequestDispatcher dispatcherEmpList = request.getRequestDispatcher("SearchResult.jsp");
		request.setAttribute("search", search);
		request.setAttribute("resultList", resultList);
		dispatcherEmpList.forward(request, response);
	}
	
	/**
	 * Redirect to message page when user UPDATE, DELETE, CREATE employee
	 * @param request
	 * @param response
	 * @param message
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void redirectToMessagePage(HttpServletRequest request, HttpServletResponse response, String message)
			throws ServletException, IOException, ParseException {
		
		RequestDispatcher dispatcherEmpList = request.getRequestDispatcher("Message.jsp");
		request.setAttribute("message", message);
		dispatcherEmpList.forward(request, response);
		
	}

}
