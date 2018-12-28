/**
 * 
 */
package com.test.beans;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ers.beans.LoginBean;

/**
 * @author Phil
 *
 */
public class LoginBeanTest {
	LoginBean bean = new LoginBean();
	LoginBean beanBlank = new LoginBean();
	
	private static final String USER_NAME="fcastle";
	private static final String NEW_USER_NAME="bbaker";
	
	private static final String PASSWORD="password";
	private static final String NEW_PASSWORD="password-x";
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		bean.setUsername(USER_NAME);
		bean.setPassword("password");
	}

	@Test
	public void testUsername() {
		assertEquals("",beanBlank.getUsername());
		assertEquals(USER_NAME, bean.getUsername());
		
		bean.setUsername(NEW_USER_NAME);
		assertEquals(NEW_USER_NAME, bean.getUsername());
	}
	
	@Test
	public void testPassword() {
		assertEquals("",beanBlank.getPassword());
		assertEquals(PASSWORD, bean.getPassword());
		
		bean.setPassword(NEW_PASSWORD);
		assertEquals(NEW_PASSWORD, bean.getPassword());
	}
}
