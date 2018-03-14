package com.hening.sale.corecode.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hening.sale.corecode.login.entity.User;
import com.hening.sale.corecode.login.mapper.LoginMapper;
import com.hening.sale.corecode.login.service.LoginService;


@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginMapper loginMapper;

	@Override
	public User findUserByLoginName(String loginName) {
		User user = new User();
		try {
			user = loginMapper.findUserByLoginName(loginName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
}
