package com.farmeasy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.farmeasy.entities.Checkout;

public class CheckoutDao {

	private Connection con;
	
	public CheckoutDao(Connection con) {
		super();
		this.con = con;
	}
	
	
	public boolean saveOrder(Checkout checkout) {
		boolean f = false;
		System.out.println(checkout);
		
		try {
			
			//Create a query
			String query = "insert into `order`(email,u_name,contact,address) values(?,?,?,?)";
			
			//Prepare statement
			PreparedStatement pstmt = this.con.prepareStatement(query);
			
			//Now store the values into ? mark
			pstmt.setString(1,checkout.getEmail());
			pstmt.setString(2,checkout.getName());
			pstmt.setLong(3,checkout.getMobile());
			pstmt.setString(4,checkout.getAddress());
			
			pstmt.executeUpdate();
			
			f = true;
			
		}catch (Exception e) {
			e.printStackTrace();
			f = false;
		}
		
		return f;
	}
	
}
