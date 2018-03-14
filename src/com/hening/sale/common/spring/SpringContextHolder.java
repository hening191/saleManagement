package com.hening.sale.common.spring;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextHolder implements ApplicationContextAware{
	
	private static ApplicationContext applicationContext;

	/**
	 * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
	 *@title:setApplicationContext
	 *@author mf-luozg 
	 *@description @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		SpringContextHolder.applicationContext = arg0;
	}

	/**
	 * 
	 *@description 此方法描述的是：此方法描述的是：取得存储在静态变量中的ApplicationContext.
	 *@author mf-luozg 
	 *@version 2014年12月5日上午11:21:03.
	 */
	public static ApplicationContext getApplicationContext() {
		checkApplicationContext();
		return applicationContext;
	}
	
	/**
	 * 
	 *@description 此方法描述的是：此方法描述的是：从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 *@author mf-luozg 
	 *@version 2014年12月5日上午11:21:20.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		checkApplicationContext();
		return (T) applicationContext.getBean(name);
	}
	
	/**
	 * 
	 *@description 此方法描述的是：此方法描述的是：从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型. 如果有多个Bean符合Class, 取出第一个.
	 *@author mf-luozg 
	 *@version 2014年12月5日上午11:21:35.
	 */
	@SuppressWarnings("all")
	public static <T> T getBean(Class<T> clazz) {
		checkApplicationContext();
		Map beanMaps = applicationContext.getBeansOfType(clazz);
		if (beanMaps != null && !beanMaps.isEmpty()) {
			return (T) beanMaps.values().iterator().next();
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 *@description 此方法描述的是：此方法描述的是： 找不到bin 给出异常提示
	 *@author mf-luozg 
	 *@version 2014年12月5日上午11:22:00.
	 */
	private static void checkApplicationContext() {
		if (applicationContext == null) {
			throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
		}
	}
	
}
