/**
 * 
 */
package com.banking.services;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.banking.beans.UserBean;
import com.banking.dao.UserDAO;
import com.banking.exceptions.InvalidPasswordException;
import com.banking.exceptions.InvalidUserException;
import com.banking.io.Keyboard;
import com.banking.io.Log;
import com.banking.dao.DAO;

/**
 * service layer for the user table access
 * 
 * @author pgerringer
 *
 */
public class UserHelper {
	
	/*
	 * invalid user strings
	 */
	private static final String INV_FIRST_NAME = "Invalid First Name. ";
	private static final String INV_LAST_NAME = "Invalid Last Name. ";
	private static final String INV_LOGINID_NAME = "Invalid User ID.  Cannot be the same as your First or Last Name.";
	private static final String INV_PASSWORD = "Invalid Password. ";
	
	/**
	 * use the DAO to get a UserBean from the DB based on the login ID
	 * 
	 * @param loginID
	 * @return
	 */
	public static UserBean loadUser (String loginID) {
		UserDAO dao = new UserDAO();
		return(dao.getByLoginID(loginID));
	}
	
	/**
	 * use the DAO to insert a new user
	 * 
	 * @param user
	 */
	public static void insertNew(UserBean user) {
		DAO<UserBean,Integer> dao = new UserDAO();
		dao.insert(user);
	}
	
	/**
	 * processes the request for a new account.  Once the new account is created the user will need to login using the new account
	 * 
	 * @throws ClassNotFoundException 
	 */
	public static void processNewAccount() throws ClassNotFoundException {
		Logger log = Log.getInstance(Class.forName("com.banking.services.UserHelper"));
		Scanner input = Keyboard.getInstance();
		
		log.debug("in new account");
		UserBean user = new UserBean();
		do {
			System.out.print("Enter First Name:  ");
			user.setFirstName(input.nextLine().trim());
			System.out.print("Enter Last Name:  ");
			user.setLastName(input.nextLine().trim());
			System.out.print("Enter a User ID:  ");
			user.setLoginID(input.nextLine().trim());
			System.out.print("Enter a password:  ");
			user.setPassword(input.nextLine().trim());
			
			// now all temp data has been keyed in we can validate
			if (!UserHelper.validateFirstName(user)) {
				System.out.println(INV_FIRST_NAME);
			}
			else if (!UserHelper.validateLastName(user)) {
				System.out.println(INV_LAST_NAME);
			}
			else if (!UserHelper.validateLoginID(user)) {
				System.out.println(INV_LOGINID_NAME);
			}
			else if (!UserHelper.validatePassword(user)) {
				System.out.println(INV_PASSWORD);
			}
			else {
				// do a verification before we continue
				System.out.print("Creating the account, are you sure? Y/N ");
				String choice = input.nextLine().trim().toUpperCase();
				if(choice.equals("Y")) {  // assume all else is no
					UserHelper.insertNew(user);
					System.out.println("User " + user.getLoginID() + " created.");
					break;
				}
				else {
					break;
				}
			}
		} 
		while(true);
		
		log.debug(user.toString());
	}
	
	/**
	 * Runs the login process
	 * 
	 * @param userID
	 * @param user
	 * @return
	 * @throws InvalidUserException
	 * @throws InvalidPasswordException
	 * @throws ClassNotFoundException
	 */
	public static UserBean processLogin(String userID, UserBean user) throws InvalidUserException, InvalidPasswordException, ClassNotFoundException {
		Logger log = Log.getInstance(Class.forName("com.banking.services.UserHelper"));
		
		// load in the user account
		user = UserHelper.loadUser(userID);
		log.debug(user);
		
		// see if the user is valid
		if(user == null)
			throw new InvalidUserException();
		
		log.debug(user.toString());
		
		// check the password
		int count = 1;
		String password;
		while ((count <= 3) & !user.isLoggedIn()) {
			// ask for the password (you only get 3 chances to make it right
			password = scanPassword();

			if (user.getPassword().equals(password)) {
				user.setLoggedIn(true);
				log.debug(user.toString());
				break;
			}
			
			++count;
			
			System.out.print("Invalid password. ");
			switch (count) {
			case 2:
				System.out.println("Second chance.");
				break;
			case 3:
				System.out.println("Last chance.");
				break;
			default:
				throw new InvalidPasswordException();  // we didn't get a valid login
			}
		}
		return user;
	}
	
	/**
	 * asks the user to enter a password
	 *  
	 * @return
	 * @throws ClassNotFoundException 
	 */
	public static String scanPassword() throws ClassNotFoundException {
		Logger log = Log.getInstance(Class.forName("com.banking.services.UserHelper"));
		
		Scanner scan = Keyboard.getInstance();
		
		String choice = "";
		
		System.out.print("Enter Password: ");
		choice = scan.nextLine();
		
		log.debug("User Password entered " + choice);
		return(choice);
	}
	
	/**
	 * field validation for the last name
	 * 
	 * @param bean
	 * @return
	 */
	public static boolean validateFirstName (UserBean bean) {
		
		// must have a first name
		if(!Tools.exists(bean.getFirstName())) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * field validation for the last name
	 * 
	 * @param bean
	 * @return
	 */
	public static boolean validateLastName (UserBean bean) {
		
		// must have a last name
		if(!Tools.exists(bean.getLastName())) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * field validation for the login ID
	 * 
	 * @param bean
	 * @return
	 */
	public static boolean validateLoginID (UserBean bean) {
		
		// must have a user id
		if(!Tools.exists(bean.getLoginID())) {
			return false;
		}
		
		// login cannot be equal to first name
		if (bean.getLoginID().trim().equals(bean.getFirstName().trim())) {
			return false;
		}
		
		// login cannot be equal to last name
		if (bean.getLoginID().trim().equals(bean.getLastName().trim())) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * field validation for the password
	 * 
	 * @param bean
	 * @return
	 */
	public static boolean validatePassword (UserBean bean) {
		
		// must have a password
		if(!Tools.exists(bean.getPassword())) {
			return false;
		}
		
		return true;
	}
}
