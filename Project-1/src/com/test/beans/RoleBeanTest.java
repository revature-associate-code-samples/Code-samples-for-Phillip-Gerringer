package com.test.beans;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ers.beans.RoleBean;

public class RoleBeanTest {
	private static RoleBean bean = new RoleBean();
	private static RoleBean beanBlank = new RoleBean();
	
	private static final int ID=1;
	private static final int NEW_ID=2;
	
	private static final String NAME="password";
	private static final String NEW_NAME="password-x";

	@Before
	public void setUp() throws Exception {
		bean.setRole_id(ID);
		bean.setName(NAME);
	}

	@Test
	public void testRole_Id() {
		assertEquals(0,beanBlank.getRole_id());
		assertEquals(ID, bean.getRole_id());
		
		bean.setRole_id(NEW_ID);
		assertEquals(NEW_ID, bean.getRole_id());
	}

	@Test
	public void testSetName() {
		assertEquals("",beanBlank.getName());
		assertEquals(NAME, bean.getName());
		
		bean.setName(NEW_NAME);
		assertEquals(NEW_NAME, bean.getName());
	}

}
