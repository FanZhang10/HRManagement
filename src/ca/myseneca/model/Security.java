package ca.myseneca.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "SECURITY")
@NamedQueries({ @NamedQuery(name = "Security.findAll", query = "SELECT s FROM Security s"),
		@NamedQuery(name = "Security.checkLogin", query = "SELECT s.emp_id FROM Security s WHERE s.sec_id = :username AND s.sec_password = :pwd AND s.sec_status = 'A'") })
public class Security implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "EMPLOYEE_ID")
	private int emp_id;
	@Column(name = "SEC_ID")
	private String sec_id;
	@Column(name = "SEC_PASSWORD")
	private String sec_password;
	@Column(name = "SEC_STATUS")
	private char sec_status;

	public Security() {
	}

	// emp_id
	public void set_emp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public int get_emp_id() {
		return this.emp_id;
	}

	// sec_id
	public void set_sec_id(String sec_id) {
		this.sec_id = sec_id;
	}

	public String get_sec_id() {
		return this.sec_id;
	}

	// password
	public void set_password(String password) {
		this.sec_password = password;
	}

	public String get_password() {
		return this.sec_password;
	}

	// sec_status
	public void set_sec_status(char sec_status) {
		this.sec_status = sec_status;
	}

	public char get_sec_status() {
		return this.sec_status;
	}

}
