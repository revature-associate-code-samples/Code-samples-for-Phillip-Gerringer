/**
 * 
 */
package com.banking.services;

import java.util.List;

import com.banking.beans.TypeBean;
import com.banking.dao.TypeDAO;

/**
 * service layer for the type table access 
 * 
 * @author pgerringer
 *
 */
public class TypeHelper {
	
	public static List<TypeBean> getTypeList() {
		TypeDAO typeDAO = new TypeDAO();
		return (typeDAO.getAll());
	}
}
