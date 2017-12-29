package cn.et.springmvc.lesson6.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.et.springmvc.lesson6.dao.MyFoodDao;
import cn.et.springmvc.lesson6.service.MyFoodService;
@Service
public class FoodServiceImpl implements MyFoodService {

	@Autowired
	MyFoodDao myFood;

	public List<Map<String, Object>> queryAllFood(String foodName) {
		if(foodName==null){
			foodName="";
		}
		return myFood.queryAllFood(foodName);
	}

	public void deleteFood(String foodid) {
		myFood.deleteFood(foodid);
	}

	public void updateFood(String foodid, String foodName, String price) {
		myFood.updateFood(foodid, foodName, price);
	}

	public void saveFood(String foodName, String price) {
		myFood.saveFood(foodName, price);
	}
	

}
