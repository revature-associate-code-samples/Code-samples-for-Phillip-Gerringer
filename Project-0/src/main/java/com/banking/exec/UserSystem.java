/**
 * 
 */
package com.banking.exec;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.banking.beans.UserBean;
import com.banking.exceptions.InvalidEntryException;
import com.banking.exceptions.InvalidPasswordException;
import com.banking.exceptions.InvalidUserException;
import com.banking.exceptions.UserNotLoggedInException;
import com.banking.io.Keyboard;
import com.banking.io.Log;
import com.banking.services.Tools;
import com.banking.services.UserHelper;

/**
 * The UserSystem is the control point for all User level processing.  Includes 
 * adding a user and login.  Once a successful login, the UserSystem will
 * springboard into the AccountSubSystem.
 * 
 * @author pgerringer
 *
 */
public class UserSystem {
	private Logger log = Log.getInstance(this); // get an instance to the logger class
	private UserBean user = new UserBean();
	
	private final String NEW_KEY = "#";  // key to create a new account
	
	/**
	 *  Entry point for program execution
	 */
	public void run() {
		log.debug("program start...");
		
		// this do while loop will keep the program running...until it stops
		do {
			// starting fresh so clean the slate
			user = new UserBean();
			
			// beginning we need to get the initial input
			String userID = scanFront().trim();
			log.debug("User ID entered " + userID);
			
			// if nothing entered, then exit
			if (userID.length() == 0) {
				break;
			}
			
			// check for new or existing user
			if(userID.equals(NEW_KEY)) {
				// user wants to create a new account
				log.debug("User wants to create a new account");
				try {
					UserHelper.processNewAccount();	
				}
				catch (ClassNotFoundException cnfe) {
					// this is a catastrophic failure 
					cnfe.printStackTrace();
					break;  // exit the while loop
				}
			}
			else { // user wants to use the keyed in user id to login
				try {
					user = UserHelper.processLogin(userID, user);
					log.debug(user.toString());
					
					// as long as the user is logged in then we can send them to the account subsystem
					if(user.isLoggedIn()) {
						AccountSubSystem sub = new AccountSubSystem(user);
						sub.run();
					}
				}
				catch (InvalidUserException iue) {
					log.debug("user doesn't exist");
					System.out.println("User ID doesn't exist. Create a New Account \n");
				}
				catch (InvalidPasswordException ipe) {
					System.out.println("\n");
					log.debug("invalid password");
				}
				catch (UserNotLoggedInException unlie) {
					log.debug("user not logged in");
					System.out.println("An error has occured - restarting");
				}
				catch (ClassNotFoundException cnfe) {
					cnfe.printStackTrace();
				} 
				catch (InvalidEntryException e) {
					e.printStackTrace();
				}
			}
		}
		while(true);
		
		System.out.println(Tools.VERTICAL_PAD + "...exiting...");
		log.debug("program end...");
	}
	
	/**
	 * sets up the main page and gets the initial input
	 * 
	 * @return
	 */
	public String scanFront() {
		System.out.println(Tools.VERTICAL_PAD + Tools.center("Welcome to ACME bank", Tools.DASH_SEP.length()));
		System.out.println(Tools.DASH_SEP);
		return(scanID());
	}
	
	/**
	 * asks the user to enter their ID or # to create a new user 
	 * Note:  ENTER will exit the program
	 * 
	 * @return
	 */
	public String scanID() {
		String choice = NEW_KEY;
		Scanner scan = Keyboard.getInstance();
		
		System.out.print("\nEnter ID (enter " + NEW_KEY + " to add a new user): ");
		choice = scan.nextLine();
		
		log.debug("User ID entered " + choice);
		return(choice);
	}
}
