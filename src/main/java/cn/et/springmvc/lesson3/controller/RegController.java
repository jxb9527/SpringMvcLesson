package cn.et.springmvc.lesson3.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.et.springmvc.lesson3.entity.UserInfo;
/**
 * 后台验证步骤
 * 	1.javabean添加验证注解
 * 	2.action中使用@Valid表示javabean 使用Errors或者BindingResult判断是否验证失败
 * 	3.出现jar包冲突4.3.2
 * @author Administrator
 *
 */
@Controller
public class RegController {
	//如果想用其他名字在参数上添加@ModelAttribute("user")
	@RequestMapping(value="/reg",method=RequestMethod.POST)
	public String reg(@Valid UserInfo userInfo,BindingResult error){
		//编程式
		if(!userInfo.getPassword().equals(userInfo.getRepassword())){
			error.addError(new FieldError("userInfo", "repassword", "两次输入密码不一致"));
		}
		//判断是否有异常信息
		if(error.hasErrors()){
			return "lesson3/reg.jsp";
		}
		return "lesson3/suc.jsp";
	}
}
