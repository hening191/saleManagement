package com.hening.sale.corecode.buy.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hening.sale.common.base.BaseAction;
import com.hening.sale.corecode.login.entity.User;
import com.hening.sale.corecode.sale.entity.Sale;
import com.hening.sale.corecode.sale.service.SaleService;
import com.hening.sale.utils.SessionUtil;

@SuppressWarnings("serial")
public class BuyAction  extends BaseAction{
	
	private Map<String,Object> resMap = new HashMap<String,Object>();
	private Sale sale = new Sale();
	
	@Autowired
	private SaleService saleService;
	
	public String buy(){
		User user = SessionUtil.getCurrenUser(request);
		resMap.put("user", user);
		return "buy-success";
	}
	
	public String findBuyList(){
		try {
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			sale.setType(1);
			list = saleService.findProList(sale);
			resMap.put("list", list);
			resMap.put("success", 1);
		} catch (Exception e) {
			resMap.put("success", 0);
			resMap.put("info", "数据请求错误，请联系管理员");
			e.printStackTrace();
		}
		return "findBuyList-success";
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
