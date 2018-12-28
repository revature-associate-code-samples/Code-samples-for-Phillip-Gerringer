package com.banking.io;

import java.util.Scanner;

/**
 * Factory Class specifically for controlling access to a Scanner instance
 * 
 * No real reason for this I just didn't want to write the scanner string
 * a ton of times...so laziness
 * 
 * @author pgerringer
 *
 */
public class Keyboard {
	private static Scanner scanner = new Scanner(System.in);
	
	/**
	 * Returns the previously created Scanner instance
	 * @return
	 */
	static public Scanner getInstance() {
		
		if (scanner == null) {
			scanner = new Scanner(System.in);
		}
		return scanner;
	}
}
