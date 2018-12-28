/**
 * 
 */
package com.ers.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

/**
 * a set of utilities and static final members 
 * @author Phil
 *
 */
public class ToolBelt {
	   
	public static final String CONTENT_TYPE = "application/json";
	public static final String CHARACTER_ENCODING = "UTF-8";
	public static final String DATE_FORMAT = "MM-dd-yyyy";
	
	/**
	 * Pulls the body from the request...this is a copy and needs to be understood
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String getBody(HttpServletRequest request) throws IOException {
	    String body = null;
	    StringBuilder stringBuilder = new StringBuilder();
	    BufferedReader bufferedReader = null;

	    try {
	        InputStream inputStream = request.getInputStream();
	        if (inputStream != null) {
	            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	            char[] charBuffer = new char[128];
	            int bytesRead = -1;
	            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
	                stringBuilder.append(charBuffer, 0, bytesRead);
	            }
	        } else {
	            stringBuilder.append("");
	        }
	    } catch (IOException ex) {
	        throw ex;
	    } finally {
	        if (bufferedReader != null) {
	            try {
	                bufferedReader.close();
	            } catch (IOException ex) {
	                throw ex;
	            }
	        }
	    }

	    body = stringBuilder.toString();
	    return body;
	}

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
	
}
