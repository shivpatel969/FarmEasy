package com.farmeasy.entities;

public class Product {
	private int pId;
	private String pTitle;
	private String pDesc;
	private String pPhoto;
	private int pPrice;
	private int pDiscount;
	private int pQuantity;
	private int cId;
	
	private Category category;
	
	
	
	public Product(int pId, String pTitle, String pDesc, String pPhoto, int pPrice, int pDiscount, int pQuantity,
			int cId) {
		super();
		this.pId = pId;
		this.pTitle = pTitle;
		this.pDesc = pDesc;
		this.pPhoto = pPhoto;
		this.pPrice = pPrice;
		this.pDiscount = pDiscount;
		this.pQuantity = pQuantity;
		this.cId = cId;
	}
	
	public Product(String pTitle, String pDesc, String pPhoto, int pPrice, int pDiscount, int pQuantity, int cId) {
		super();
		this.pTitle = pTitle;
		this.pDesc = pDesc;
		this.pPhoto = pPhoto;
		this.pPrice = pPrice;
		this.pDiscount = pDiscount;
		this.pQuantity = pQuantity;
		this.cId = cId;
	}
	
	
	public Product(String pTitle, String pDesc, String pPhoto, int pPrice, int pDiscount, int pQuantity, int cId, Category category) {
		super();
		this.pTitle = pTitle;
		this.pDesc = pDesc;
		this.pPhoto = pPhoto;
		this.pPrice = pPrice;
		this.pDiscount = pDiscount;
		this.pQuantity = pQuantity;
		this.cId = cId;
		this.category = category;
	}
	
	public Product() {
		super();
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getpTitle() {
		return pTitle;
	}
	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}
	public String getpDesc() {
		return pDesc;
	}
	public void setpDesc(String pDesc) {
		this.pDesc = pDesc;
	}
	public String getpPhoto() {
		return pPhoto;
	}
	public void setpPhoto(String pPhoto) {
		this.pPhoto = pPhoto;
	}
	public int getpPrice() {
		return pPrice;
	}
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}
	public int getpDiscount() {
		return pDiscount;
	}
	public void setpDiscount(int pDiscount) {
		this.pDiscount = pDiscount;
	}
	public int getpQuantity() {
		return pQuantity;
	}
	public void setpQuantity(int pQuantity) {
		this.pQuantity = pQuantity;
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [pId=" + pId + ", pTitle=" + pTitle + ", pDesc=" + pDesc + ", pPhoto=" + pPhoto + ", pPrice="
				+ pPrice + ", pDiscount=" + pDiscount + ", pQuantity=" + pQuantity + ", cId=" + cId + "]";
	}

	//Calculate price after discount
	public int getPriceAfterApplyingDiscount() {
		int d = (int)((this.getpDiscount() / 100.0) * this.getpPrice());
		return (this.getpPrice() - d);
	}
}
