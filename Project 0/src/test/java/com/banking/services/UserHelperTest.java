package com.banking.services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.banking.beans.UserBean;

public class UserHelperTest {
	private UserBean userGood;
	private UserBean userBad;
	private UserBean userBad2;
	private UserBean userEmpty;
	
	
	@Before
	public void setUp() throws Exception {
		userGood = new UserBean();
		userGood.setFirstName("Bob");
		userGood.setLastName("Builder");
		userGood.setLoginID("IamBob");
		userGood.setPassword("password");
		
		userBad = new UserBean();
		userBad.setFirstName("Bob");
		userBad.setLastName("Builder");
		userBad.setLoginID("Bob");
		userBad.setPassword("password");
		
		userBad2 = new UserBean();
		userBad2.setFirstName("Bob");
		userBad2.setLastName("Builder");
		userBad2.setLoginID("Builder");
		userBad2.setPassword("password");
		
		userEmpty = new UserBean();
	}

	@Test
	public void test() {
		assertEquals(true, UserHelper.validateFirstName(userGood));
		assertEquals(true, UserHelper.validateLastName(userGood));
		assertEquals(true, UserHelper.validateLoginID(userGood));
		assertEquals(true, UserHelper.validatePassword(userGood));
		
		assertEquals(true, UserHelper.validateFirstName(userBad));
		assertEquals(true, UserHelper.validateLastName(userBad));
		assertEquals(false, UserHelper.validateLoginID(userBad));
		assertEquals(true, UserHelper.validatePassword(userBad));
		
		assertEquals(false, UserHelper.validateLoginID(userBad2));
		
		assertEquals(false, UserHelper.validateFirstName(userEmpty));
		assertEquals(false, UserHelper.validateLastName(userEmpty));
		assertEquals(false, UserHelper.validateLoginID(userEmpty));
		assertEquals(false, UserHelper.validatePassword(userEmpty));
		
	}

}
