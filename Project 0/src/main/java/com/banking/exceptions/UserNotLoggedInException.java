package com.banking.exceptions;

/**
 * Exception spawned from a check when a user is not logged in but 
 * tries to access product (account) level functions
 * 
 * @author pgerringer
 *
 */
public class UserNotLoggedInException extends Exception {
	private static final long serialVersionUID = 5225848736469736168L;
	
	public UserNotLoggedInException () {
		super("User not logged in");
	}
}
