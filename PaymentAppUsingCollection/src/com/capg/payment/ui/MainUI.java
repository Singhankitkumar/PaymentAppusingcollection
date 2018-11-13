package com.capg.payment.ui;

import java.util.Scanner;

import com.capg.payment.dto.Customer;
import com.capg.payment.exception.PaymentException;
import com.capg.payment.exception.InvalidAmount;
import com.capg.payment.exception.InvalidPhoneNumber;
import com.capg.payment.exception.NameException;
import com.capg.payment.service.IPaymentService;
import com.capg.payment.service.PaymentServiceImpl;

public class MainUI {
	
	public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	IPaymentService service = new PaymentServiceImpl();

	int option;
	do {

		System.out.println("1. Create new Customer Account");
		System.out.println("2. Show Existing Customer's balance");
		System.out.println("3. Fund Transfer");
		System.out.println("4. Deposit the Amount into your Account...");
		System.out.println("5. Withdraw the Amount from your Account...");
		System.out.println("6. Exit Application");
		option = sc.nextInt();

		switch (option) {
		case 1:                                                                               // Create new Customer Account

			String name;
			String mobNo;
			double amt;

			do {
				System.out.println("Enter Customer Name: ");
				name = sc.next();
				try {
					if (service.validateUserName(name))
						break;
				} catch (NameException e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}
			} while (true);
			do {
				System.out.println("Enter Mobile Number: ");
				mobNo = sc.next();
				try {
					if (service.validatePhoneNumber(mobNo))
						break;
				} catch (InvalidPhoneNumber e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}
			} while (true);
			do {
				System.out.println("Enter Initial Amount: ");
				amt = sc.nextDouble();
				try {
					if (service.validateAmount(amt))
						break;
				} catch (InvalidAmount e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}
			} while (true);

			Customer c = new Customer(name, mobNo, amt);
			Customer c1 = null;
			try {
				if (service.validateAll(c))
					c1 = service.createAccount(c);
				System.out.println("Successfully Created new Account for "
						+ c1.getCustomerName() + " With "
						+ "Mobile Number " + c1.getMobileNumber());
				System.out.println("\n****************************************************************************************");
			} catch (PaymentException | NameException | InvalidAmount
					| InvalidPhoneNumber e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
			break;

		case 2:                                                                                      //Show Existing Customer's balance

			System.out.println("Enter an existing mobile number: ");
			String mobNoShow = sc.next();

			double bal = 0;
			try {
				if (service.validatePhoneNumber(mobNoShow))
					bal = service.showBalance(mobNoShow);
				System.out.println("Available balance for the Mobile Number "
								+ mobNoShow + " is " + bal);
				System.out.println("\n****************************************************************************************");
			} catch (InvalidPhoneNumber | PaymentException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}

			break;

		case 3:                                                                                         // Fund Transfer

			String sourceMobileNo;
			String targetMobileNo;
			double amount;
			Customer funds = null;

			do {
				System.out.println("Enter your mobile number: ");
				sourceMobileNo = sc.next();
				try {
					if (service.validatePhoneNumber(sourceMobileNo))
						break;
				} catch (InvalidPhoneNumber e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}
			} while (true);
			do {
				System.out.println("Enter recipient's mobile number: ");
				targetMobileNo = sc.next();
				try {
					if (service.validatePhoneNumber(targetMobileNo))
						break;
				} catch (InvalidPhoneNumber e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}
			} while (true);
			do {
				System.out
						.println("Enter the amount that to be transfered: ");
				amount = sc.nextDouble();
				try {
					if (service.validateAmount(amount))
						break;
				} catch (InvalidAmount e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}
			} while (true);

			try {
				if (service.validatePhoneNumber(sourceMobileNo)
						&& service.validateTargetMobNo(targetMobileNo)
						&& service.validateAmount(amount))
					funds = service.fundTransfer(sourceMobileNo,
							targetMobileNo, amount);

				System.out.println("Successfully transfered Rs." + amount
						+ " to " + targetMobileNo + ".\n"
						+ "Available balance is Rs. " + funds.getAmount());
				System.out.println("\n****************************************************************************************");

			} catch (InvalidPhoneNumber | InvalidAmount | PaymentException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}

			break;

		case 4:                                                                                           //Deposit the Amount into your Account...

			String mobNoDep;
			double amtDep;
			Customer cDep = null;

			do {
				System.out.println("Enter your mobile number: ");
				mobNoDep = sc.next();
				try {
					if (service.validatePhoneNumber(mobNoDep))
						break;
				} catch (InvalidPhoneNumber e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}
			} while (true);
			do {
				System.out.println("Enter amount that to be deposited: ");
				amtDep = sc.nextDouble();
				try {
					if (service.validateAmount(amtDep))
						break;
				} catch (InvalidAmount e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}
			} while (true);

			try {
				if (service.validatePhoneNumber(mobNoDep)
						&& service.validateAmount(amtDep))
					cDep = service.depositAmount(mobNoDep, amtDep);
				System.out.println("Your current balance is Rs."
						+ cDep.getAmount());
				System.out.println("\n****************************************************************************************");
			} catch (InvalidAmount | InvalidPhoneNumber | PaymentException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
			break;

		case 5:                                                                                                 // Withdraw the Amount from your Accountt

			String mobNoWiDraw;
			double amtWiDraw;

			do {
				System.out.println("Enter your mobile number: ");
				mobNoWiDraw = sc.next();
				try {
					if (service.validatePhoneNumber(mobNoWiDraw))
						break;
				} catch (InvalidPhoneNumber e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}
			} while (true);
			do {
				System.out.println("Enter amount that to be withdrawn: ");
				amtWiDraw = sc.nextDouble();
				try {
					if (service.validateAmount(amtWiDraw))
						break;
				} catch (InvalidAmount e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} while (true);

			Customer cWD = null;
			try {
				if (service.validatePhoneNumber(mobNoWiDraw)
						&& service.validateAmount(amtWiDraw))
					cWD = service.withdrawAmount(mobNoWiDraw, amtWiDraw);
				System.out.println("Your current balance is Rs. "
						+ cWD.getAmount());
				System.out.println("\n****************************************************************************************");
			} catch (InvalidAmount | PaymentException | InvalidPhoneNumber e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
			break;
		case 6:                                                                                                     //Exit Application
			System.out.println("You have exited the application.....");
			System.out.println("\n****************************************************************************************");
			break;
			
		default:
			System.out.println("Invalid entry. Please select from above options.\n");
			System.out.println("\n****************************************************************************************");
		}

	} while (option != 6);

	sc.close();
}

}



