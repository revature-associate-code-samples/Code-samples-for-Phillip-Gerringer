/**
 * 
 */
package com.ers.DAO;

import java.io.Serializable;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * @author Phil
 *
 */
public interface DAO <T, I extends Serializable>{
	
	/**
	 * returns all rows from the database
	 * @return
	 */
	List<T> getAll();
	
	/**
	 * returns a T based on data retrieved from the DB that matches specific fields in the obj
	 * @param obj
	 * @return
	 */
	T getByID(T obj);
	
	/**
	 * returns a T based on data retrieved from the DB that matches the passed in key
	 * @param obj
	 * @return
	 */
	T getByID(I obj);
	
	/**
	 * inserts a row into the database matching the obj
	 * @param obj
	 * @return
	 */
	T insert(T obj) throws SQLIntegrityConstraintViolationException;
	
	/**
	 * updates the pattern that matches the obj in the database
	 * @param obj
	 * @return
	 */
	T update(T obj) throws SQLIntegrityConstraintViolationException ;
	
	/**
	 * deletes the pattern that matches the obj from the database
	 * @param obj
	 */
	void delete(T obj);
	
	/**
	 * returns the number of rows in the table based on the id
	 * @return
	 */
	int getCount(I obj);
	
	/**
	 * returns the number of rows in the table
	 * @return
	 */
	int getCount();
}
