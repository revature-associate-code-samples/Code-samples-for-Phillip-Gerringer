/**
 * 
 */
package com.ers.exceptions;

import javax.servlet.http.HttpServletResponse;

/**
 * Exception when the employee cannot be found.  Used on login (user) or employee info (employee)
 * 
 * @author Phil
 *
 */
public class UserNotFoundException extends ERSException {
	private static final long serialVersionUID = -163622717359872436L;

	public UserNotFoundException () {
		super(HttpServletResponse.SC_NOT_FOUND, "Unable to find the employee.");
	}

}
