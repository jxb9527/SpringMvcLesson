package cn.et.springmvc.lesson1;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * 浏览器的提交方式，必须和@RequestMapping指定的资源动作必须一致，不一致会抛出405（表示请求方式不匹配）
 * 
 * @author Administrator
 *
 */
@Controller
public class RestController {
	
	@RequestMapping(value="/user/{userId}",method=RequestMethod.GET)
	public String queryUser(@PathVariable String userId,HttpServletResponse response) throws IOException{
		response.getWriter().println(userId+" -----------queryUser");
		return null;	
	}
	
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public String addUser(String name,HttpServletResponse response) throws IOException{
		response.getWriter().println(name+" -----------addUser");
		return null;	
	}
	
	@RequestMapping(value="/user/{userId}",method=RequestMethod.PUT)
	public String updateUser(@PathVariable String userId,HttpServletResponse response) throws IOException{
		response.getWriter().println(userId+" -----------updateUser");
		return null;	
	}
	
	@RequestMapping(value="/user/{userId}",method=RequestMethod.DELETE)
	public String deleteUser(@PathVariable String userId,HttpServletResponse response) throws IOException{
		response.getWriter().println(userId+" -----------deleteUser");
		return null;	
	}
}
