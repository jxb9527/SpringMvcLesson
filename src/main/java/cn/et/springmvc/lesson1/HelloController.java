package cn.et.springmvc.lesson1;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * springmvc中一个路径和方法的映射，叫做一个action（动作）增删该查
 * @author Administrator
 *
 */
@Controller
public class HelloController {
	
	@RequestMapping("/test")
	public String query(User user,HttpServletResponse response) throws IOException{
		response.getWriter().println("hhehhehe"+user.getId()+"-------"+user.getName());
		return "/index.jsp";
	}
}
