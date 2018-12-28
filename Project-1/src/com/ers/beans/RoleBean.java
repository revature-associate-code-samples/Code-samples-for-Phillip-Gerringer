/**
 * 
 */
package com.ers.beans;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * a java bean representing the Role table
 * @author Phil
 *
 */
public class RoleBean implements Bean, Serializable {
	private static final long serialVersionUID = -2988880011246179669L;
	
	private int role_id = 0;
	private String name = "";
	
	
	public RoleBean() {
		super();
	}
	public RoleBean(int role_id, String name) {
		super();
		this.role_id = role_id;
		this.name = name;
	}
	/**
	 * @return the id
	 */
	public int getRole_id() {
		return role_id;
	}
	/**
	 * @param id the id to set
	 */
	public void setRole_id(int role_id) {
		this.role_id = role_id;
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
