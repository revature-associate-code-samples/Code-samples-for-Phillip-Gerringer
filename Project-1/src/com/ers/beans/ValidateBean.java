/**
 * 
 */
package com.ers.beans;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * a java bean representing a server side validation object
 * @author Phil
 *
 */
public class ValidateBean  implements Bean, Serializable {
	private static final long serialVersionUID = 828005136849192521L;

	private String type = "";
	private String value = "";
	private int status = 200;
		
	public ValidateBean() {
	}

	public ValidateBean(String type, String value, int status) {
		super();
		this.type = type;
		this.value = value;
		this.status = status;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
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
