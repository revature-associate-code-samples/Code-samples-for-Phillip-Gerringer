package com.banking.services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ToolsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		// exists
		assertEquals(true, Tools.exists("Some valid text"));
		assertEquals(false, Tools.exists(""));
		assertEquals(false, Tools.exists(null));
		
		// format 
		assertEquals("$125.00", Tools.format(125));

		// padleft
		assertEquals(" Fred", Tools.padLeft("Fred", 5));
		assertEquals("Fred", Tools.padLeft("Fred", 3));
		
		// padRight
		assertEquals("Fred ", Tools.padRight("Fred", 5));
		assertEquals("Fred", Tools.padRight("Fred", 3));
		
		// isDouble
		assertEquals(true, Tools.isDouble("100.00"));
		assertEquals(false, Tools.isDouble("hat"));
		
		// center
		assertEquals("  o  ", Tools.center("o", 5));
		assertEquals("  o   ", Tools.center("o", 6));
		assertEquals("bob", Tools.center("bob", 2));
		
		// is odd
		assertEquals(true, Tools.isOdd(5));
		assertEquals(false, Tools.isOdd(4));
	}

}
