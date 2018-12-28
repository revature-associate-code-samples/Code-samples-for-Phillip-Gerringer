/**
 * 
 */
package com.ers.services;

import java.util.List;

import com.ers.DAO.StatusDAO;
import com.ers.beans.StatusBean;

/**
 * service layer object interacting with the STATUS table
 * @author Phil
 *
 */
public class StatusService {
	StatusDAO dao = new StatusDAO();
	
	/**
	 * service returns a List<StatusBean> (empty or full doesn't matter)
	 * @return
	 */
	public List<StatusBean> getAll() {
		// an empty array is ok at this point
		return dao.getAll(); 
	}
}
