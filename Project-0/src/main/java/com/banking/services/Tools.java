/**
 * 
 */
package com.banking.services;

import java.text.NumberFormat;

/**
 * @author pgerringer
 *
 */
public class Tools {
	
	public static final String VERTICAL_PAD = "\n\n\n";
	public static final String DASH_SEP = "--------------------------------------------------------------------";
	public static final String WELCOME_STRING = "Hi/Hola/Welkom";
	
	/**
	 * returns true if there is text in the word
	 * 
	 * @param word
	 * @return
	 */
	public static boolean exists (String word) {
		if(word == null) {
			return false;
		}
		
		if(word.trim().length() == 0) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * returns a double formatted as currency
	 * 
	 * @param value
	 * @return
	 */
	public static String format(double value) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		return formatter.format(value);
	}
	
	/**
	 * pads a string to the size indicated with spaces to the left
	 *  
	 * @param str
	 * @param size
	 * @return
	 */
	public static String padLeft(String str, int size) {
	    return String.format("%1$" + size + "s", str);  
	}
	
	/**
	 * pads a string to the size indicated with spaces to the right
	 *  
	 * @param str
	 * @param size
	 * @return
	 */
	public static String padRight(String str, int size) {
	     return String.format("%1$-" + size + "s", str);  
	}
	
	/**
	 * does a quick formatting check to ensure the string is a double value
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isDouble(String value) {
		try {
			Double.parseDouble(value.trim());
		}
		catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	/**
	 * centers the passed in string for the amount of padSize
	 * 
	 * @param str
	 * @param padSize
	 * @return
	 */
	public static String center(String str, int padSize) {
		// determing the number of spaces for each side
		int spaces = (padSize - str.length()) / 2;
		
		return padRight(padLeft(str, spaces + str.length()),padSize);
	}
	
	/**
	 * returns true if the passed in int is odd
	 * 
	 * @param n
	 * @return
	 */
	public static boolean isOdd(int n) {
		if(n%2 == 0) {
			return false;
		}
		
		return true;
	}

}
