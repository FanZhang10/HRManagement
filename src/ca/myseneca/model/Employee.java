package ca.myseneca.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the EMPLOYEES database table.
 * 
 */
@Entity
@Table(name = "EMPLOYEES")
@NamedQueries({ 
		@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
		@NamedQuery(name = "Employee.findEmployeeByDepartmentId", query = "SELECT e FROM Employee e WHERE e.departmentId = :deptId"),
		@NamedQuery(name = "Employee.updateEmployeeByID", query = "UPDATE Employee e SET e.firstName= :firstName, e.lastName = :lastName, "
				+ "e.email = :email, e.phoneNumber= :phoneNumber, e.hireDate = :hireDate, e.jobId = :jobId, e.salary = :salary,"
				+ "e.commissionPct = :commissionPct, e.managerId = :managerId, e.departmentId = :departmentId"
				+ " WHERE e.employeeId = :empId"),
		@NamedQuery(name = "Employee.findNewID", query = "SELECT MAX(e.employeeId) FROM Employee e")
		})
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EMPLOYEE_ID")
	private int employeeId;

	@Column(name = "COMMISSION_PCT")
	private double commissionPct;

	@Column(name = "DEPARTMENT_ID")
	private int departmentId;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Temporal(TemporalType.DATE)
	@Column(name = "HIRE_DATE")
	private Date hireDate;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "MANAGER_ID")
	private int managerId;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "SALARY")
	private double salary;
	
	@Column(name = "JOB_ID")
	private String jobId;
	// bi-directional many-to-one association to Department
	@OneToMany(mappedBy = "employee")
	private List<Department> departments;

	// bi-directional many-to-one association to Job

	

	public Employee() {
	}

	public int getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public double getCommissionPct() {
		return this.commissionPct;
	}

	public void setCommissionPct(double commissionPct) {
		this.commissionPct = commissionPct;
	}

	public int getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getHireDate() {
		return this.hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getManagerId() {
		return this.managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public double getSalary() {
		return this.salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public List<Department> getDepartments() {
		return this.departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public Department addDepartment(Department department) {
		getDepartments().add(department);
		department.setEmployee(this);

		return department;
	}

	public Department removeDepartment(Department department) {
		getDepartments().remove(department);
		department.setEmployee(null);

		return department;
	}

	public String getJobId() {
		return this.jobId;
	}

	public void setJob(String jobId) {
		this.jobId = jobId;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", phoneNumber=" + phoneNumber + ", hireDate=" + hireDate + ", job=" + jobId + ", salary="
				+ salary + ", commissionPct=" + commissionPct + ", managerId=" + managerId + ", departmentId="
				+ departmentId + "]\n";
	}

}