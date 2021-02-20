//This Dao class is used for counting user and product

package com.farmeasy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.farmeasy.helper.ConnectionProvider;

public class HelperDao {
	
	private static Connection connection;
	
	public HelperDao(Connection con) {
		super();
		this.connection = con;
	}

	//Create Function for counting user and product
	public static Map<String,Long> getCounts() {
		
		
		Map<String,Long> map = null;
		
		try {
			
			//Query to get the number of rows in a table
			String q1 = "select count(*) from user";
			//Query to get the number of rows in a table
			String q2 = "select count(*) from product";
			
			//Creating the Statement object
			PreparedStatement pstmt1 = connection.prepareStatement(q1);
			//Creating the Statement object
			PreparedStatement pstmt2 = connection.prepareStatement(q2);
			
			//Executing the query
			ResultSet rs1 = pstmt1.executeQuery(q1);
			//Executing the query
			ResultSet rs2 = pstmt2.executeQuery(q2);
			
			//Retrieving the result
			rs1.next();
			//Retrieving the result
			rs2.next();
			
			Long count1 =  rs1.getLong(1);
			
			Long count2 =  rs2.getLong(1);
			
			map = new HashMap<>();
			map.put("userCount", count1);
			map.put("productCount", count2);
			
			
			
		}catch (Exception e) {
			e.printStackTrace();		
		}
		return map;
		
	}
	
}
