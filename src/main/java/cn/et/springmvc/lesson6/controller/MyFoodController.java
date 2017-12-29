package cn.et.springmvc.lesson6.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.et.springmvc.lesson6.service.MyFoodService;

@Controller
public class MyFoodController {
	/*OutputStream os;
	 * 原始的输出json方式是通过OutputStream os;然后再通过os.write(第三方json-lib转换的json字符串.getBytes("UTF-8"));来获取数据的。输出到浏览器会有乱码。
	 *byte[]:
	 *	 在springmvc中简化了，返回字节数组，springmvc看到了返回的是btye[],会自己帮我们写到OutputStream流中,不需要我们自己来写入流中
	 *	正常的来说返回btye[]是不认识的，我们要在方法上加上@ResponseBody这个注解，告诉他这是响应的消息体。默认已经配置了消息转换器ByteArrayHttpMessageConverter。输出到浏览器不会有乱码。
	 *List<Map<String,Object>>:
	 *	可以直接在返回List<Map<String,Object>>。调用MappingJackson2HttpMessageConverter，要在springmvc-servlet.xml中配置消息转换器，消息转换器有两种，一种针对请求的（参数），一种针对响应的（返回值）。
	 *	配置：
	 *	<mvc:annotation-driven>
			<!-- 配置消息转换器 -->
			<mvc:message-converters>
				<!-- 配置返回List<Map<String,Object>>解析成json的消息转换器 -->
				<bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
					<property name="supportedMediaTypes">
						<list>
							<value>text/html</value>
							<value>application/x-www-form-urlencoded;charset=utf-8</value>
						</list>
					</property>
			
				</bean>
			</mvc:message-converters>
		</mvc:annotation-driven>
		
		还要引一些库（依赖）：才支持
	 * */
	@Autowired
	MyFoodService myfood;
	
	@RequestMapping(value="/queryFood",method={RequestMethod.GET,RequestMethod.POST})
	public String queryFood(String foodName,OutputStream os) throws UnsupportedEncodingException, IOException{
		List<Map<String, Object>> queryAllFood = myfood.queryAllFood(foodName);
		JSONArray fromObject = JSONArray.fromObject(queryAllFood);
		String jsonStr = fromObject.toString();
		os.write(jsonStr.getBytes("UTF-8"));
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryFoodReturn",method={RequestMethod.GET,RequestMethod.POST})
	public byte[] queryFoodReturn(String foodName) throws UnsupportedEncodingException, IOException{
		List<Map<String, Object>> queryAllFood = myfood.queryAllFood(foodName);
		JSONArray fromObject = JSONArray.fromObject(queryAllFood);
		String jsonStr = fromObject.toString();
		return jsonStr.getBytes("UTF-8");
	}
	
	@ResponseBody
	@RequestMapping(value="/queryFoodReturnList",method={RequestMethod.GET,RequestMethod.POST})
	public List<Map<String,Object>> queryFoodReturnList(String foodName) throws UnsupportedEncodingException, IOException{
		List<Map<String, Object>> queryAllFood = myfood.queryAllFood(foodName);
		return queryAllFood;
	}
	
	/**
	 * 删除food
	 * @param foodId 菜品id
	 * @param os
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/food/{foodId}",method={RequestMethod.DELETE})
	public String deleteFood(@PathVariable String foodId,OutputStream os) throws Exception{
		try {
			myfood.deleteFood(foodId);
			os.write("1".getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			os.write("0".getBytes("UTF-8"));
		}
		return null;
	}
	
	/**
	 * 修改food
	 * @param foodId 菜品id
	 * @param foodName 菜品名
	 * @param price 菜品价格
	 * @param os
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/food/{foodId}",method={RequestMethod.PUT})
	public String udpateFood(@PathVariable String foodId,String foodName,String price,OutputStream os) throws Exception{
		try {
			myfood.updateFood(foodId, foodName, price);
			os.write("1".getBytes("UTF-8"));
		} catch (Exception e) {
			os.write("0".getBytes("UTF-8"));
		}
		return null;
	}
	
	/**
	 * 新增菜品
	 * @param foodName 菜品名称
	 * @param price 价格
	 * @param os
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/food",method={RequestMethod.POST})
	public String saveFood(String foodName,String price,OutputStream os) throws Exception{
		try {
			myfood.saveFood(foodName, price);
			os.write("1".getBytes("UTF-8"));
		} catch (Exception e) {
			os.write("0".getBytes("UTF-8"));
		}
		return null;
	}
}
