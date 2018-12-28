/**
 * 
 */
package com.fanatics.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * a set of utilities and static final members 
 * @author Phil
 *
 */
public class Tool {
	public static final String CONTENT_TYPE = "application/json";
	public static final String CHARACTER_ENCODING = "UTF-8";
	public static final String DATE_FORMAT = "MM-dd-yyyy";	

	/**
	 * converts a sql.Date object to a util.Date
	 * @param sqlDate
	 * @return
	 */
	public static java.util.Date convertDate(java.sql.Date sqlDate) {
		if (sqlDate != null) {
			return new java.util.Date(sqlDate.getTime());
		}
		return null;
	}
	
	/**
	 * converts a util.Date object to a sql.Date
	 * @param utilDate
	 * @return
	 */
	public static java.sql.Date convertDate(java.util.Date utilDate) {
		if (utilDate != null) {
			return new java.sql.Date(utilDate.getTime());
		}
		return null;
	}

	/**
	 * converts a util.Date object to a Timestamp
	 * @param utilDate
	 * @return
	 */
	public static Timestamp getTime(java.util.Date utilDate) {
		return new Timestamp(utilDate.getTime());
	}
	
	/**
	 * returns the current date
	 * @return
	 */
	public static java.util.Date getCurrentDate() {
		return new Date();
	}
	
	/**
	 * adds a number of types to the date.  Uses Calendar.YEAR, etc.
	 * @param date
	 * @param type
	 * @param number
	 * @return
	 */
	public static java.util.Date addTime(Date date, int type, int number ) {
		// convert date to calendar
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        // manipulate date
        cal.add(type, number);
        
        // convert calendar to date
        return (cal.getTime());
	}
	
	/**
	 * adds 10 years to the current time
	 * @return
	 */
	public static java.util.Date tenYears() {
		return addTime(getCurrentDate(), Calendar.YEAR, 10);
	}
	
}
