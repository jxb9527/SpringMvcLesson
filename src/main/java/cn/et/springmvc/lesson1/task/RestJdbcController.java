package cn.et.springmvc.lesson1.task;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RestJdbcController {
	@Autowired
	private JdbcTemplate jdbc;
	
	@RequestMapping(value="/student/{id}",method=RequestMethod.GET)
	public String queryUser(@PathVariable String id,HttpServletResponse response) throws IOException{
		String sql="select * from student where s="+id;
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println("<table><tr><th>编号</th><th>名字</th><th>年龄</th><th>性别</th></tr>");
		List<Map<String, Object>> queryForList = jdbc.queryForList(sql);
		for(Map<String, Object> map: queryForList){
			response.getWriter().println("<tr><td>"+map.get("s")+"</td><td>"+map.get("sname")+"</td><td>"+map.get("sage")+"</td><td>"+map.get("ssex")+"</td></tr>");
		}
		response.getWriter().println("</table>");
		return null;	
	}
}	
