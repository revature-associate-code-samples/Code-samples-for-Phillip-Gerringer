/**
 * 
 */
package com.ers.beans;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * A java bean representing login information
 * 
 * @author Phil
 *
 */
public class LoginBean implements Bean, Serializable {
	private static final long serialVersionUID = -3084689619148706015L;
	
	private String username = "";
	private String password = "";
	
	public LoginBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginBean(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	
	/* (non-Javadoc)
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
