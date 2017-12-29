package cn.et.springmvc.lesson6.dao;

import java.util.List;
import java.util.Map;

public interface MyFoodDao {
	public List<Map<String ,Object>> queryAllFood(String foodName);
	
	public void deleteFood(String foodid);
	
	public void updateFood(String foodid,String foodName,String price);
	
	public void saveFood(String foodName,String price);
		
}
