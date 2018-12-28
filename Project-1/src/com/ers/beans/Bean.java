/**
 * 
 */
package com.ers.beans;

import java.io.Serializable;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Interface to make all beans consistent
 * 
 * @author Phil
 *
 */
public interface Bean extends Serializable {
	static final long serialVersionUID = 1L;
	ObjectMapper mapper = new ObjectMapper();
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	String toString();
	
}
