/**
 * 
 */
package com.banking.dao;

import java.io.Serializable;
import java.util.List;

/**
 * DAO access interface to manage DAO behavior
 * 
 * @author pgerringer
 *
 */
public interface DAO <T, I extends Serializable> {
	
	/*
	 * returns all of the records in the generic table. 
	 */
	List<T> getAll();
	
	/*
	 * returns all of the records in the generic table with the associated id.
	 */
	List<T> getAll(I id);
	
	/*
	 * returns a specific instance of the generic object with the associated id.
	 */
	T getByID(I id);
	
	/*
	 * inserts an instance of the generic object into the database and returns 
	 * the saved object including the keys and any generated fields.
	 */
	T insert(T obj);
	
	/*
	 * updates the database with data from the passed in generic object.
	 */
	T update(T obj);
	
	/*
	 * deletes the passed in generic object from the database.
	 */
	void delete(T obj);
}
