package com.app.mapper.order.exception;

public class OrderNotFoundException extends RuntimeException {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int orderID;

public OrderNotFoundException(int orderID,String messgae) {
	super(messgae);
	this.orderID=orderID;
}

public int getOrderID() {
	return orderID;
}

}
