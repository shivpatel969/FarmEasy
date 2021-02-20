package com.farmeasy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.farmeasy.entities.Category;
import com.farmeasy.entities.Product;


public class ProductDao {

		private Connection con;

		public ProductDao(Connection con) {
			super();
			this.con = con;
		}
		
		public boolean saveProduct(Product product) {
			boolean f = false;		
				
			try {
				
				//Create a query
				String query = "insert into product(pTitle,pDesc,pPhoto,pPrice,pDiscount,pQuantity,cId) values(?,?,?,?,?,?,?)";
				
				//Prepare statement
				PreparedStatement pstmt = con.prepareStatement(query);
				
				//Now store the values into ? mark
				pstmt.setString(1, product.getpTitle());
				pstmt.setString(2, product.getpDesc());
				pstmt.setString(3, product.getpPhoto());
				pstmt.setInt(4, product.getpPrice());
				pstmt.setInt(5, product.getpDiscount());
				pstmt.setInt(6, product.getpQuantity());
				pstmt.setInt(7, product.getcId());
				
				pstmt.executeUpdate();
	
				f = true;
				
			}catch(Exception e) {
				e.printStackTrace();
				f = false;
			}
			
			return f;
			
		}
		
		//Get all products
		public List<Product> getAllProducts(){
			
			List<Product> list = new ArrayList<>();
			
			try {
				
				//Prepare sql
				String query = "select * from product";
				
				//Set prepare statement
				PreparedStatement pstmt = con.prepareStatement(query);
				
				//Execute sql
				ResultSet rs = pstmt.executeQuery();
				
				//Get data from the database
				while(rs.next()) {
					Product product = new Product();
					product.setpId(rs.getInt("pId"));
					product.setpTitle(rs.getString("pTitle"));
					product.setpDesc(rs.getString("pDesc"));
					product.setpPhoto(rs.getString("pPhoto"));
					product.setpPrice(rs.getInt("pPrice"));
					product.setpDiscount(rs.getInt("pDiscount"));
					product.setpQuantity(rs.getInt("pQuantity"));
					product.setcId(rs.getInt("cId"));
					
					
					list.add(product);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return list;
			
		}
		
		
		public List<Product> getAllProductsById(int cId){
			List<Product> list = new ArrayList<>(); 
			
			try {
				String query = "select * from product where cId=?";
				
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setInt(1, cId);
				
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
						int pId = rs.getInt("pId");			
						String pTitle = rs.getString("pTitle");
						String pDesc = rs.getString("pDesc");
						String pPhoto = rs.getString("pPhoto");
						int pPrice = rs.getInt("pPrice");
						int pDiscount = rs.getInt("pDiscount");
						int pQuantity = rs.getInt("pQuantity");
						int catId = rs.getInt("cId");
						
						Product product = new Product(pId, pTitle, pDesc, pPhoto, pPrice, pDiscount, pQuantity, catId);
						list.add(product);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return list;
			
		}
		
}
