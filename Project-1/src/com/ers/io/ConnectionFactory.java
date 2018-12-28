/**
 * 
 */
package com.ers.io;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Factory to serve only one connection to the db
 * 
 * @author Phil
 *
 */
public class ConnectionFactory {
	private static ConnectionFactory connFactory = null;
	
	// so we can't create an instance
	private ConnectionFactory() {

	}

	public static synchronized ConnectionFactory getInstance() {
		if (connFactory == null) {
			connFactory = new ConnectionFactory();
		}
		return connFactory;
	}
	
	/**
	 * returns the one connection to the db 
	 * @return
	 */
	public Connection getConnection() {
		Connection conn = null;
		Properties prop = new Properties();
		String path="C:\\Users\\Phil\\my_git_repos\\Revature\\Project1\\src\\db.properties";
		
		try {
			prop.load(new FileReader(path));
			
			// the following line of code uses reflection and the 
			// .properties file in order to instantiate our driver
			// class listed in the file
			Class.forName(prop.getProperty("driver"));
			
			conn = DriverManager.getConnection(prop.getProperty("url"), 
												prop.getProperty("usr"), 
												prop.getProperty("pwd"));
			
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
				
		return conn;
	}
}
