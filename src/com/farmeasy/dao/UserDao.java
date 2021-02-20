package com.farmeasy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.farmeasy.entities.User;

public class UserDao {
	
	private Connection con;

	public UserDao(Connection con) {
		super();
		this.con = con;
	}
	
	//Save user data
	public boolean saveUser(User user) {
		
		boolean f = false;
		
		try {
			String query = "insert into user(userName,userEmail,userPassword,userMobile,userPic,userType) values(?,?,?,?,?,?)";
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserEmail());
			pstmt.setString(3, user.getUserPassword());
			pstmt.setString(4, user.getUserMobile());
			pstmt.setString(5, user.getUserPic());
			pstmt.setString(6, user.getUserType());
			
			pstmt.executeUpdate();
			f = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
		
	}
	
	//Get user by email and password
	public User getUserByEmailAndPassword(String email, String password)
	{
		User user = null;
		
		//fire a query
		try {
			
			String query = "select * from user where userEmail=? and userPassword=?";
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setUserName(rs.getString("userName"));
				user.setUserEmail(rs.getString("userEmail"));
				user.setUserPassword(rs.getString("userPassword"));
				user.setUserMobile(rs.getString("userMobile"));
				user.setUserPic(rs.getString("userPic"));
				user.setUserType(rs.getString("userType"));
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return user;
	
	}
	

}
