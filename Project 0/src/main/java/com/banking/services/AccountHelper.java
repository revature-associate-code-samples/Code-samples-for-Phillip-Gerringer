/**
 * 
 */
package com.banking.services;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.banking.beans.AccountBean;
import com.banking.beans.TypeBean;
import com.banking.beans.UserBean;
import com.banking.dao.AccountDAO;
import com.banking.io.Keyboard;
import com.banking.io.Log;

/**
 * service layer for the account table access
 * 
 * @author pgerringer
 *
 */
public class AccountHelper {
	
	/** 
	 * loads up all the accounts that the user has
	 * 
	 * @param user
	 * @return
	 */
	public static List<AccountBean> loadAccount(UserBean user) {	
		AccountDAO dao = new AccountDAO();
		return(dao.getAll(user.getID()));
	}
	
	/**
	 * inserts the input account bean into the database
	 * 
	 * @param bean
	 */
	public static void insert(AccountBean bean) {
		AccountDAO dao = new AccountDAO();
		dao.insert(bean);
		return;
	}
	
	/**
	 * updates the database with the data from the input account bean 
	 * 
	 * @param bean
	 */
	public static void update(AccountBean bean) {
		AccountDAO dao = new AccountDAO();
		dao.update(bean);
		return;
	}
	
	/**
	 * displays the account balance screen to the user
	 * 
	 * @param user
	 */
	public static void accountSplashScreen(UserBean user) {

		System.out.println(Tools.VERTICAL_PAD + Tools.DASH_SEP);
		System.out.println(Tools.center(Tools.WELCOME_STRING + " " + user.getFirstName() + " " + 
									user.getLastName(), Tools.DASH_SEP.length()));
		System.out.println(Tools.DASH_SEP);
	}
	
	/**
	 * Asks the user to choose between actions
	 * 
	 * @return
	 * @throws ClassNotFoundException 
	 */
	public static AccountBean scanActiveChoice(List<AccountBean> accounts, UserBean userBean) throws ClassNotFoundException {
		Logger log = Log.getInstance(Class.forName("com.banking.services.AccountHelper"));
		Scanner scan = Keyboard.getInstance();
		AccountBean choice = null;
		
		int index = 0;
		HashMap<String, AccountBean> active = new HashMap<String, AccountBean>();
		
		// show the user the existing accounts as options
		for (AccountBean bean : accounts) {
			++index;
			System.out.println("  " + Tools.padRight(index + ") deposit/withdraw from " + bean.getType(), 35) + 
					Tools.padLeft("  " + Tools.format(bean.getBalance()),17) + " | " + bean.getType());
			active.put(String.valueOf(index), bean);  // save the choices for later 
		}
		
		// all products are shown so no more can be added
		if(index < TypeHelper.getTypeList().size()) {
			System.out.println("  A) Add a new product ");
		}
			
		// give the user the option to exit
		System.out.println("  Any other key to exit and logout ");
		
		System.out.print("\nYour choice:  ");
		String c = scan.nextLine().trim();
		
		log.debug("User ID entered " + c);
		
		// see if the choice is an active account
		if(active.containsKey(c)) {
			choice = alterBalance(active.get(c));
			
		}
		else if (c.toUpperCase().equals("A")) { 
			// add a new product
			// must pass in the list of products the user does have
			choice = scanNewProduct(active, userBean);
		}
		
		// anything else returns a null
		log.debug("user chose:  " + choice);
		return choice;
	}
	
	/**
	 * Displays the products that the user can add 
	 * 
	 * @param active
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static AccountBean scanNewProduct(HashMap<String, AccountBean> active, UserBean userBean) throws ClassNotFoundException {
		Logger log = Log.getInstance(Class.forName("com.banking.services.AccountHelper"));
		Scanner scan = Keyboard.getInstance();
		HashMap<String, TypeBean> inactive = new HashMap<String, TypeBean>();
		int index = 0;
		AccountBean newAccount = null;
		
		accountSplashScreen(userBean);
		
		// go through the types looking for the types that are not in the active list
		for (TypeBean type : TypeHelper.getTypeList()) {
			if(!containsProduct(type,active)) {
				// its possible to add the product
				++index;
				System.out.println("  " + index + ") " + type.getName());
				inactive.put(String.valueOf(index), type);  // save the choices for later
			}
		}
		
		System.out.print("\nProduct to add: ");
		String c = scan.nextLine().trim();
		
		log.debug("User ID entered " + c);
		
		// whatever product was chosen we need to make a new account bean for the product
		if(inactive.containsKey(c)) {
			TypeBean typeBean = inactive.get(c);
			
			// build the new account without user_id or account_id
			newAccount = new AccountBean();
			newAccount.setType_id(typeBean.getId());
			newAccount.setType(typeBean.getName());
			newAccount.setBalance(0);
		}
		
		return newAccount;
	}

	/**
	 * returns true if the active products hash includes the specific type
	 * 
	 * @param type
	 * @param active
	 * @return
	 */
	public static boolean containsProduct(TypeBean type, HashMap<String, AccountBean> active) {
		
		for(AccountBean bean : active.values()) {
			if(bean.getType().equals(type.getName())) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * allows the user to add/withdraw amounts.
	 *   
	 * @param bean
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static AccountBean alterBalance(AccountBean bean) throws ClassNotFoundException {
		Logger log = Log.getInstance(Class.forName("com.banking.services.AccountHelper"));
		Scanner scan = Keyboard.getInstance();
		boolean exit = false;
		
		System.out.println(Tools.VERTICAL_PAD + Tools.DASH_SEP);
		System.out.println(Tools.center("Deposit/Withdraw from " + bean.getType() + "  " + 
										Tools.format(bean.getBalance()),Tools.DASH_SEP.length()));
		System.out.println(Tools.DASH_SEP);
		
		System.out.println("  D) Deposit");
		System.out.println("  W) Withdraw");
		System.out.println("  Any other key to logout and exit");
		System.out.print("\nYour choice:  ");
		String choice = scan.nextLine().toUpperCase();
		log.debug("user chose: " + choice);
		
		if(choice.equals("D") | choice.equals("W")) {
			do {
				System.out.print("  How much? ");
				String amount = scan.nextLine().toUpperCase();

				switch(choice) {
				case "D":
					if (Tools.isDouble(amount)) {
						bean.setBalance(bean.getBalance() + Double.parseDouble(amount));
						exit = true;
					}
					else System.out.println("Amount is invalid.  Try again.");
					break;
				case "W":
					if (Tools.isDouble(amount)) {
						if(bean.getBalance() < Double.parseDouble(amount)) {
							System.out.println("  You are trying to withdraw more then you have.  Try again.");
						}
						else {
							bean.setBalance(bean.getBalance() - Double.parseDouble(amount));
							exit = true;
						}
					}
					else System.out.println("Amount is invalid.  Try again.");
					break;
				}
			}
			while(!exit);
		}
		
		return bean;
	}
}
