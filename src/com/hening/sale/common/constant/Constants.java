package com.hening.sale.common.constant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 *@company 美福科技
 *@ClassName Constants
 *@author mf-luozg 
 *@date 2014年12月5日上午11:38:15
 */
@SuppressWarnings("serial")
public class Constants implements Serializable{

	// 当前用户的信息
	public static final String CURRENT_USER = "CURRENT_USER";
	
	//用户阅读习惯
	public static final String CURRENT_USER_READ_HABIT = "CURRENT_USER_READ_HABIT";

	//用户阅读习惯
	public static final String CURRENT_SCHOOL_CONFIG = "CURRENT_SCHOOL_CONFIG";
	

	/*  时间，日期格式定义 */
	/**
	 * 优先选用
	 */
	public static final String DATE_TIME_TYPE1_24H = "yyyy-MM-dd HH:mm:ss";//优先选用

	public static final String DATE_TIME_TYPE1_12H = "yyyy-MM-dd hh:mm:ss";

	public static final String DATE_TIME_TYPE2 = "yyyy/MM/dd HH:mm:ss";

	public static final String DATE_TIME_TYPE3 = "yyyy/MM/dd HH:mm:ss";

	public static final String DATA_TIME_TYPE4 = "yyyyMMddhhmmssSSS";

	public static final String DATE_TIME_TYPE5 = "MM/dd/yyyy HH:mm:ss";

	public static final String DATE_TIME_TYPE6 = "yyyyMMdd HH:mm:ss";

	public static final String DATE_TYPE1 = "yyyy-MM-dd";

	public static final String DATE_TYPE2 = "yyyy/MM/dd";

	/**
	 * 优先选用
	 */
	public static final String DATE_TYPE3 = "yyyy年MM月dd日";//优先选用

	public static final String DATE_TYPE4 = "MM/dd";

	/**
	 * 优先选用
	 */
	public static final String TIME_TYPE1 = "HH:mm:ss";//优先选用

	public static final String TIME_TYPE2 = "HH/mm/ss";

	public static final String TIME_TYPE3 = "HH时mm分ss秒";
	
	public static final String TIME_TYPE4 = "HH:mm";

	public static final String TIME_TYPE5 = "HH/mm";

	public static final String TIME_TYPE6 = "HH时mm分";
	
	
	public static final List<String> URL_LIST = new ArrayList<String>();
	
	
}
