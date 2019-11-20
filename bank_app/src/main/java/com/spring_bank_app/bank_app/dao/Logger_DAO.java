package com.spring_bank_app.bank_app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import com.spring_bank_app.bank_app.model.Transac_Log;


public class Logger_DAO extends JdbcDaoSupport {

	public void add_Log(int acct_id, String transac_type, String transac_status, int init_bal, int final_bal) {

		String sql = "insert into transac_log (acct_id, transac_type, transac_status, init_bal, final_bal) values (?,?,?,?,?)";
		this.getJdbcTemplate().update(sql, new Object[] { acct_id, transac_type, transac_status, init_bal, final_bal });
	}

	public void show_Transac(int acct_id) {
		String sql = "SELECT * FROM transac_log WHERE acct_id = ?";
		List<Transac_Log> record = this.getJdbcTemplate().query(sql, new Object[] { acct_id },
				new Transac_Log_Mapper());
		System.out.println("| Account ID | " + " | Transaction Type | " + " | Transaction Status | "
				+ " | Initial Balance | " + " | Final Balance |");
		for (Transac_Log r : record) {
			System.out.println("	" + r.getAcct_id() + "		" + r.getTransac_type() + "		"
					+ r.getTransac_status() + "			" + r.getInit_bal() + "		" + r.getFinal_bal());
		}
	}

	private static final class Transac_Log_Mapper implements RowMapper<Transac_Log> {

		public Transac_Log mapRow(ResultSet rs, int rowNum) throws SQLException {
			Transac_Log transac_log_obj = new Transac_Log();
			transac_log_obj.setAcct_id(rs.getInt("acct_id"));
			transac_log_obj.setTransac_type(rs.getString("transac_type"));
			transac_log_obj.setTransac_status(rs.getString("transac_status"));
			transac_log_obj.setInit_bal(rs.getInt("init_bal"));
			transac_log_obj.setFinal_bal(rs.getInt("final_bal"));
			return transac_log_obj;
		}
	}
}
