package com.spring_bank_app.bank_app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import com.spring_bank_app.bank_app.model.Customer;

public class Customer_DAO extends JdbcDaoSupport {

	public void get_Cust_Info(int acct_id) {
		String sql = "SELECT * FROM cust_info WHERE acct_id = ?";
		List<Customer> record = this.getJdbcTemplate().query(sql, new Object[] { acct_id }, new Cust_Info_Mapper());
		for (Customer r : record) {
			System.out.println("ID : " + r.getAcct_id());
			System.out.println("Name : " + r.getCust_name());
			System.out.println("City : " + r.getCity());
			System.out.println("State : " + r.getState());
			System.out.println("Country : " + r.getCountry());
			System.out.println("Phone Number : " + r.getPhone_no());
			System.out.println("Password : " + r.getPassword());
		}
	}

	private static final class Cust_Info_Mapper implements RowMapper<Customer> {

		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer cust_info_obj = new Customer();
			cust_info_obj.setAcct_id(rs.getInt("acct_id"));
			cust_info_obj.setCust_name(rs.getString("cust_name"));
			cust_info_obj.setCity(rs.getString("city"));
			cust_info_obj.setState(rs.getString("state"));
			cust_info_obj.setCountry(rs.getString("country"));
			cust_info_obj.setPhone_no(rs.getInt("phone_no"));
			cust_info_obj.setPassword(rs.getString("password"));
			return cust_info_obj;
		}
	}

	public void create_Acct(Customer cust_info) {
		String sql = "select count(*) from cust_info where acct_id=?";
		int records = this.getJdbcTemplate().queryForObject(sql, new Object[] { cust_info.getAcct_id() },
				Integer.class);

		if (records == 1) {
			System.out.println("Account with Account ID: " + cust_info.getAcct_id() + " already present!!!");
		} else {
			sql = "insert into cust_info (acct_id, cust_name, city, state, country, phone_no, password) "
					+ "values(?,?,?,?,?,?,?)";
			this.getJdbcTemplate().update(sql,
					new Object[] { cust_info.getAcct_id(), cust_info.getCust_name(), cust_info.getCity(),
							cust_info.getState(), cust_info.getCountry(), cust_info.getPhone_no(),
							cust_info.getPassword() });

			sql = "insert into credentials (acct_id, password) values (?,?)";
			this.getJdbcTemplate().update(sql, new Object[] { cust_info.getAcct_id(), cust_info.getPassword() });

			sql = "insert into accounts (acct_id, balance, acct_status ) values (?,?,?)";
			this.getJdbcTemplate().update(sql, new Object[] { cust_info.getAcct_id(), 0, "Active" });

			System.out.println("Account Created Successfully");
		}
	}

	public int check_Credentials(int acct_id, String password) {
		String sql = "select count(*) from credentials where acct_id=? and password=?";
		return this.getJdbcTemplate().queryForObject(sql, new Object[] { acct_id, password }, Integer.class);
	}

}
