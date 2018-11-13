package com.capg.payment.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capg.payment.dao.IPaymentDAO;
import com.capg.payment.dao.PaymentDAOImpl;
import com.capg.payment.dto.Customer;
import com.capg.payment.exception.PaymentException;
import com.capg.payment.exception.InvalidAmount;
import com.capg.payment.exception.InvalidPhoneNumber;
import com.capg.payment.exception.NameException;

public class PaymentServiceImpl implements IPaymentService{
	

	IPaymentDAO dao;

	public PaymentServiceImpl() {
		dao = new PaymentDAOImpl();
	}

	@Override
	public boolean validateAll(Customer c) throws PaymentException, NameException, InvalidAmount, InvalidPhoneNumber {

		boolean b = false;
		
		if (validateUserName(c.getCustomerName()) == true
				&& validatePhoneNumber(c.getMobileNumber()) == true
				&& validateAmount(c.getAmount()) == true)
			b = true;
		if (!b)
			throw new PaymentException("Invalid details");
		return b;
	}

	@Override
	public boolean validateUserName(String name) throws NameException {

		if(name==null) 
			throw new NameException("Null value");
		Pattern p = Pattern.compile("[A-Z]{1}[a-z]{2,20}");
		Matcher mat = p.matcher(name);
		if(!mat.matches())
			System.out.println("Invalid name(First letter must be caps and rest in small letters only)");
		return mat.matches();
	}

	@Override
	public boolean validatePhoneNumber(String mobileNo) throws InvalidPhoneNumber {
		if(mobileNo==null) 
			throw new InvalidPhoneNumber("Null value");
		Pattern pat = Pattern.compile("[7-9][0-9]{9}");
		Matcher mat = pat.matcher(mobileNo);
		if(!mat.matches())
			System.out.println("Invalid mobile number(Please provide a ten digit mobile number starting from 7,8,or 9)");
		return mat.matches();
	}

	@Override
	public boolean validateAmount(double amt) throws InvalidAmount {

		Pattern pat = Pattern.compile("[1-9]{1}[0-9.]{0,9}");
		Matcher mat = pat.matcher(String.valueOf(amt));
		boolean b = mat.matches();

		if (!b)
			throw new InvalidAmount();

		return b;
	}

	@Override
	public boolean validateTargetMobNo(String targetMobNo) throws InvalidPhoneNumber {
		if(targetMobNo==null) 
			throw new InvalidPhoneNumber("Null value");
		Pattern pat = Pattern.compile("[6-9]{1}[0-9]{9}");
		Matcher mat = pat.matcher(targetMobNo);
		boolean b = mat.matches();

		if (!b)
			throw new InvalidPhoneNumber("target Mobile Invalid");

		return b;
	}

	@Override
	public Customer createAccount(Customer c) throws PaymentException {
		// TODO Auto-generated method stub
		Customer create = dao.createAccount(c);
		
		if(create == null)
			throw new PaymentException("Mobile number doesn't exist");
		return create;
	}

	@Override
	public double showBalance(String mobileno) throws PaymentException,
	PaymentException {
		// TODO Auto-generated method stub
		String tranType = "Check balance";
		Customer bal = dao.getAccount(mobileno);
		if (bal == null)
			throw new PaymentException("Mobile number doesn't exist");
		return bal.getAmount();
	}

	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo,
			double amount) throws PaymentException {
		// TODO Auto-generated method stub
		String tranType = "Transfer";
		Customer sbal = dao.getAccount(sourceMobileNo);
		Customer tbal = dao.getAccount(targetMobileNo);
		if (sbal == null)
			throw new PaymentException("Mobile number doesn't exist");
		if (tbal == null)
			throw new PaymentException("Mobile number doesn't exist");
		double tmp = (sbal.getAmount() - amount);
		if (tmp >= 500) {
			dao.setAccount(targetMobileNo, tbal.getAmount() + amount);
			dao.setAccount(sourceMobileNo, sbal.getAmount() - amount);
		} else {
			throw new PaymentException(
					"Invalid amount.....Amount cannot be negative, zero, or Your account balance cannot be less than 500 after tranferring.");
		}
		return dao.getAccount(sourceMobileNo);
	}

	@Override
	public Customer depositAmount(String mobileNo, double amount)
			throws InvalidPhoneNumber, InvalidAmount, PaymentException {
		// TODO Auto-generated method stub
		String tranType = "Deposit";
		Customer cDep1 = dao.getAccount(mobileNo);
		if (cDep1 == null)
			throw new PaymentException("Mobile number doesn't exist");
		boolean c = dao.setAccount(mobileNo, cDep1.getAmount() + amount);
//		System.out.println(c);
		Customer cDep = dao.getAccount(mobileNo);
		if (!c)
			throw new PaymentException("Unable to deposit");
		else
			return cDep;
	}

	@Override
	public Customer withdrawAmount(String mobileNo, double amount)
			throws PaymentException {
		// TODO Auto-generated method stub
		String tranType = "Withdraw";
		boolean c = false;
		Customer cDep1 = dao.getAccount(mobileNo);
		if (cDep1 == null)
			throw new PaymentException("Mobile number doesn't exist");
		if ((cDep1.getAmount() - amount) >= 0)
			c = dao.setAccount(mobileNo, cDep1.getAmount() - amount);
		Customer cDep = dao.getAccount(mobileNo);
		if (!c)
			throw new PaymentException("Unable to withdraw");
		else
			return cDep;
	}

}



