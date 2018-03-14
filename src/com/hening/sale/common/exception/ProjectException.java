package com.hening.sale.common.exception;

/**
 * 
 *@company 美福科技
 *@ClassName ProjectException
 *@author mf-luozg 
 *@date 2014年12月5日下午1:50:08
 */
@SuppressWarnings("serial")
public class ProjectException extends Exception{
	
	public ProjectException() { }
	
	
	public ProjectException(String msg) {
		super( msg );
	}
}
