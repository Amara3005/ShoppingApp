package com.ShoppingApplication.Application;

import javax.persistence.Id;

import jakarta.persistence.Entity;

@Entity(name="orders")
public class Orders {
	
	@Id
	private int orderId;
	private String transactionId="";
	private String status;
	private String description="Transaction yet to complete";
	private int amount;
	private String Coupon;
	private String date;
	private int quantity;
	

}
