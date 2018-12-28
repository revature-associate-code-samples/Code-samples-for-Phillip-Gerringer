/**
 * 
 */
package com.banking.beans;

import java.io.Serializable;

/**
 * Provides serialized access to product (account) fields
 * 
 * @author pgerringer
 *
 */
public class AccountBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int user_id;
	private String type;
	private int type_id;
	private double balance;
	
	public AccountBean() {
		super();
	}
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	@Override
	public String toString() {
		return "AccountBean [id=" + id + ", user_id=" + user_id + ", type=" + type + ", type_id=" + type_id
				+ ", balance=" + balance + "]";
	}
	
}
