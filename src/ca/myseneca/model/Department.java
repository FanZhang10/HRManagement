package ca.myseneca.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the DEPARTMENTS database table.
 * 
 */
@Entity
@Table(name = "DEPARTMENTS")
@NamedQueries({ 
		@NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d"),
		@NamedQuery(name = "Department.findDeptId", query = "SELECT d.departmentId FROM Department d WHERE d.departmentName = :deptName")
		})
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DEPARTMENT_ID")
	private int departmentId;

	@Column(name = "DEPARTMENT_NAME")
	private String departmentName;

	@Column(name = "LOCATION_ID")
	private int locationId;

	// bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name = "MANAGER_ID")
	private Employee employee;

	public Department() {
	}

	public int getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public int getLocationId() {
		return this.locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + ", locationId="
				+ locationId + ", employee=" + employee + "]\n";
	}

}