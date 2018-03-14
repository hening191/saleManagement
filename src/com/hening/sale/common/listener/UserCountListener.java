package com.hening.sale.common.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class UserCountListener implements HttpSessionListener{

	
	public void sessionCreated(HttpSessionEvent arg0) {
		//session创建时，修改memcache中在线人数
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		//session销毁时，修改memcache中在线人数
	}

}
