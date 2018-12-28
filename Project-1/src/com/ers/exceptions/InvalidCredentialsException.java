/**
 * 
 */
package com.ers.exceptions;

import javax.servlet.http.HttpServletResponse;

/**
 * Exception when the user inputs an invalid user/password combo
 * 
 * @author Phil
 *
 */
public class InvalidCredentialsException extends ERSException {
	private static final long serialVersionUID = 2730811836232515243L;

	public InvalidCredentialsException () {
		super(HttpServletResponse.SC_UNAUTHORIZED, "Unable to login.  Invalid user name/password combination.");
	}
}
