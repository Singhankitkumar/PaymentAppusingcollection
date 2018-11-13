package com.capg.payment.test;

import static org.junit.Assert.assertEquals;
import junit.framework.Assert;

import org.junit.Test;

import com.capg.payment.exception.InvalidAmount;
import com.capg.payment.exception.InvalidPhoneNumber;
import com.capg.payment.exception.NameException;
import com.capg.payment.service.PaymentServiceImpl;

public class TestClass {
	
	
	@Test
	public void ValidateNameTrue() throws NameException{
		PaymentServiceImpl bs = new PaymentServiceImpl();
		assertEquals(true, bs.validateUserName("Ankit"));
	}
	
//	@Test (expected = InvalidNameFormat.class)
//	public void ValidateUserNameV1(){
//		BankServiceImpl bs = new BankServiceImpl();
		
//	}
	
	@Test
	public void ValidatePhonNumberTrue() throws InvalidPhoneNumber{
		PaymentServiceImpl bs = new PaymentServiceImpl();
		assertEquals(true, bs.validatePhoneNumber("9791575147"));
	}
	
	@Test
	public void ValidatePhoneNumber() throws InvalidPhoneNumber{
		PaymentServiceImpl bs = new PaymentServiceImpl();
		  
			    
		        String mobNo="ABCD91828288";
		 
		        boolean result= bs.validatePhoneNumber(mobNo);
		        Assert.assertEquals(false,result);
	}
	
	@Test 
	public void ValidateNameV2() throws NameException{
		PaymentServiceImpl bs = new PaymentServiceImpl();
		String name="pappu";
		 
        boolean result= bs.validateUserName(name);
        Assert.assertEquals(false,result);
	}
	
	@Test
	public void ValidateAmountTrue() throws InvalidAmount{
		PaymentServiceImpl bs = new PaymentServiceImpl();
		assertEquals(true, bs.validateAmount(100));
	}
	
	@Test (expected = InvalidAmount.class)
	public void ValidateAmount() throws InvalidAmount{
		PaymentServiceImpl bs = new PaymentServiceImpl();
		double amt= -400;
		 
        boolean result= bs.validateAmount(amt);
//        Assert.assertEquals(false,result);
		
	}


}
