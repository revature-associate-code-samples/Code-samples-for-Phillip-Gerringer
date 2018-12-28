package com.ers.beans;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * a java bean for passing the count of rows in the data table 
 * 
 * @author Phil
 *
 */
public class CountBean implements Bean, Serializable {
	private static final long serialVersionUID = -7449473949243673074L;
	
	private String data;
	private int count;
	
	public CountBean() {
	}
	
	public CountBean(String data, int count) {
		super();
		this.data = data;
		this.count = count;
	}
	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
	/*
	 * (non-Javadoc)
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
