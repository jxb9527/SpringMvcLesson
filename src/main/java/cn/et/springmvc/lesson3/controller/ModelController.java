package cn.et.springmvc.lesson3.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/**
 * springmvc中Model相关对象，是处理和数据相关的对象
 * 	@ModelAttribute 重命名 参数数据
 *  Model传递数据到视图(request.setAttribute)
 *  ModelMap 传递数据到视图
 *  Map 传递数据到视图
 *  ModelAndView 绑定数据到视图(ModelMap用于传递数据，View对象用于跳转)
 * @author Administrator
 *	请求转发 forward:
 *	请求重定向 redirect:
 */
@Controller
public class ModelController {
	@RequestMapping(value="/case",method=RequestMethod.GET)
	public String case1(Map map){
		map.put("aaa", "bbb");
		
		return "forward:/lesson3/res.jsp";
	}
	@RequestMapping(value="/case2",method=RequestMethod.GET)
	public ModelAndView case2(){
		ModelAndView mav = new ModelAndView("/lesson3/res.jsp");
		mav.addObject("sss","xxx");
		return mav;
	}
}
