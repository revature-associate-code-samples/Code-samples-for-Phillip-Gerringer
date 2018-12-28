/**
 * 
 */
package com.ers.beans;

import java.io.Serializable;

/**
 * a small java bean used only to pull a specific Reimbursement row based on ID
 * @author Phil
 *
 */
public class PseudoReimbBean implements Bean, Serializable {
	private static final long serialVersionUID = 8706064818028855823L;
	private int id = 0;

	public PseudoReimbBean() {
	}

	public PseudoReimbBean(int id) {
		super();
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
}
