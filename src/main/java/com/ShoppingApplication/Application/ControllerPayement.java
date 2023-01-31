package com.ShoppingApplication.Application;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ShoppingApp.Orders;
import com.example.ShoppingApp.Products;

@Controller
public class ControllerPayement {

	
	@Autowired
	Products prod;
	
	@Autowired
	DAO_User data;
	
	@PostMapping("/{userId}/{orderId}/pay")
	public ResponseEntity payement(@PathVariable("userId") int userId,@PathVariable("orderId") int orderId, @RequestParam int amount) {
		
		Orders order=data.getOrderDetails(userId, orderId);
		
		Map<String,Object> map = new HashMap<>();
		
		if(order==null) {
			map.put("orderId", orderId);
			map.put("userId", userId);
	        map.put("transactionId","tran0#"+orderId);
	        map.put("status", "failed");
	        map.put("description", "Payment Failed due to invalid order id");
	        return new ResponseEntity(map,HttpStatus.BAD_REQUEST);
		}
		
		int i=orderId%10;
		
		if(order.getTransactionId().length()>0) {
			map.put("orderId", orderId);
			map.put("userId", userId);
	        map.put("transactionId",order.getTransactionId());                                    
	        map.put("status", order.getStatus());                                           
	        map.put("description", order.getDescription()+". This Transaction was already done");                    
	        return new ResponseEntity(map,HttpStatus.BAD_REQUEST);
		}
		else if(amount!=order.getAmount() ) {
			map.put("orderId", orderId);
			map.put("userId", userId);
	        map.put("transactionId","tran"+Products.transactionNum+"#"+orderId);   order.setTransactionId("tran"+Products.transactionNum+"#"+orderId);
	        Products.transactionNum++;                                   
	        map.put("status", "failed");                                           order.setStatus("failed");
	        map.put("description", "Payment Failed as amount is invalid");         order.setDescription("Payment Failed as amount is invalid");
	        
	        data.updateOrder(order);
	        return new ResponseEntity(map,HttpStatus.BAD_REQUEST);
		}
		else if(i<=9) {
			
			data.changeCouponStatus(userId, order.getCoupon());
			prod.setOrdered(prod.getOrdered()+order.getQuantity());
			prod.setAvailable(prod.getAvailable()-order.getQuantity());
			
			map.put("orderId", orderId);
			map.put("userId", userId);
	        map.put("transactionId","tran"+Products.transactionNum+"#"+orderId);    order.setTransactionId("tran"+Products.transactionNum+"#"+orderId);
	        Products.transactionNum++;                                              order.setDescription("Payement Success");
	        map.put("status", "successful");                                        order.setStatus("successfull");
	        data.updateOrder(order);
	        return new ResponseEntity(map,HttpStatus.OK);
		}
		else if(i==40 || i==50) {
			map.put("orderId", orderId);
			map.put("userId", userId);
	        map.put("transactionId","tran"+Products.transactionNum+"#"+orderId);   order.setTransactionId("tran"+Products.transactionNum+"#"+orderId);
	        Products.transactionNum++;                                   
	        map.put("status", "failed");                                           order.setStatus("failed");
	        map.put("description", "Payment Failed from bank");                    order.setDescription("Payment Failed from bank");
	        data.updateOrder(order);
	        return new ResponseEntity(map,HttpStatus.BAD_REQUEST);
		}
		else {
			map.put("orderId", orderId);
			map.put("userId", userId);
	        map.put("transactionId","tran"+Products.transactionNum+"#"+orderId);   order.setTransactionId("tran"+Products.transactionNum+"#"+orderId);
	        Products.transactionNum++;                                   
	        map.put("status", "failed");                                           order.setStatus("failed");
	        map.put("description", "No response from payment server");             order.setDescription("No response from payment server");
	        data.updateOrder(order);
	        return new ResponseEntity(map,HttpStatus.GATEWAY_TIMEOUT);
		}
		
	}
	
}
