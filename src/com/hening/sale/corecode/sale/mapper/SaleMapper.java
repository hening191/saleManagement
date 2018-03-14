package com.hening.sale.corecode.sale.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hening.sale.corecode.sale.entity.Sale;

public interface SaleMapper {
	
	List<Map<String,Object>> findSaleRecords(@Param("sale")Sale sale);
	
	void addProductRecord(@Param("sale")Sale sale);
	
	Integer getProductRecordCount(@Param("sale")Sale sale);
	
	void deleteProductRecord(@Param("sale")Sale sale);
}
