/**
 * 
 */
package com.ers.services;

import java.util.List;

import com.ers.DAO.RoleDAO;
import com.ers.beans.RoleBean;

/**
 * service layer object interacting with the ROLE table
 * @author Phil
 *
 */
public class RoleService {
	RoleDAO dao = new RoleDAO();
	
	/**
	 * service returns a JSONArray (empty or full doesn't matter)
	 * @return
	 */
	public List<RoleBean> getAll() {
		// an empty array is ok at this point
		return dao.getAll();
	}
}
