package com.app.mapper.controller;

import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.mapper.order.exception.OrderNotFoundException;
import com.app.mapper.record.Order;

@RestController
@RequestMapping("/order")
public class OrderController {

	@GetMapping("/info")
	public ResponseEntity<Order> getOrderInfo(@RequestParam(required = true) int orderID )
	{
		var orders=Arrays.asList(new Order(1,"lux",100.0),
				new Order(4,"rin",300.0),
				new Order(3,"det",400.0),
				new Order(7,"vim",600.0),
				new Order(9,"rof",200.0)
				 );
				
		// Find the first matching order or throw the exception immediately
	    Order foundOrder = orders.stream()
	            .filter(order -> order.orderId() == orderID)
	            .findFirst()
	            .orElseThrow(() -> new OrderNotFoundException(+orderID," Order# was  not found"));

	    return ResponseEntity.ok(foundOrder);
	}
}
