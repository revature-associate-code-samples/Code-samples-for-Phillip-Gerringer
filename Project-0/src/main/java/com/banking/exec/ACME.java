/**
 * 
 */
package com.banking.exec;

import com.banking.io.ConnectionFactory;

/**
 * Main entry for the ACME Banking program
 * 
 * @author pgerringer
 *
 */
public class ACME {
	/**
	 * main kicking off point of the banking systems
	 * @param args
	 */
	public static void main(String[] args) {
		
		// from the start, if you can't get a connection 
		// then we can't run the program
		if(ConnectionFactory.getInstance().getConnection() == null) {
			System.out.println("Unable to start ACME due to catastrophic failure");
		}
		else {
			UserSystem acme = new UserSystem();
			acme.run();
		}
	}
}
