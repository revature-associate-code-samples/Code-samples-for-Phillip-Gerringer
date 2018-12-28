/**
 * 
 */
package com.ers.services;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.ers.DAO.ReimbursementDAO;
import com.ers.beans.ReimbursementBean;
import com.ers.io.Log;

/**
 * service layer object interacting with the REIMBURSEMENT table
 * @author Phil
 *
 */
public class ReimbursementService {
	Logger log = Log.getInstance(this);
	ReimbursementDAO dao = new ReimbursementDAO();

	/**
	 * returns all of the reimbursement rows
	 * @return
	 */
	public List<ReimbursementBean> getAll() {
		return dao.getAll();
	}
	
	/**
	 * returns all of the reimbursement rows attached to the employee_id
	 * @param employee_id
	 * @return
	 */
	public List<ReimbursementBean> getAll(int employee_id) {
		log.debug("in getAll(" + employee_id + ")");
		return dao.getAll(employee_id);
	}
	
	/**
	 * returns the specific reimbursement record from the database
	 * @param reimbursement_id
	 * @return
	 */
	public ReimbursementBean getByID(int reimbursement_id) {
		return dao.getByID(reimbursement_id);
	}
	
	/**
	 * 
	 * @param bean
	 * @param employee_id
	 * @return
	 * @throws SQLException
	 */
	public ReimbursementBean put(ReimbursementBean bean, int employee_id) throws SQLException {
		log.debug("bean coming in:  " + bean);
		// determine if we need to insert the bean or update the bean
		if(bean.getReimbursement_id() == 0) {
			log.debug("requesting insert");
			// then we need to create a new one
			bean.getSubmitter().setEmployee_id(employee_id);
			bean.getStatus().setStatus_id(1);   // submitted
			
			bean = dao.insert(bean);
		}
		else {	
			log.debug("requesting update");
			//this is an update
			bean = dao.update(bean);
		}
		log.debug("bean coming out:  " + bean);
		
		return bean;
	}
	
	/**
	 * returns the count of the reimbursements for the submitter where the status is Submitted
	 * @param submitter_id
	 * @return
	 */
	public int getCount(Integer submitter_id) {
		return dao.getCount(submitter_id);
	}
	
	/**
	 * returns the total of Submitted reimbursements
	 * @return
	 */
	public int getCount() {
		return dao.getCount();
	}
}