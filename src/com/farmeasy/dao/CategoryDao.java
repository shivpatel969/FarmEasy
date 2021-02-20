package com.farmeasy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.farmeasy.entities.Category;
import com.mysql.cj.Session;



public class CategoryDao {

		private Connection con;

		public CategoryDao(Connection con) {
			super();
			this.con = con;
		}
		
		//Create Save Category data method
		public boolean saveCategory(Category category) {
			boolean f = false;
			
			try {
				
				String query = "insert into category(categoryTitle, categoryDescription) values(?,?)";
				
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, category.getCategoryTitle());
				pstmt.setString(2, category.getCategoryDescription());
				//pstmt.setInt(3, category.getCategoryId());
				
				pstmt.executeUpdate();
				
				f = true;
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return f;
		}
		
		
		public ArrayList<Category> getCategories(){
			
			ArrayList<Category> list= new ArrayList<>();
			
			try {
				
				//Prepare sql
				String query = "select * from category";
				
				//Set prepared statement
				PreparedStatement pstmt = con.prepareStatement(query);
				
				//Execute sql
				ResultSet rs = pstmt.executeQuery();
				
				//Get data from the database
				while(rs.next()) { //upto when next row comes While loop run 
					/*
					 * Category category = new Category();
					 * category.setCategoryTitle(rs.getString("categoryTitle"));
					 * 
					 * list.add(category);
					 */
					
					//new code for cid
					int cId = rs.getInt("categoryId");
					String title = rs.getString("categoryTitle");
					String desc = rs.getString("categoryDescription");
					
					Category category = new Category(cId,title,desc);
					list.add(category);
				}
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return list;
		}
	
		
		public Category getCategoryById(int cId) {
			Category category = null;
			
			try {
				
				//create session
				String query = "select * from category where categoryId=?";
				
				//prepare statement
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setInt(1, cId);
				
				ResultSet rs = pstmt.executeQuery();
				
				//Get data from the database
				if(rs.next()) {
					//Category category = new Category();
					
					category= new Category();
					category.setCategoryId(rs.getInt("categoryId"));
					category.setCategoryTitle(rs.getString("categoryTitle"));
					category.setCategoryDescription(rs.getString("categoryDescription"));
					
				}
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return category;
		}
}
