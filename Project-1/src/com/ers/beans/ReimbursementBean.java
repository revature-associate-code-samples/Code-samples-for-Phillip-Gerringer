/**
 * 
 */
package com.ers.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ers.utils.ToolBelt;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * a java object representing the reimbursement table in the db
 * 
 * @author Phil
 *
 */
public class ReimbursementBean implements Bean, Serializable {
	private static final long serialVersionUID = 7473272274460449408L;
	
	private int reimbursment_id = 0;
	private double amount = 0.0;
	private Date submit_date = null;
	private Date resolve_date = null;
	private String detail = "";
	//private <something> attachment;
	private EmployeeBean submitter = new EmployeeBean();
	private EmployeeBean resolver = new EmployeeBean();
	private StatusBean status = new StatusBean();
	private TypeBean type = new TypeBean();
	
	SimpleDateFormat sdf = new SimpleDateFormat(ToolBelt.DATE_FORMAT);
	
	public ReimbursementBean() {
	}

	public ReimbursementBean(int reimbursment_id, double amount, Date submit_date, Date resolve_date, String detail,
			EmployeeBean submitter, EmployeeBean resolver, StatusBean status, TypeBean type, SimpleDateFormat sdf) {
		super();
		this.reimbursment_id = reimbursment_id;
		this.amount = amount;
		this.submit_date = submit_date;
		this.resolve_date = resolve_date;
		this.detail = detail;
		this.submitter = submitter;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
		this.sdf = sdf;
	}
	
	/**
	 * @return the id
	 */
	public int getReimbursement_id() {
		return reimbursment_id;
	}
	/**
	 * @param id the id to set
	 */
	public void setReimbursement_id(int reimbursment_id) {
		this.reimbursment_id = reimbursment_id;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the submit_date
	 */
	public String getSubmit_date() {
		if(submit_date != null) {
			return sdf.format(submit_date);
		}
		return null;
	}
	/**
	 * @param submit_date the submit_date to set
	 */
	public void setSubmit_date(Date submit_date) {
		this.submit_date = submit_date;
	}
	/**
	 * @return the resolve_date
	 */
	public String getResolve_date() {
		if(resolve_date != null) {
			return sdf.format(resolve_date);
		}
		return null;
	}
	public Date getNatural_Resolve_date() {
		return resolve_date;

	}
	/**
	 * @param resolve_date the resolve_date to set
	 */
	public void setResolve_date(Date resolve_date) {
		this.resolve_date = resolve_date;
	}
	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}
	/**
	 * @param detail the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	/**
	 * @return the status_id
	 */
	public StatusBean getStatus() {
		return status;
	}
	/**
	 * @param status_id the status_id to set
	 */
	public void setStatus(StatusBean status) {
		this.status = status;
	}
	/**
	 * @return the type_id
	 */
	public TypeBean getType() {
		return type;
	}
	/**
	 * @return the type_id
	 */
	public void setType(TypeBean type) {
		this.type = type;
	}
	/**
	 * @return the submitter
	 */
	public EmployeeBean getSubmitter() {
		return submitter;
	}
	/**
	 * @param submitter the submitter to set
	 */
	public void setSubmitter(EmployeeBean submitter) {
		this.submitter = submitter;
	}
	/**
	 * @return the resolver
	 */
	public EmployeeBean getResolver() {
		return resolver;
	}
	/**
	 * @param resolver the resolver to set
	 */
	public void setResolver(EmployeeBean resolver) {
		this.resolver = resolver;
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
