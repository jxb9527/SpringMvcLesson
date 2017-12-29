package cn.et.springmvc.lesson2.crud.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.et.springmvc.lesson2.crud.dao.FoodDao;
import cn.et.springmvc.lesson2.crud.service.FoodService;
import cn.et.springmvc.lesson2.crud.util.PageTool;
@Service
public class FoodServiceImpl implements FoodService{
	
	@Autowired
	private FoodDao foodDao;
	
	public PageTool queryByName(String name,Integer curPage) {
		if(name==null ){
			name="";
		}
		Integer totalCount = getTableListCount(name);
		
		PageTool pt = new PageTool(curPage,totalCount,2);
		
		List<Map<String, Object>> result = foodDao.queryByName(name, pt.getStartIndex()-1, pt.getPageCount());
		
		pt.setData(result);
		
		return pt;
	}

	public void saveFood(String foodName, String price, String imagePath) {
		foodDao.saveFood(foodName, price, imagePath);
		
	}

	public void updateFood(String foodId, String foodName, String price,
			String imagePath) {
		foodDao.updateFood(foodId, foodName, price, imagePath);
	}

	public void deleteFood(String id) {
		foodDao.deleteFood(id);
	}

	public Map<String, Object> queryById(String foodId) {
		Map<String, Object> result = foodDao.queryById(foodId);
		return result;
	}

	public Integer getTableListCount(String name) {
		Integer tableListCount = foodDao.getTableListCount(name);
		return tableListCount;
	}

}
