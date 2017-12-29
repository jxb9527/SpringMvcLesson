package cn.et.springmvc.lesson6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * json字符串的键一定要带双引号:值（数字不需要带双引号，字符串必须带双引号）
 * @author Administrator
 *
 */
public class Test {
	public static void main(String[] args) {
		Map map=new HashMap();
		map.put("id", 1);
		map.put("name", "历史");
		
		
		Map map1=new HashMap();
		map1.put("id", "2");
		map1.put("name", "语文");
		map1.put("map", map);
		
		List list = new ArrayList();
		list.add(map1);
		list.add(map);
		
		/*
		 * {
		 * 	"id":2,
		 * 	"name":"语文",
		 * 	"map":{
		 * 		"id":1,
		 * 		"name":"历史"
		 * 	}
		 * }
		 * 
		 * */
		//解析一个对象
		JSONObject fromObject = JSONObject.fromObject(map1);
		System.out.println(fromObject.toString());
//		
		//解析数组
		JSONArray fromObject2 = JSONArray.fromObject(list);
		System.out.println(fromObject2);
	}
}
