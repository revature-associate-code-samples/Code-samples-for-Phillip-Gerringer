/**
 * 
 */
package com.banking.exec;

import java.util.List;

import org.apache.log4j.Logger;

import com.banking.beans.AccountBean;
import com.banking.beans.UserBean;
import com.banking.exceptions.InvalidEntryException;
import com.banking.exceptions.UserNotLoggedInException;
import com.banking.io.Log;
import com.banking.services.AccountHelper;

/**
 * Front facing code for controlling the account level functions.  User must be logged in.
 * 
 * @author pgerringer
 *
 */
public class AccountSubSystem {
	private Logger log = Log.getInstance(this); // get an instance to the logger class
	private UserBean user;
	
	/**
	 * This subsystem requires a valud user so it is needed for instantiation
	 * @param user
	 */
	public AccountSubSystem(UserBean userBean) {
		super();
		this.user = userBean;
	}
	
	/**
	 *  Entry point for subsystem execution
	 * @throws InvalidEntryException 
	 * @throws ClassNotFoundException 
	 */
	public void run() throws UserNotLoggedInException, InvalidEntryException, ClassNotFoundException {
		AccountBean bean = null;
		boolean exit = false;
		
		do {
			// load the list of accounts associated with the user
			List<AccountBean> list = AccountHelper.loadAccount(user);

			// show the user's welcome screen
			AccountHelper.accountSplashScreen(user);

			// get the users choice of actions
			bean = AccountHelper.scanActiveChoice(list, user);

			// if we have a valid bean then we must want to update or insert but we
			// need to grab the user id
			if(bean != null) {
				bean.setUser_id(user.getID());

				// if our bean has a 0 id then it is a new record
				if(bean.getID() == 0) {
					AccountHelper.insert(bean);
				}
				else {  // do the update
					// save any change
					AccountHelper.update(bean);
				}
			} 
			else { // no option chosen
				exit = true;
			}
		}
		while (!exit);
		
		log.debug(bean);
	}
}
