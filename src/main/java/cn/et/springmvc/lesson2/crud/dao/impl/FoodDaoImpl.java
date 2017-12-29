package cn.et.springmvc.lesson2.crud.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.et.springmvc.lesson2.crud.dao.FoodDao;

@Repository
public class FoodDaoImpl implements FoodDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> queryByName(String foodName,Integer startIndex,Integer pageCount) {
		String sql="select * from myfood where foodname like '%"+foodName+"%' limit "+startIndex+","+pageCount;
		List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
		return result;
	}

	public void saveFood(String foodName, String price, String imagePath) {
		String sql ="insert into myfood(foodname,price,imagepath) values('"+foodName+"','"+price+"','"+imagePath+"')";
		jdbcTemplate.execute(sql);
	}

	public void updateFood(String foodId,String foodName, String price, String imagePath) {
		String sql="update myfood set foodname='"+foodName+"',price='"+price+"',imagepath='"+imagePath+"' where foodid="+foodId;
		jdbcTemplate.execute(sql);
	}

	public void deleteFood(String foodId) {
		String sql="delete from myfood where foodid="+foodId;
		jdbcTemplate.execute(sql);
	}

	public Map<String, Object> queryById(String foodId) {
		String sql="select * from myfood where foodid="+foodId;
		List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
		return result.get(0);
	}

	public Integer getTableListCount(String name) {
		String sql="select count(*) as cr from myfood where foodname like '%"+name+"%'";
		List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
		return Integer.parseInt(result.get(0).get("cr").toString());
	}

}
