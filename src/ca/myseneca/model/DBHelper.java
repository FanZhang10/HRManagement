package ca.myseneca.model;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class DBHelper {
	private static EntityManagerFactory emf;
	private static EntityManager em;

	public static void getEntityManager() {
		// Create the EntityManager
		emf = Persistence.createEntityManagerFactory("AS2");
		em = emf.createEntityManager();
		em.getTransaction().begin();
	}

	public static void closeEntityManager() {
		em.getTransaction().commit();
		// Close the EntityManager
		em.close();
		emf.close();
	}

	/**
	 * Check user username and password exist in DB or not
	 * 
	 * @param sec
	 * @return
	 */
	public int checkLogin(Security sec) {

		int empid = 0;
		getEntityManager();
		try {
			Query query = em.createNamedQuery("Security.checkLogin", Security.class);
			query.setParameter("username", sec.get_sec_id());
			query.setParameter("pwd", sec.get_password());
			empid = (int) query.getSingleResult();

		} catch (Exception ex) {
			System.out.println("Can't login!");
			System.out.println(ex.getMessage());
		} finally {
			closeEntityManager();
		}
		return empid;
	}

	/**
	 * Find the employee by ID
	 * @param empID
	 * @return
	 */
	public Employee findEmpByID(int empID) {

		getEntityManager();
		Employee employee = null;
		try {
			employee = em.find(Employee.class, empID);
		} catch (Exception ex) {
			System.err.println("Can't find employee!");
			System.out.println(ex.getMessage());
		} finally {
			closeEntityManager();
		}
		return employee;
	}

	/**
	 * Find Employee by department ID
	 * @param deptId
	 * @return
	 */
	public List<Employee> findEmployeeByDepartmentId(int deptId) {
		getEntityManager();
		List<Employee> emplist = null;
		try {
			TypedQuery<Employee> query = em.createNamedQuery("Employee.findEmployeeByDepartmentId", Employee.class);
			query.setParameter("deptId", deptId);
			emplist = query.getResultList();
		} catch (Exception ex) {
			System.out.println("Can't employee by DepartmentID!");
		} finally {
			closeEntityManager();
		}
		return emplist;
	}

	/**
	 * Find all Employees
	 * @return list of employees
	 */
	public List<Employee> findAllEmployee() {
		getEntityManager();
		List<Employee> emplist = null;
		try {
			TypedQuery<Employee> query = em.createNamedQuery("Employee.findAll", Employee.class);
			emplist = query.getResultList();
		} catch (Exception ex) {
			System.out.println("Can't find all Employees!");
		} finally {
			closeEntityManager();
		}
		return emplist;
	}

	/**
	 * Delete the selected employee
	 * @param empId
	 */
	public void deleteEmployee(int empId) {
	
		getEntityManager();

		try {

			Employee deleteEmp = em.find(Employee.class, empId);
			em.remove(deleteEmp);

		} catch (Exception ex) {
			System.err.println("Can't delete Employees!");
		} finally {

			closeEntityManager();
		}	
	}

	/**
	 * Add new employee
	 * @param emp
	 */
	public void addEmployee(Employee emp) {
		getEntityManager();
		System.out.println(emp.toString());
		try {
			em.persist(emp);
		} catch (Exception ex) {
			System.out.println("Can't update Employees!");
		} finally {

			closeEntityManager();
		}
	}

	/**
	 * Update the selected employee
	 * @param emp
	 */
	public void updateEmployee(Employee emp) {
	
		getEntityManager();
		try {

			Employee updateEmp = em.find(Employee.class, emp.getEmployeeId());
			updateEmp.setFirstName(emp.getFirstName());
			updateEmp.setLastName(emp.getLastName());
			updateEmp.setEmail(emp.getEmail());
			updateEmp.setPhoneNumber(emp.getPhoneNumber());
			updateEmp.setHireDate(emp.getHireDate());
			updateEmp.setJob(emp.getJobId());
			updateEmp.setSalary(emp.getSalary());
			updateEmp.setCommissionPct(emp.getCommissionPct());
			updateEmp.setManagerId(emp.getManagerId());
			updateEmp.setDepartmentId(emp.getDepartmentId());
			
		} catch (Exception ex) {
			System.out.println("Can't update Employees!");
		} finally {

			closeEntityManager();
		}
	}

	/**
	 * Find the largest ID and add 1
	 * @return the new employee ID
	 */
	public int findNewEmpId() {
		int empid = 0;
		getEntityManager();
		try {
			Query query = em.createNamedQuery("Employee.findNewID", Employee.class);
			empid = (int) query.getSingleResult();
			System.out.println("empid from DB is " + empid);
			empid++;
		} catch (Exception ex) {
			System.out.println("Can't find new ID!");
			System.out.println(ex.getMessage());
		} finally {
			closeEntityManager();
		}
		return empid;
	}

	/**
	 * List all the Department
	 * @return
	 */
	public List<Department> findAllDepartment() {
		List<Department> deptlist = null;
		getEntityManager();

		try {
			TypedQuery<Department> query = em.createNamedQuery("Department.findAll", Department.class);
			deptlist = query.getResultList();
		} catch (Exception ex) {
			System.out.println("Can't find all Department!");
		} finally {
			closeEntityManager();
		}
		return deptlist;
	}
	
	/**
	 * List all Job ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> findAllJobId() {
		List<String> jobIdList = null;
		getEntityManager();

		try {
			String jobIdSql = "SELECT DISTINCT e.jobId FROM Employee e";
			Query query = em.createQuery(jobIdSql);
			jobIdList = query.getResultList();
		} catch (Exception ex) {
			System.out.println("Can't find all Job ID!");
		} finally {
			closeEntityManager();
		}
		return jobIdList;
	}

	/**
	 * Use Department ID to get the Department Name
	 * @param deptName
	 * @return
	 */
	public int getDeptIDByName(String deptName) {
		int deptID = 0;
		getEntityManager();

		try {
			Query query = em.createNamedQuery("Department.findDeptId", Department.class);
			query.setParameter("deptName", deptName);

			deptID = (int) query.getSingleResult();

		} catch (Exception ex) {
			System.err.println("Can't find Department Name!");
		} finally {
			closeEntityManager();
		}
		return deptID;
	}

	/**
	 * Search the Item in Employee and Department table
	 * @param item
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> searchItem(String item) {
		getEntityManager();
		List<Object[]> results = null;
		try {
			// Define query String
			final String searchQuery = "SELECT e.firstName, e.lastName, e.email, e.salary, e.jobId, e.phoneNumber, d.departmentName FROM Employee e, Department d " + 
					"WHERE e.departmentId = d.departmentId" + 
					" AND (UPPER(e.firstName) LIKE UPPER(:item)" + 
					"	OR UPPER(e.lastName) LIKE UPPER(:item)" + 
					"	OR UPPER(e.email) LIKE UPPER(:item)" + 
					"	OR e.phoneNumber LIKE :item" + 
					"	OR UPPER(d.departmentName) LIKE UPPER(:item))";
			// Define the query
			Query query = em.createQuery(searchQuery);
			query.setParameter("item", "%" + item + "%");
			// Query and get result
			results = query.getResultList();

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			closeEntityManager();
		}
		return results;

	}
}
