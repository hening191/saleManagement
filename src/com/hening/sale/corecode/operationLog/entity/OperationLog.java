package com.hening.sale.corecode.operationLog.entity;

public class OperationLog {

	private Integer exceptionId;
	private Integer userId;
	private String name;
	private String operation;
	private String operationDate;
	private String b_date;
	private String d_date;
	
	
	public Integer getExceptionId() {
		return exceptionId;
	}
	public void setExceptionId(Integer exceptionId) {
		this.exceptionId = exceptionId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}
	public String getB_date() {
		return b_date;
	}
	public void setB_date(String b_date) {
		this.b_date = b_date;
	}
	public String getD_date() {
		return d_date;
	}
	public void setD_date(String d_date) {
		this.d_date = d_date;
	}
	
}
