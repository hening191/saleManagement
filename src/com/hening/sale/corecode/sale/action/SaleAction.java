package com.hening.sale.corecode.sale.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hening.sale.corecode.login.entity.User;
import com.hening.sale.corecode.sale.entity.Sale;
import com.hening.sale.corecode.sale.service.SaleService;
import com.hening.sale.utils.SessionUtil;
import com.hening.sale.common.base.BaseAction;

@SuppressWarnings("serial")
public class SaleAction extends BaseAction{
	
	private Map<String,Object> resMap = new HashMap<String,Object>();
	private Sale sale = new Sale();
	
	@Autowired
	private SaleService saleService;
	
	public String sale(){
		User user = SessionUtil.getCurrenUser(request);
		resMap.put("user", user);
		return "sale-success";
	}
	
	public String findSaleList(){
		try {
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			sale.setType(2);
			list = saleService.findProList(sale);
			resMap.put("list", list);
			resMap.put("success", 1);
		} catch (Exception e) {
			resMap.put("success", 0);
			resMap.put("info", "数据请求错误，请联系管理员");
			e.printStackTrace();
		}
		return "findSaleList-success";
	}
	
	public void downloadSale(){
		try {
			sale.setType(2);
			saleService.downloadSale(sale, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public Map<String, Object> getResMap() {
		return resMap;
	}
	public void setResMap(Map<String, Object> resMap) {
		this.resMap = resMap;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	
}
