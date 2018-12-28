package com.test.beans;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ EmployeeBeanTest.class, LoginBeanTest.class, ReimbursementBeanTest.class, RoleBeanTest.class,
		StatusBeanTest.class, TypeBeanTest.class })
public class AllBeanTests {

}
