/**
 * 
 */
package com.ers.beans;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * a java bean representing the Status table
 * @author Phil
 *
 */
public class StatusBean implements Bean, Serializable {
	private static final long serialVersionUID = -1138954604263754113L;
	
	private int status_id = 0;
	private String name = "";
	
	public StatusBean() {
	}
	public StatusBean(int status_id, String name) {
		super();
		this.status_id = status_id;
		this.name = name;
	}
	/**
	 * @return the id
	 */
	public int getStatus_id() {
		return status_id;
	}
	/**
	 * @param id the id to set
	 */
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
