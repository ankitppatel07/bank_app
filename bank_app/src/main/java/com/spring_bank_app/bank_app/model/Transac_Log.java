package com.spring_bank_app.bank_app.model;

import org.springframework.stereotype.Component;

@Component
public class Transac_Log {
	int acct_id;
	String transac_type;
	String transac_status;
	int init_bal;
	int final_bal;

	public int getAcct_id() {
		return acct_id;
	}

	public void setAcct_id(int acct_id) {
		this.acct_id = acct_id;
	}

	public String getTransac_type() {
		return transac_type;
	}

	public void setTransac_type(String transac_type) {
		this.transac_type = transac_type;
	}

	public String getTransac_status() {
		return transac_status;
	}

	public void setTransac_status(String transac_status) {
		this.transac_status = transac_status;
	}

	public int getInit_bal() {
		return init_bal;
	}

	public void setInit_bal(int init_bal) {
		this.init_bal = init_bal;
	}

	public int getFinal_bal() {
		return final_bal;
	}

	public void setFinal_bal(int final_bal) {
		this.final_bal = final_bal;
	}

}
