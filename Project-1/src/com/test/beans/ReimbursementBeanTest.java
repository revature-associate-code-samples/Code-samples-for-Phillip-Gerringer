package com.test.beans;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import com.ers.beans.ReimbursementBean;
import com.ers.beans.StatusBean;
import com.ers.beans.TypeBean;
import com.ers.utils.ToolBelt;

public class ReimbursementBeanTest {
	private ReimbursementBean bean = new ReimbursementBean();
	private ReimbursementBean beanBlank = new ReimbursementBean();
	private static SimpleDateFormat sdf = new SimpleDateFormat(ToolBelt.DATE_FORMAT);
	
	private static final int ID = 1000;
	private static final int NEW_ID = 1500;
	
	private static final double AMOUNT = 6532;
	private static final double NEW_AMOUNT = 5489.61;
	
	private static final String DATE = "12-28-1967";
	private static final String NEW_DATE = "11-11-2011";
	
	private static final String DETAIL = "Houston";
	private static final String NEW_DETAIL = "we have a problem!";
	
	private static final String STATUS_NAME = "Fast";
	private static final String NEW_STATUS_NAME = "Slow";
	
	private static final String TYPE_NAME = "red";
	private static final String NEW_TYPE_NAME = "blue";
	
	@Before
	public void setUp() throws Exception {
		bean.setReimbursement_id(ID);
		bean.setAmount(AMOUNT);
		
		new Date((sdf.parse(DATE)).getTime());
		
		
		bean.setSubmit_date(new Date((sdf.parse(DATE)).getTime()));
		bean.setResolve_date(new Date((sdf.parse(DATE)).getTime()));
		bean.setDetail(DETAIL);
		
		StatusBean sBean = new StatusBean();
		sBean.setStatus_id(ID);
		sBean.setName(STATUS_NAME);
		bean.setStatus(sBean);
		
		TypeBean tBean = new TypeBean();
		tBean.setType_id(ID);
		tBean.setName(TYPE_NAME);
		bean.setType(tBean);
	}

	@Test
	public void testId() {
		assertEquals(0,beanBlank.getReimbursement_id());
		assertEquals(ID, bean.getReimbursement_id());
		
		bean.setReimbursement_id(NEW_ID);
		assertEquals(NEW_ID, bean.getReimbursement_id());
	}

	@Test
	public void testAmount() {
		assertEquals(0.0,beanBlank.getAmount(), .5);
		assertEquals(AMOUNT, bean.getAmount(), .5);
		
		bean.setAmount(NEW_AMOUNT);
		assertEquals(NEW_AMOUNT, bean.getAmount(), .5);
	}

	@Test
	public void testSubmit_date() {
		try {
			assertEquals(null,beanBlank.getSubmit_date());
			assertEquals(new Date((sdf.parse(DATE)).getTime()), sdf.parse(bean.getSubmit_date()));
			
			bean.setSubmit_date(new Date((sdf.parse(NEW_DATE)).getTime()));
			assertEquals(new Date((sdf.parse(NEW_DATE)).getTime()), sdf.parse(bean.getSubmit_date()));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testResolve_date() {
		try {
			assertEquals(null,beanBlank.getResolve_date());
			assertEquals(new Date((sdf.parse(DATE)).getTime()), sdf.parse(bean.getResolve_date()));
			
			bean.setResolve_date(new Date((sdf.parse(NEW_DATE)).getTime()));
			assertEquals(new Date((sdf.parse(NEW_DATE)).getTime()), sdf.parse(bean.getResolve_date()));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDetail() {
		assertEquals("",beanBlank.getDetail());
		assertEquals(DETAIL, bean.getDetail());
		
		bean.setDetail(NEW_DETAIL);
		assertEquals(NEW_DETAIL, bean.getDetail());
	}

	@Test
	public void testStatus() {
		assertEquals("",beanBlank.getStatus().getName());
		assertEquals(STATUS_NAME, bean.getStatus().getName());
		
		bean.getStatus().setName(NEW_STATUS_NAME);
		assertEquals(NEW_STATUS_NAME, bean.getStatus().getName());
	}

	@Test
	public void testType() {
		assertEquals("",beanBlank.getType().getName());
		assertEquals(TYPE_NAME, bean.getType().getName());
		
		bean.getType().setName(NEW_TYPE_NAME);
		assertEquals(NEW_TYPE_NAME, bean.getType().getName());
	}
}
