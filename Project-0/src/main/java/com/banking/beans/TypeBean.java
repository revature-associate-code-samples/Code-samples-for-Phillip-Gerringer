/**
 * 
 */
package com.banking.beans;

import java.io.Serializable;

/**
 * Provides serialized access to product types
 * 
 * @author pgerringer
 *
 */
public class TypeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	
	public TypeBean() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "TypeBean [id=" + id + ", name=" + name + "]";
	} 
}
