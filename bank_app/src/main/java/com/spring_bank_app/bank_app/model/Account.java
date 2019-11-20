package com.spring_bank_app.bank_app.model;

public class Account {
	public Account() {

	}

	private int acct_id;
	private int balance;
	private String acct_status;

	public int getAcct_id() {
		return acct_id;
	}

	public void setAcct_id(int acct_id) {
		this.acct_id = acct_id;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getAcct_status() {
		return acct_status;
	}

	public void setAcct_status(String acct_status) {
		this.acct_status = acct_status;
	}

}
