/**
 * 
 */
package com.ers.beans;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * a java bean defining the employee table values
 * 
 * @author Phil
 *
 */
public class EmployeeBean implements Bean, Serializable {
	private static final long serialVersionUID = -3095823489641237568L;
	
	private int employee_id=0;
	private String first_name="";
	private String last_name="";
	private String user_name="";
	private String password="";	
	private String email="";
	private RoleBean role = new RoleBean();
	
	public EmployeeBean() {
		
	}
	
	public EmployeeBean(int employee_id, String first_name, String last_name, String user_name, String password,
			String email, RoleBean role) {
		super();
		this.employee_id = employee_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.user_name = user_name;
		this.password = password;
		this.email = email;
		this.role = role;
	}
	
	/**
	 * @return the employee_id
	 */
	public int getEmployee_id() {
		return employee_id;
	}
	/**
	 * @param employee_id the employee_id to set
	 */
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	/**
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}
	/**
	 * @param first_name the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}
	/**
	 * @param last_name the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}
	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the role_id
	 */
	public RoleBean getRole() {
		return role;
	}
	/**
	 * @param role_id the role_id to set
	 */
	public void setRole(RoleBean role) {
		this.role = role;
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String json="";
		
		try {
			json = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return json;
	}
}
