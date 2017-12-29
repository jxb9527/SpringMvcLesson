package cn.et.springmvc.lesson5.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.et.springmvc.lesson5.dao.MoneyDao;

@Repository
public class MoneyDaoImpl implements MoneyDao{
	@Autowired
	JdbcTemplate jdbc;
	
	public void trasnateMoney(Integer money) {
		String sql="update mymoney set ubalance=ubalance-"+money+" where uid=1";
		jdbc.execute(sql);
	}

	public Integer selectMoney() {
		String sql="select ubalance from mymoney where uid=1";
		return 	jdbc.queryForObject(sql, Integer.class);
	}
}
