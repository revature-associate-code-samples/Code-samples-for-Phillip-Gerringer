/**
 * 
 */
package com.banking.beans;

import java.io.Serializable;

/**
 * Provides serialized access to user fields
 * 
 * @author pgerringer
 *
 */
public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id; // db key
	private String firstName;
	private String lastName;
	private String loginID;
	private String password;
	private boolean loggedIn = false; //is user logged in

	/**
	 *  no args construtor
	 */
	public UserBean() {
		super();
	}

    /**
     * login id arg constructor
     * 
     * @param loginID
     */
	public UserBean(String loginID) {
		super();
		this.loginID = loginID;
	}	
	
	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String userID) {
		this.loginID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", loginID=" + loginID
				+ ", password=" + password + ", loggedIn=" + loggedIn + "]";
	}
}
