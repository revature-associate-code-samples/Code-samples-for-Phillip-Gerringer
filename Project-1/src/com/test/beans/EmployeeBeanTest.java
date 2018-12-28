package com.test.beans;

import static org.junit.Assert.*;
import com.ers.beans.*;

import org.junit.Before;
import org.junit.Test;

public class EmployeeBeanTest {
	EmployeeBean bean = new EmployeeBean();
	EmployeeBean beanBlank = new EmployeeBean();
	
	private static final String ROLE_NAME="jack of all trades...";
	private static final String NEW_ROLE_NAME="master of none..."; 
	
	@Before
	public void setUp() throws Exception {
		bean.setEmployee_id(1000);
		bean.setFirst_name("Frank");
		bean.setLast_name("Castle");
		bean.setUser_name("fcastle");
		bean.setPassword("password");
		bean.setEmail("123@karma.com");
		
		RoleBean rBean = new RoleBean();
		rBean.setRole_id(1);
		rBean.setName(ROLE_NAME);
		bean.setRole(rBean);
	}

	@Test
	public void testEmployee_id() {
		assertEquals(0,beanBlank.getEmployee_id());
		assertEquals(1000, bean.getEmployee_id());
		
		bean.setEmployee_id(1500);
		assertEquals(1500, bean.getEmployee_id());
	}

	@Test
	public void testFirst_name() {
		assertEquals("", beanBlank.getFirst_name());
		assertEquals("Frank", bean.getFirst_name());
		
		bean.setFirst_name("Bob");
		assertEquals("Bob", bean.getFirst_name());
	}

	@Test
	public void testLast_name() {
		assertEquals("", beanBlank.getLast_name());
		assertEquals("Castle", bean.getLast_name());
		
		bean.setLast_name("Baker");
		assertEquals("Baker", bean.getLast_name());
	}

	@Test
	public void testUser_name() {
		assertEquals("", beanBlank.getUser_name());
		assertEquals("fcastle", bean.getUser_name());
		
		bean.setUser_name("bbaker");
		assertEquals("bbaker", bean.getUser_name());
	}

	@Test
	public void testPassword() {
		assertEquals("", beanBlank.getPassword());
		assertEquals("password", bean.getPassword());
		
		bean.setPassword("br549");
		assertEquals("br549", bean.getPassword());
	}

	@Test
	public void testEmail() {
		assertEquals("", beanBlank.getEmail());
		assertEquals("123@karma.com", bean.getEmail());
		
		bean.setEmail("123@day.com");
		assertEquals("123@day.com", bean.getEmail());
	}

	@Test
	public void testRole_id() {
		assertEquals(0, beanBlank.getRole().getRole_id());
		assertEquals(1, bean.getRole().getRole_id());
		
		bean.getRole().setRole_id(2);
		assertEquals(2, bean.getRole().getRole_id());
	}

	@Test
	public void testRole_name() {
		assertEquals("", beanBlank.getRole().getName());
		assertEquals(ROLE_NAME, bean.getRole().getName());
		
		bean.getRole().setName(NEW_ROLE_NAME);
		assertEquals(NEW_ROLE_NAME, bean.getRole().getName());
	}
}
