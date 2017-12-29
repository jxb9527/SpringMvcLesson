package cn.et.springmvc.lesson5;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TokenInteractor implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String myToken = request.getParameter("myToken");
		
		Object sessionMyToken = request.getSession().getAttribute("myToken");
		//需要验证重复提交
		if(myToken!=null){
			
			//重复提交
			if(sessionMyToken==null){
				return false;
			}else{
				if(myToken.equals(sessionMyToken)){
					request.getSession().removeAttribute("myToken");
					return true;
				}else{
					return false;
				}
			}
			
		}else{
			return true;
		}
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
