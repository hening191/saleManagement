package com.hening.sale.corecode.product.entity;

public class Product {
	
	private Integer productId;
	private String productName;
	private Integer sock;
	private double price;
	private double prePrice;
	private String source;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getSock() {
		return sock;
	}
	public void setSock(Integer sock) {
		this.sock = sock;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPrePrice() {
		return prePrice;
	}
	public void setPrePrice(double prePrice) {
		this.prePrice = prePrice;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
}
