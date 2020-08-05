package ca.myseneca.controller;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.myseneca.model.DBHelper;
import ca.myseneca.model.Employee;
import ca.myseneca.model.Security;

/**
 * Servlet implementation class Menu
 */
@WebServlet("/Menu")
public class Menu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DBHelper dbhelper = new DBHelper();
	private int globalCount; // instance variables are not thread-safe.
	private int empid;

	public Menu() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("System check Login!");

		String action = request.getParameter("action");

		if ("Logout".equals(action)) {
			checkLogOutAction(request, response);
		} else {
			checkLoginAction(request, response);
		}
	}

	/**
	 * Check user ID and password if pass, login user into system, show User Name if
	 * fail, direct to index.html page
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void checkLoginAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		while (globalCount == 0) {
			// get request parameters for email and password
			String sec_id = request.getParameter("username");
			String pwd = request.getParameter("password");

			globalCount++;
			
			Security sec = new Security();
			sec.set_sec_id(sec_id);
			sec.set_password(pwd);
			empid = dbhelper.checkLogin(sec);
		}
		if (empid != 0) {
			Employee emp = dbhelper.findEmpByID(empid);
		
			HttpSession session = request.getSession();
			session.setAttribute("name", emp.getFirstName()+ " "+  emp.getLastName());
			RequestDispatcher dispatcher = request.getRequestDispatcher("Menu.jsp");
			request.setAttribute("employee", emp);
			dispatcher.forward(request, response);
		} else {
			PrintWriter out = response.getWriter();
			out.println("<p style=\"color:red;\">Either username or password is wrong. " + "Please try again.</p>");
			checkLogOutAction(request, response);
		}

	}

	public void checkLogOutAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
		globalCount = 0;
		rd.include(request, response);
	}

}
