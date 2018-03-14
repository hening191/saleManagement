package com.hening.sale.corecode.product.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hening.sale.corecode.product.entity.Product;
import com.hening.sale.corecode.product.mapper.ProductMapper;
import com.hening.sale.corecode.product.service.ProductService;

@Service("ProductService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public List<Map<String, Object>> findProductList(Product product) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = productMapper.findProductList(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Product findProductByName(String name) {
		Product product = new Product();
		try {
			product = productMapper.findProductByName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public void addProduct(Product product) {
		try {
			productMapper.addProduct(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void editProduct(Product product) {
try {
	productMapper.editProduct(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteProduct(Product product) {
try {
	productMapper.deleteProduct(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Product findProductById(Integer productId) {
		Product product = new Product();
		try {
			product = productMapper.findProductById(productId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

}
