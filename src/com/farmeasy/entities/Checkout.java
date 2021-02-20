package com.farmeasy.entities;

public class Checkout {

	private int orderId;
	private String email;
	private String name;
	private Long mobile;
	private String address;

	public Checkout() {
		super();
	}
	
	public Checkout(String email, String name, Long mobile, String address) {
		super();
		this.email = email;
		this.name = name;
		this.mobile = mobile;
		this.address = address;
	}
	
	public Checkout(int orderId, String email, String name, Long mobile, String address) {
		super();
		this.orderId = orderId;
		this.email = email;
		this.name = name;
		this.mobile = mobile;
		this.address = address;
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Checkout [orderId=" + orderId + ", email=" + email + ", name=" + name + ", mobile=" + mobile
				+ ", address=" + address + "]";
	}
	
	
	
}
