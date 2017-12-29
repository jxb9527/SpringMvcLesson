package cn.et.springmvc.lesson2.crud.dao;

import java.util.List;
import java.util.Map;

public interface FoodDao {
	/**
	 * 根据名字模糊查询 
	 * @param name	名字
	 * @return 
	 */
	public List<Map<String,Object>> queryByName(String name,Integer startIndex,Integer pageCount);
	/**
	 * 新增菜品
	 * @param foodName 名字
	 * @param price	价格
	 * @param imagePath	图片路径
	 */
	public void saveFood(String foodName,String price,String imagePath);
	/**
	 * 修改菜品
	 * @param foodId 菜品id
	 * @param foodName 菜品名字
	 * @param price	菜品价格
	 * @param imagePath	菜品图片路径
	 */
	public void updateFood(String foodId,String foodName,String price,String imagePath);
	/**
	 * 删除菜品
	 * @param id 菜品id
	 */
	public void deleteFood(String id);
	/**
	 * 根据id查详细信息
	 * @param foodId 菜品id
	 * @return 
	 */
	public Map<String, Object> queryById(String foodId);
	/**
	 * 根据名字获取数据库总条数
	 * @param name 菜品姓名 
	 * @return
	 */
	public Integer getTableListCount(String name);
}
