package com.capg.payment.dao;

import com.capg.payment.dto.Customer;

import com.capg.payment.exception.PaymentException;

public interface IPaymentDAO {

	public Customer createAccount(Customer c) throws PaymentException;
	public Customer getAccount(String mobileno);
	public boolean setAccount(String mobileNo, double amount);
}
