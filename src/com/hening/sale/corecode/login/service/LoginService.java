package com.hening.sale.corecode.login.service;

import com.hening.sale.corecode.login.entity.User;

public interface LoginService {
	
	User findUserByLoginName(String loginName);
}
