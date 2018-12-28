package com.banking.exceptions;

/**
 * Exception for identifying when a user enters an incorrect password
 * 
 * @author pgerringer
 *
 */
public class InvalidPasswordException extends Exception {
	private static final long serialVersionUID = -3339569714930716055L;

	public InvalidPasswordException () {
		super("Password not valid");
	}
}
