package cn.et.springmvc.lesson6.service;

import java.util.List;
import java.util.Map;

public interface MyFoodService {
	public List<Map<String ,Object>> queryAllFood(String foodName);
	
	public void deleteFood(String foodid);
	
	public void updateFood(String foodid,String foodName,String price);
	
	public void saveFood(String foodName,String price);
		
}
