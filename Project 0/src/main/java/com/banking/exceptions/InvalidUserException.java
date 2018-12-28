package com.banking.exceptions;

/**
 * Exception when the user inputs an invalid login id 
 * 
 * @author pgerringer
 *
 */
public class InvalidUserException extends Exception {
	private static final long serialVersionUID = -7866330362619588803L;

	public InvalidUserException () {
		super("Login Not Found");
	}
}
