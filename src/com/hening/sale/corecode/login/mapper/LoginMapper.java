package com.hening.sale.corecode.login.mapper;

import org.apache.ibatis.annotations.Param;

import com.hening.sale.corecode.login.entity.User;

public interface LoginMapper {
	
	User findUserByLoginName(@Param("loginName")String loginName);
}
