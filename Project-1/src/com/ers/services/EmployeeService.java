/**
 * 
 */
package com.ers.services;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.apache.log4j.Logger;

import com.ers.DAO.EmployeeDAO;
import com.ers.beans.EmployeeBean;
import com.ers.beans.LoginBean;
import com.ers.exceptions.DuplicateUserNameException;
import com.ers.exceptions.InvalidCredentialsException;
import com.ers.exceptions.UserNotFoundException;
import com.ers.io.Log;

/**
 * service layer object interacting with the EMPLOYEE table
 * @author Phil
 *
 */
public class EmployeeService {
	Logger log = Log.getInstance(this);
	EmployeeDAO dao = new EmployeeDAO();

	/**
	 * returns all of the employees in the DB
	 * @return
	 */
	public List<EmployeeBean> getAll() {
		return dao.getAll();
	}
	
	/**
	 * takes the JSONObject and puts into an EmployeeBean it then attempts to save/update based 
	 * on whether or not the employee_id exists. 
	 * @param json
	 * @throws SQLException 
	 */
	public EmployeeBean put(EmployeeBean bean) throws SQLException, DuplicateUserNameException {
		log.debug("bean coming in:  " + bean);
		// determine if we need to insert the bean or update the bean
		if(bean.getEmployee_id() == 0) {
			log.debug("requesting insert");
			// then we need to create a new one
			try {
				bean = dao.insert(bean);
			}
			catch (SQLIntegrityConstraintViolationException se) {
				throw new DuplicateUserNameException();
			}
		}
		else {	
			try {
				bean = dao.update(bean);
			}
			catch (SQLIntegrityConstraintViolationException se) {
				throw new DuplicateUserNameException();
			}
		}
		log.debug("bean coming out:  " + bean);
		
		return bean;
	}
	
	/**
	 * validates the user, returns a bean object of the Employee that has logged in.
	 * if the credentials do not match the user in the database, then throw an exception
	 * @param json
	 * @return
	 * @throws InvalidCredentialsException 
	 */
	public EmployeeBean validateUser(LoginBean loginBean) throws InvalidCredentialsException {
		EmployeeBean bean = new EmployeeBean();

		// get the credentials
		bean.setUser_name(loginBean.getUsername().trim());
		bean.setPassword(loginBean.getPassword().trim());

		// go to the DB for user validation
		bean = dao.getByID(bean);

		if(bean.getEmployee_id() == 0) {
			// a user didn't match the criteria
			throw new InvalidCredentialsException();
		}

		// else return our found employee
		return bean;
	}
	
	/**
	 * returns the employee found matching user and password for validation purposes
	 * @param user_name
	 * @param password
	 * @return
	 * @throws InvalidCredentialsException
	 */
	public EmployeeBean validateUser(String user_name, String password) throws InvalidCredentialsException {
		EmployeeBean bean = new EmployeeBean();

		// get the credentials
		bean.setUser_name(user_name.trim());
		bean.setPassword(password.trim());

		log.debug(bean);
		
		// go to the DB for user validation
		bean = dao.getByID(bean);
		log.debug(bean);

		if(bean.getEmployee_id() == 0) {
			// a user didn't match the criteria
			throw new InvalidCredentialsException();
		}

		// else return our found employee
		return bean;
	}
	
	/**
	 * returns the employee that matches the employee_id
	 * @param employee_id
	 * @return
	 * @throws UserNotFoundException
	 */
	public EmployeeBean getByID(int employee_id) throws UserNotFoundException {
		EmployeeBean bean = new EmployeeBean();
			
		// go to the db to get the data
		bean = dao.getByID(employee_id);

		if(bean.getEmployee_id() == 0) {
			// a user didn't match the criteria
			throw new UserNotFoundException();
		}

		// else return our found employee
		return bean;
	}
	
	/**
	 * returns the employee that matches the user name of user_id
	 * @param user_id
	 * @return
	 * @throws UserNotFoundException
	 */
	public EmployeeBean getByID(String user_id) throws UserNotFoundException {
		EmployeeBean bean = new EmployeeBean();
			
		// go to the db to get the data
		bean = dao.getByID(user_id);

		if(bean.getEmployee_id() == 0) {
			// a user didn't match the criteria
			throw new UserNotFoundException();
		}

		// else return our found employee
		return bean;
	}
}