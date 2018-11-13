package com.capg.payment.service;

import com.capg.payment.dto.Customer;
import com.capg.payment.exception.PaymentException;
import com.capg.payment.exception.InvalidAmount;
import com.capg.payment.exception.InvalidPhoneNumber;
import com.capg.payment.exception.NameException;

public interface IPaymentService {
	
	public Customer createAccount(Customer c) throws PaymentException;
	public double showBalance (String mobileno) throws InvalidPhoneNumber, PaymentException;
	public Customer fundTransfer (String sourceMobileNo,String targetMobileNo, double amount) throws PaymentException;
	public Customer depositAmount (String mobileNo, double amount ) throws InvalidPhoneNumber, InvalidAmount, PaymentException;
	public Customer withdrawAmount(String mobileNo, double amount) throws PaymentException;
	public boolean validateUserName(String name) throws NameException;
	public boolean validatePhoneNumber(String mobNo) throws InvalidPhoneNumber;
	public boolean validateTargetMobNo(String targetMobNo) throws InvalidPhoneNumber;
	public boolean validateAmount(double amt) throws InvalidAmount;
	public boolean validateAll(Customer c) throws PaymentException, NameException, InvalidAmount, InvalidPhoneNumber;

}
