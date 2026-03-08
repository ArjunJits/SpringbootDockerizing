package com.app.mapper.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.mapper.order.exception.OrderNotFoundException;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<String> odernotFoundResponse(OrderNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order " +ex.getOrderID() +"not found please check");
	}
	
}
