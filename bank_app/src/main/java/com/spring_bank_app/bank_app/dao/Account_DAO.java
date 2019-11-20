package com.spring_bank_app.bank_app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import com.spring_bank_app.bank_app.model.Account;

public class Account_DAO extends JdbcDaoSupport implements ApplicationContextAware {

	private ApplicationContext context = null;

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		// TODO Auto-generated method stub
		this.context = context;
	}

	public void del_Acct(String password, int acct_id) {
		String sql = "delete from transac_log where acct_id=?";
		this.getJdbcTemplate().update(sql, new Object[] { acct_id });
		sql = "delete from credentials where acct_id=?";
		this.getJdbcTemplate().update(sql, new Object[] { acct_id });
		sql = "delete from accounts where acct_id=?";
		this.getJdbcTemplate().update(sql, new Object[] { acct_id });
		sql = "delete from cust_info where acct_id=?";
		this.getJdbcTemplate().update(sql, new Object[] { acct_id });

		System.out.println("Account Successfully Deleted");
	}

	public void transfer(int amount, int acct_id, int dest_acct_id) {
		int sender_init_balance = check_bal(acct_id);
		int receiver_init_balance = check_bal(dest_acct_id);

		if (sender_init_balance == 0) {
			System.out.println("Can't proceed with the transfer. The Account has zero balance.");
		} else {
			String sql = "update accounts set balance=balance-? where acct_id=?";
			this.getJdbcTemplate().update(sql, new Object[] { amount, acct_id });

			sql = "update accounts set balance=balance+? where acct_id=?";
			this.getJdbcTemplate().update(sql, new Object[] { amount, dest_acct_id });
			System.out.println("Amount of " + amount + " successfully Transferred from Account: " + acct_id + " to "
					+ dest_acct_id);

			Logger_DAO log = context.getBean("logger_DAO", Logger_DAO.class);
			log.add_Log(acct_id, "Transfer", "Successful", sender_init_balance, sender_init_balance - amount);

			log.add_Log(dest_acct_id, "Deposit", "Successful", receiver_init_balance, receiver_init_balance + amount);
		}
	}

	public void withdraw(int amount, int acct_id) {
		int init_balance = check_bal(acct_id);
		if (init_balance == 0) {
			System.out.println("Can't withdraw money. The Account has zero balance.");
		} else {
			String sql = "update accounts set balance=balance-? where acct_id=?";
			this.getJdbcTemplate().update(sql, new Object[] { amount, acct_id });
			System.out.println("Amount of " + amount + " successfully withdrawn from Account: " + acct_id);

			Logger_DAO log = context.getBean("logger_DAO", Logger_DAO.class);
			log.add_Log(acct_id, "Withdraw", "Successful", init_balance, init_balance - amount);
		}
	}

	public void deposit(int amount, int acct_id) {
		int init_balance = check_bal(acct_id);

		String sql = "update accounts set balance=balance+? where acct_id=?";
		this.getJdbcTemplate().update(sql, new Object[] { amount, acct_id });
		System.out.println("Amount of " + amount + " successfully deposited to Account: " + acct_id);

		Logger_DAO log = context.getBean("logger_DAO", Logger_DAO.class);
		log.add_Log(acct_id, "Deposit", "Successful", init_balance, init_balance + amount);
	}

	public int check_bal(int acct_id) {
		String sql = "Select balance from accounts where acct_id=?";
		return this.getJdbcTemplate().queryForObject(sql, new Object[] { acct_id }, Integer.class);
	}

	public void get_Acct_Info(int acct_id) {
		String sql = "SELECT * FROM accounts WHERE acct_id = ?";
		List<Account> record = this.getJdbcTemplate().query(sql, new Object[] { acct_id }, new Account_Mapper());
		for (Account r : record) {
			System.out.println("Account ID : " + r.getAcct_id());
			System.out.println("Balance : " + r.getBalance());
			System.out.println("Account Status : " + r.getAcct_status());
		}
	}

	private static final class Account_Mapper implements RowMapper<Account> {

		public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
			Account acct_obj = new Account();
			acct_obj.setAcct_id(rs.getInt("acct_id"));
			acct_obj.setBalance(rs.getInt("balance"));
			acct_obj.setAcct_status(rs.getString("acct_status"));
			return acct_obj;
		}
	}

}
