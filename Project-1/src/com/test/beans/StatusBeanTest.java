package com.test.beans;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ers.beans.StatusBean;

public class StatusBeanTest {
	StatusBean bean = new StatusBean();
	StatusBean beanBlank = new StatusBean();

	private static final String NAME="fcastle";
	private static final String NEW_NAME="bbaker";
	
	private static final int ID=1;
	private static final int NEW_ID=2;
	
	
	@Before
	public void setUp() throws Exception {
		bean.setStatus_id(ID);
		bean.setName(NAME);
	}

	@Test
	public void testGetStatus_id() {
		assertEquals(0,beanBlank.getStatus_id());
		assertEquals(ID, bean.getStatus_id());
		
		bean.setStatus_id(NEW_ID);
		assertEquals(NEW_ID, bean.getStatus_id());}

	@Test
	public void testGetName() {
		assertEquals("",beanBlank.getName());
		assertEquals(NAME, bean.getName());
		
		bean.setName(NEW_NAME);
		assertEquals(NEW_NAME, bean.getName());
	}

}
