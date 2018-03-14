package com.hening.sale.corecode.product.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hening.sale.corecode.product.entity.Product;

public interface ProductMapper {
	
	List<Map<String,Object>> findProductList(@Param("pro")Product product);
	
	void addProduct(@Param("pro")Product product);
	
	void editProduct(@Param("pro")Product product);
	
	void deleteProduct(@Param("pro")Product product);
	
	Product findProductByName(@Param("name")String name);
	
	Product findProductById(@Param("productId")Integer productId);
}
