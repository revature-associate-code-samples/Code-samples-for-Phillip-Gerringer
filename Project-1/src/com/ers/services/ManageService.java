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
 * service layer object interacting with the REIMBURSEMENT table and the manager function
 * @author Phil
 *
 */
public class ManageService {
	Logger log = Log.getInstance(this);
	ReimbursementDAO dao = new ReimbursementDAO();

	/**
	 * returns all of the reimbursements
	 * @return
	 */
	public List<ReimbursementBean> getAll() {
		return dao.getAll();
	}
	
	/**
	 * returns all of the reimbursements that are Submitted
	 * @return
	 */
	public List<ReimbursementBean> getAllSubmitted() {
		return dao.getAllSubmitted();
	}
	
	/**
	 * updates the status of the reimbursement to approved/denied
	 * @param json
	 * @throws SQLException 
	 */
	public ReimbursementBean put(ReimbursementBean bean) throws SQLException {
		log.debug("bean coming in:  " + bean);
		log.debug("requesting update");
		bean = dao.update(bean);
		log.debug("bean coming out:  " + bean);
		
		return bean;
	}
}
