package com.hening.sale.common.exception;

public class ORSException extends RuntimeException{

	
	 /**
	 *
	 *@field serialVersionUID:TODO(description here)
	 *@author (作者):mf-luozg
	 *
	 */
	 
	private static final long serialVersionUID = 1L;
	
	
	public ORSException() { }
	
	
	public ORSException(String msg) {
		super( msg );
	}

}
