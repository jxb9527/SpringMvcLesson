package cn.et.springmvc.lesson5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.et.springmvc.lesson5.dao.MoneyDao;
import cn.et.springmvc.lesson5.service.MoneyService;
@Service
public class MoneyServiceImpl implements MoneyService {
	@Autowired
	MoneyDao moneyDao;
	
	public void trasnateMoney(Integer money) {
		moneyDao.trasnateMoney(money);
	}

	public Integer selectMoney() {
		return moneyDao.selectMoney();
	}

}
