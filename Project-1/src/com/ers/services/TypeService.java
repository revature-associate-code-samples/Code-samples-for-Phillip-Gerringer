/**
 * 
 */
package com.ers.services;

import java.util.List;

import com.ers.DAO.TypeDAO;
import com.ers.beans.TypeBean;

/**
 * service layer object interacting with the TYPE table
 * @author Phil
 *
 */
public class TypeService {
	TypeDAO dao = new TypeDAO();
	
	/**
	 * service returns a JSONArray (empty or full doesn't matter)
	 * @return
	 */
	public List<TypeBean> getAll() {
		return dao.getAll();
	}
}
