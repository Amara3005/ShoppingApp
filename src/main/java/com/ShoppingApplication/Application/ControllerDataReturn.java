package com.ShoppingApplication.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.ShoppingApp.Orders;
import com.example.ShoppingApp.User;


@Controller
public class ControllerDataReturn {

	
	@Autowired
	DAO_User data;
	
	@GetMapping("/{userId}/orders")
	public ResponseEntity allOrders(@PathVariable int userId) {
		
		User user=data.getUser(userId);
		
		if(user==null) {
			Map<String,Object> map = new HashMap<>();
			 map.put("userId",userId);
			 map.put("description", "Invalid User Id");
			return new ResponseEntity(map,HttpStatus.NOT_FOUND);
		}
		else {
			List<Orders> list=user.getList();
			List<Map<String,Object>> lisMap = new ArrayList<>();
			
			for(int i=0;i<list.size();i++) {
				Orders order=list.get(i);
				Map<String,Object> map = new HashMap<>();
				 map.put("orderId",order.getOrderId());
				 map.put("amount", order.getAmount());
				 map.put("date", order.getDate());
				 map.put("coupon", order.getCoupon());
				 lisMap.add(map);
			}
			return new ResponseEntity(lisMap,HttpStatus.OK);
		}
		
	}
}
