package com.hening.sale.corecode.product.service;

import java.util.List;
import java.util.Map;

import com.hening.sale.corecode.product.entity.Product;

public interface ProductService {
	
	List<Map<String,Object>> findProductList(Product product);
	
	Product findProductByName(String name);
	
	Product findProductById(Integer productId);
	
	void addProduct(Product product);
	
	void editProduct(Product product);
	
	void deleteProduct(Product product);
}
