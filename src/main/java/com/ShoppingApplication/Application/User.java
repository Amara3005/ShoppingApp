package com.ShoppingApplication.Application;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name="user")
public class User {

	@Id
	private int id;
	private boolean off5;
	private boolean off10;
	
	@OneToMany
	List<Orders> list= new ArrayList<>();

	public User(int id) {
		this.id = id;                          
		off5=false;                          //  I have intialized the off5 and off10 to false,  
		off10=false;                         //   -> if off5 is false then it is not yet used,
	}   
}
