package cn.et.springmvc.lesson4.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * 拦截器原理：
 * 	它是一种类似于过滤器的一种拦截机制，过滤器是用来拦截servlet的一些资源，
 * 	springMvc是使用servlet实现的，中央处理器就叫做DispatcharServlet，又自己实现了一套过滤器，
 * 	用于拦截springmvc中action,该拦截器是springmvc内置实现的，它必须要实现一个接口HandlerInterceptor，在这几个接口中有三个方法，preHandle（在action执行之前）,postHandle（在action执行之前）,afterCompletion（在action完成之后）
 * @author Administrator
 *
 */
@Controller
public class ViewController {
	
	@Autowired
	private MessageSource ms;
	 
	@RequestMapping(value="/nation",method=RequestMethod.GET)
	public String reg(OutputStream os,Locale locale) throws UnsupportedEncodingException, NoSuchMessageException, IOException{
		
		os.write(ms.getMessage("key", null, locale).getBytes("UTF-8"));
		return null;
	}
	
	@RequestMapping(value="/mid",method=RequestMethod.GET)
	public String mid(String locale) {
		
		return "/lesson4/reg.jsp";
	}
}
