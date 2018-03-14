package com.hening.sale.corecode.login.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hening.sale.utils.SessionUtil;
import com.hening.sale.corecode.login.entity.User;
import com.hening.sale.corecode.login.service.LoginService;
import com.hening.sale.common.base.BaseAction;

@SuppressWarnings("serial")
public class LoginAction extends BaseAction {

	private String loginName;
	private String password;
	private Map<String,Object> resMap = new HashMap<String,Object>();
	
	@Autowired
	private LoginService loginService;
	
	public String login() {
		SessionUtil.clearCurrentUser(request, response);
		return "fail";
	}
	
	public String doLogin(){
		if( loginName == null || "".equals( loginName ) ){
			resMap.put("msg", "请输入用户名");
			resMap.put("success", 0);
			resMap.put("msg_type", "0");
			return  "error";
		}
		if( password == null || "".equals( password ) ){
			resMap.put("msg", "请输入密码");
			resMap.put("success", 0);
			resMap.put("msg_type", "1");
			return  "error";
		}
		try {
			User user = loginService.findUserByLoginName(loginName);
			if(user != null){
				if(password.equals(user.getPassword())){
					SessionUtil.setCurrentUser( request, response, user );
					resMap.put("success", 1);
					return "success";
				}else{
					resMap.put("msg", "密码错误");
					resMap.put("success", 0);
					resMap.put("msg_type", "1");
					return "error";
				}
			}else{
				resMap.put("msg", "帐号不存在");
				resMap.put("success", 0);
				resMap.put("msg_type", "0");
				return "error";
			}
		} catch (Exception e) {
			return "fail";
		}
	}

	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Map<String, Object> getResMap() {
		return resMap;
	}

	public void setResMap(Map<String, Object> resMap) {
		this.resMap = resMap;
	}
	
	
}
