package com.capg.payment.exception;

public class InvalidAmount extends Exception {
	
	public InvalidAmount() {
		super("Invalid amount(Amount shouldn't be negative or zero. Maximum amount can be 9999999.99");
}

}
