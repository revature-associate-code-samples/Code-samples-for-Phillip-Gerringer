package com.test.beans;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ers.beans.TypeBean;

public class TypeBeanTest {
	private TypeBean bean = new TypeBean();
	private TypeBean beanBlank = new TypeBean();
	
	private static final int ID=1;
	private static final int NEW_ID=2;
	
	private static final String NAME="password";
	private static final String NEW_NAME="password-x";
	
	@Before
	public void setUp() throws Exception {
		bean.setType_id(ID);
		bean.setName(NAME);
	}

	@Test
	public void testGetType_id() {
		assertEquals(0,beanBlank.getType_id());
		assertEquals(ID, bean.getType_id());
		
		bean.setType_id(NEW_ID);
		assertEquals(NEW_ID, bean.getType_id());
	}

	@Test
	public void testGetName() {
		assertEquals("",beanBlank.getName());
		assertEquals(NAME, bean.getName());
		
		bean.setName(NEW_NAME);
		assertEquals(NEW_NAME, bean.getName());
	}

}
