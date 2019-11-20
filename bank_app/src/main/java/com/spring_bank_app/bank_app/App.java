package com.spring_bank_app.bank_app;

import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.spring_bank_app.bank_app.dao.Account_DAO;
import com.spring_bank_app.bank_app.dao.Customer_DAO;
import com.spring_bank_app.bank_app.dao.Logger_DAO;
import com.spring_bank_app.bank_app.model.Customer;
/**
 * 
 * @author Ankit_Patel
 *
 */
public class App {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

		while (true) {
			System.out.println("------------Menu-------------\n");
			System.out.println("1. Login");
			System.out.println("2. Create new account:");
			System.out.println("3. Exit\n");
			System.out.println("------------------------------");
			System.out.println("Please Enter the desired option:");

			Scanner sc = new Scanner(System.in);
			int option = sc.nextInt();

			switch (option) {
			case 1:
				login(context);
				break;
			case 2:
				create_Acct_info(context);
				break;
			case 3:
				sc.close();
				System.exit(0);
			default:
				System.out.println("Invalid Option!!!");
				sc.close();
				break;
			}
		}
	}

	public static void login(ApplicationContext context) {
		System.out.println("\n------------Menu-------------\n");
		System.out.println("1. Enter Account ID");
		int acct_id = sc.nextInt();
		System.out.println("2. Enter Password:");
		String password = sc.next();

		Customer_DAO cust_dao = context.getBean("customer_DAO", Customer_DAO.class);
		int records = cust_dao.check_Credentials(acct_id, password);
		if (records == 1) {
			login_Page(context, acct_id);
		} else {
			System.out.println("Invalid Credentials!!!");
		}
	}

	public static void login_Page(ApplicationContext context, int acct_id) {
		goto_main: while (true) {
			System.out.println("\n------------Menu-------------\n");
			System.out.println("1. Check Account Balance:");
			System.out.println("2. Deposit Money:");
			System.out.println("3. Withdraw Money:");
			System.out.println("4. Transfer Balance to Another Account:");
			System.out.println("5. Delete Account:");
			System.out.println("6. Check Account Info");
			System.out.println("7. Check Profile Info");
			System.out.println("8. Show Transaction Info:");
			System.out.println("9. Logout\n");
			System.out.println("------------------------------");
			System.out.println("Please Enter the desired option:");

			int option = sc.nextInt();

			switch (option) {
			case 1:
				acct_bal(context, acct_id);
				break;
			case 2:
				deposit(context, acct_id);
				break;
			case 3:
				withdraw(context, acct_id);
				break;
			case 4:
				transfer(context, acct_id);
				break;
			case 5:
				del_Acct(context, acct_id);
				break goto_main;
			case 6:
				Account_DAO acct_dao = context.getBean("account_DAO", Account_DAO.class);
				acct_dao.get_Acct_Info(acct_id);
				break;
			case 7:
				get_Cust_Info(context, acct_id);
				break;
			case 8:
				Logger_DAO log = context.getBean("logger_DAO", Logger_DAO.class);
				log.show_Transac(acct_id);
				break;
			case 9:
				break goto_main;
			default:
				sc.close();
				break;
			}
		}
	}

	public static void del_Acct(ApplicationContext context, int acct_id) {
		System.out.println("Enter Password to Confirm Deletion:");
		String password = sc.next();
		Customer_DAO cust_dao = context.getBean("customer_DAO", Customer_DAO.class);
		int records = cust_dao.check_Credentials(acct_id, password);
		Account_DAO acct_dao = context.getBean("account_DAO", Account_DAO.class);

		if (records == 1) {
			acct_dao.del_Acct(password, acct_id);
		} else {
			System.out.println("Invalid Credentials!!!");
		}
	}

	public static void transfer(ApplicationContext context, int acct_id) {
		System.out.println("Enter Amount to Transfer: ");
		int amount = sc.nextInt();
		System.out.println("Enter Receiver Account ID:");
		int dest_acct_id = sc.nextInt();
		Account_DAO acct_dao = context.getBean("account_DAO", Account_DAO.class);
		acct_dao.transfer(amount, acct_id, dest_acct_id);
	}

	public static void withdraw(ApplicationContext context, int acct_id) {
		System.out.println("Enter Amount to Withdraw: ");
		int amount = sc.nextInt();
		Account_DAO acct_dao = context.getBean("account_DAO", Account_DAO.class);
		acct_dao.withdraw(amount, acct_id);
	}

	public static void deposit(ApplicationContext context, int acct_id) {
		System.out.println("Enter Amount to Deposit: ");
		int amount = sc.nextInt();
		Account_DAO acct_dao = context.getBean("account_DAO", Account_DAO.class);
		acct_dao.deposit(amount, acct_id);
	}

	public static void acct_bal(ApplicationContext context, int acct_id) {
		Account_DAO acct_dao = context.getBean("account_DAO", Account_DAO.class);
		int balance = acct_dao.check_bal(acct_id);
		System.out.println("The Balance for Account ID: " + acct_id + " is: " + balance);
	}

	public static void create_Acct_info(ApplicationContext context) {
		// check if account is already there
		System.out.println("Enter an Account ID:");
		int acct_id = sc.nextInt();
		System.out.println("Enter your name:");
		String cust_name = sc.next();
		System.out.println("Enter City of Residence:");
		String address = sc.next();
		System.out.println("Enter State of Residence:");
		String state = sc.next();
		System.out.println("Enter Country of Residence:");
		String country = sc.next();
		System.out.println("Enter Phone#:");
		int phone_no = sc.nextInt();
		System.out.println("Enter your new Password:");
		String password = sc.next();

		Customer cust_info = context.getBean("cust_Info", Customer.class);
		cust_info.setAcct_id(acct_id);
		cust_info.setCust_name(cust_name);
		cust_info.setCity(address);
		cust_info.setState(state);
		cust_info.setCountry(country);
		cust_info.setPhone_no(phone_no);
		cust_info.setPassword(password);

		Customer_DAO cust_dao = context.getBean("customer_DAO", Customer_DAO.class);
		cust_dao.create_Acct(cust_info);
	}

	public static void get_Cust_Info(ApplicationContext context, int acct_id) {
		Customer_DAO cust_dao = context.getBean("customer_DAO", Customer_DAO.class);
		cust_dao.get_Cust_Info(acct_id);
	}

}
