/**
 * 
 */
package com.ers.beans;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * a java bean representing the TYPE table
 * @author Phil
 *
 */
public class TypeBean implements Bean, Serializable {
	private static final long serialVersionUID = 672754317148744412L;
	
	private int type_id = 0;
	private String name = ""; 

	public TypeBean() {
		super();
	}

	public TypeBean(int type_id, String name) {
		super();
		this.type_id = type_id;
		this.name = name;
	}

	/**
	 * returns the type id
	 * @return
	 */
	public int getType_id() {
		return type_id;
	}

	/**
	 *  sets the type id based on the input int
	 * @param type_id
	 */
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	/**
	 * returns the name string
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets the name string to the value of the input string
	 * @param name
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
