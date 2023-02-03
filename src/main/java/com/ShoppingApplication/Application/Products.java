package com.ShoppingApplication.Application;

import org.springframework.stereotype.Service;

@Service
public class Products {

	private int ordered;
	private int price;
	private int available;
	
	public static int transactionNum=100;           // Static variable used to count the transaction number (used in transaction id);
	
	public Products() {
		ordered=0;
		price=100;
		available=100;
	}
	
}
