package com.farmeasy.entities;

public class User {
	
	//Create all the var of column entities of the table
	private int userId;
	private String userName;
	private String userEmail;
	private String userPassword;
	private String userMobile;
	private String userPic;
	private String userType;
	
	//Now create a constructor
	public User(int userId, String userName, String userEmail, String userPassword, String userMobile, String userPic, String userType) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userMobile = userMobile;
		this.userPic = userPic;
		this.userType = userType;
	}


	public User(String userName, String userEmail, String userPassword, String userMobile, String userPic, String userType) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userMobile = userMobile;
		this.userPic = userPic;
		this.userType = userType;
	}


	public User() {
		super();
	}

	
	//Now create getter and setter
	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public String getUserMobile() {
		return userMobile;
	}


	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}


	public String getUserPic() {
		return userPic;
	}


	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}
	
	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}


	//Generate toString
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", userPassword="
				+ userPassword + ", userMobile=" + userMobile + ", userPic=" + userPic + "]";
	}
	
	
	
	
}
