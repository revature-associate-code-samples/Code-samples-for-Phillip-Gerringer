package com.fanatics.util;

import org.apache.log4j.Logger;

/**
 * Factory Class specfically for controlling access to a Logger instance
 * 
 * @author Phil
 *
 */
public class Log {
	
	/**
	 * Returns an instance of Logger.  Does not use the factory model
	 * because it is not necessarily needed but can easily be implemented.
	 * 
	 * @param obj
	 * @return
	 */
	public static Logger getInstance(Object obj) {
		
		return Logger.getLogger(obj.getClass());
	}
	
	/**
	 * Allows static methods to use the logger effectively
	 * @param clazz
	 * @return
	 */
	public static Logger getInstance(Class<? extends Object> clazz) {
		return Logger.getLogger(clazz);
	}
}
