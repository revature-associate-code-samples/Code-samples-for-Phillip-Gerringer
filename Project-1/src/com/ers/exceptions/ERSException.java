/**
 * 
 */
package com.ers.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * parent class for the custom exceptions
 * @author Phil
 *
 */
public abstract class ERSException extends Exception {
	private static final long serialVersionUID = 7366893544189526239L;
	ObjectMapper mapper = new ObjectMapper();
	
	private int status_code;
	private String status_text;
	private final int exception=1;
	
	public ERSException(int sc, String st) {
		status_code = sc;
		status_text = st;
	}
	
	/**
	 * @return the error
	 */
	public int getException() {
		return exception;
	}

	/**
	 * @return the status_code
	 */
	public int getCode() {
		return status_code;
	}
	/**
	 * @param status_code the status_code to set
	 */
	public void setCode(int status_code) {
		this.status_code = status_code;
	}
	/**
	 * @return the status_text
	 */
	public String getText() {
		return status_text;
	}
	/**
	 * @param status_text the status_text to set
	 */
	public void setText(String status_text) {
		this.status_text = status_text;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {
		String json="{\"exception\":" + this.getException() + ", \"code\":" + this.getCode() + ", \"text\":\"" + this.getText() + "\"}";
		
		return json;
	}
	
	
	

}
