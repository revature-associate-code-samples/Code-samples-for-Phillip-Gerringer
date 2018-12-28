package com.fanatics.util;

import org.apache.log4j.Logger;

public class App {

	public static void main(String[] args) {
		Logger log = Log.getInstance(App.class);
		
		log.debug(Tool.getCurrentDate());
		log.debug(Tool.getTime(Tool.tenYears()));
		
	}

}
