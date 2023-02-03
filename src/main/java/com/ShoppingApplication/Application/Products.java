package com.ShoppingApplication.Application;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	
	public  int getUniqueId() {   
	        Date dNow = new Date();
	        SimpleDateFormat ft = new SimpleDateFormat("ddHHmmss");
	        String datetime = ft.format(dNow);
	        return (int) Integer.valueOf(datetime);
	}
	
	
	public int getOrdered() {
		return ordered;
	}

	public int getPrice() {
		return price;
	}

	public int getAvailable() {
		return available;
	}

	public void setOrdered(int ordered) {
		this.ordered = ordered;
	}

	public void setAvailable(int available) {
		this.available = available;
	}
	
}
