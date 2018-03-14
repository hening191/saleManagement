package com.hening.sale.corecode.sale.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hening.sale.corecode.sale.entity.Sale;

public interface SaleService {
	
	List<Map<String,Object>> findProList(Sale sale);
	
	void addProductRecord(Sale sale);

	Integer getProductRecordCount(Sale sale);
	
	void deleteProductRecord(Sale sale);
	
	void downloadSale(Sale sale,HttpServletRequest request,HttpServletResponse response);
}
