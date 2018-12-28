package com.banking.exceptions;

/**
 * Exception when the user keys in an invalid entry
 * 
 * @author pgerringer
 *
 */
public class InvalidEntryException extends Exception {
	private static final long serialVersionUID = -797931828372436141L;

	public InvalidEntryException () {
		super("Bad Entry Format");
	}
	
}
