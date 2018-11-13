package com.capg.payment.dao;

import java.util.HashMap;
import java.util.Map;

import com.capg.payment.dto.Customer;

public class PaymentDAOImpl implements IPaymentDAO{
	Map<String, Customer> map;

	public PaymentDAOImpl() {
		map = new HashMap<String, Customer>();
		map.put("9791575147", new Customer("Ankit", "9791575147", 500));
		map.put("9639639639", new Customer("Ramesh", "9639639639", 1000));
		map.put("9874563210", new Customer("Pankaj", "9874563210", 5000));
		map.put("7896541230", new Customer("Ranjan", "7896541230", 100));
	}

	@Override
	public Customer createAccount(Customer c) {
		// TODO Auto-generated method stub
		map.put(c.getMobileNumber(), c);
		return c;
	}

	@Override
	public Customer getAccount(String mobileno) {
		// TODO Auto-generated method stub
		Customer cShow = map.get(mobileno);
		return cShow;
	}

	@Override
	public boolean setAccount(String mobileNo, double amount) {
		// TODO Auto-generated method stub
		Customer cSet = map.get(mobileNo);
		cSet.setAmount(amount);
		System.out.println(cSet);
		map.put(mobileNo, cSet);
		return true;
	}


}



