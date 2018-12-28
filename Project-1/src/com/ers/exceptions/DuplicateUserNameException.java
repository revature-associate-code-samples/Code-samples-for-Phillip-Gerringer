package com.ers.exceptions;

import javax.servlet.http.HttpServletResponse;

/**
 * Exception when the input user name matches a unique user name in the db
 * @author Phil
 *
 */
public class DuplicateUserNameException extends ERSException  {
	private static final long serialVersionUID = 5086828836767921554L;
	
	public DuplicateUserNameException () {
		super(HttpServletResponse.SC_PRECONDITION_FAILED, "User Name already exists.  Please try another.");
	}
}
