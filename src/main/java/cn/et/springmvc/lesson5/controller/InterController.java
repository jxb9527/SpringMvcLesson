package cn.et.springmvc.lesson5.controller;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.et.springmvc.lesson5.service.MoneyService;

/**
 * 两种情况输入重复提交
 * 	1.点完提交之后按F5刷新
 * 	2.当点了后退按钮，在提交也是重复提交
 * @author Administrator
 *
 */
@Controller
public class InterController {
	@Autowired
	MoneyService moneyService;
	
	@RequestMapping(value="/inter",method=RequestMethod.GET)
	public String reg(OutputStream os) throws IOException{
		os.write("hell".getBytes());
		return null;
	}
	
	@RequestMapping(value="/tm",method=RequestMethod.GET)
	public String tm(Integer money,OutputStream os) throws IOException{
		moneyService.trasnateMoney(money);
		os.write(("ubalance is:"+moneyService.selectMoney()).getBytes());
		return null;
	}
}
