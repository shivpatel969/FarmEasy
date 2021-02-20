package com.farmeasy.helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
	public static Connection con;
	
	public static Connection getConnection() {
		
		if(con == null) {
			try{
				
				//First load the driver class
				Class.forName("com.mysql.jdbc.Driver");
				
				//Now create a connection
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmeasy","root","root");
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return con;
	}
}
