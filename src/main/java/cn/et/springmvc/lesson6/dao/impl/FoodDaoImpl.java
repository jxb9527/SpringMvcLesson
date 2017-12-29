package cn.et.springmvc.lesson6.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.et.springmvc.lesson6.dao.MyFoodDao;
@Repository
public class FoodDaoImpl implements MyFoodDao{
	
	@Autowired
	JdbcTemplate jdbc;
	
	public List<Map<String, Object>> queryAllFood(String foodName) {
		return jdbc.queryForList("select * from myfood where foodname like '%"+foodName+"%'");
	}

	
	public void deleteFood(String foodid) {
		String sql="delete from myfood where foodid="+foodid;
		jdbc.execute(sql);
	}
	
	public void updateFood(String foodid,String foodName,String price) {
		String sql="update myfood set foodname='"+foodName+"',price='"+price+"' where foodid="+foodid;
		jdbc.execute(sql);
	}
	
	public void saveFood(String foodName,String price) {
		String sql="insert into myfood(foodname,price,imagepath) values('"+foodName+"',"+price+",'"+"/ddd.jpg"+"')";
		jdbc.execute(sql);
	}
}
