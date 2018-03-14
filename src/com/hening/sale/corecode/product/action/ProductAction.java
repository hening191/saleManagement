package com.hening.sale.corecode.product.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hening.sale.common.base.BaseAction;
import com.hening.sale.corecode.login.entity.User;
import com.hening.sale.corecode.operationLog.entity.OperationLog;
import com.hening.sale.corecode.operationLog.service.OperationLogService;
import com.hening.sale.corecode.product.entity.Product;
import com.hening.sale.corecode.product.service.ProductService;
import com.hening.sale.corecode.sale.entity.Sale;
import com.hening.sale.corecode.sale.service.SaleService;
import com.hening.sale.utils.SessionUtil;

@SuppressWarnings("serial")
public class ProductAction extends BaseAction{
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private OperationLogService operationLogService;
	
	private Map<String,Object> resMap = new HashMap<String,Object>();
	private Product product;
	private Sale sale;
	
	public String product(){
		User user = SessionUtil.getCurrenUser(request);
		resMap.put("user", user);
		return "product-success";
	}

	public String findProductList(){
		try {
			List<Map<String,Object>> list = productService.findProductList(product);
			resMap.put("list", list);
			resMap.put("success", 1);
		} catch (Exception e) {
			resMap.put("success", 0);
			e.printStackTrace();
		}
		return "findProductList-success";
	}
	
	public String findProductByName(){
		try {
			product = productService.findProductByName(product.getProductName());
			resMap.put("product", product);
			resMap.put("success", 1);
		} catch (Exception e) {
			resMap.put("success", 0);
			e.printStackTrace();
		}
		return "findProductByName-success";
	}
	
	//入库
	public String addProduct(){
		try {
			Product pro = new Product();
			
			//操作记录产品个数先进行设置
			Sale sal = new Sale();
			sal.setCount(product.getSock());
			
			//库存添加
			pro = productService.findProductByName(product.getProductName());
			if(pro != null){
				product.setSock(product.getSock()+pro.getSock());
				product.setProductId(pro.getProductId());
				productService.editProduct(product);
			}else{
				productService.addProduct(product);
			}
			
			//设置剩余操作记录数据
			sal.setType(1);
			sal.setProductId(product.getProductId());
			User user = SessionUtil.getCurrenUser(request);
			sal.setOperator(user.getName());
			sal.setCustomer(product.getSource());
			
			saleService.addProductRecord(sal);
			resMap.put("info", "入库成功");
			resMap.put("success", 1);
		} catch (Exception e) {
			resMap.put("success", 0);
			resMap.put("info", "入库错误");
			e.printStackTrace();
		}
		return "addProduct-success";
	}
	
	//出售
	public String saleProduct(){
		try {
			//库存减少
			Product pro = new Product();
			pro = productService.findProductByName(sale.getProductName());
			if(pro != null){
				if(sale.getCount()<=pro.getSock()){
					pro.setSock(pro.getSock()-sale.getCount());
					productService.editProduct(pro);
				}else{
					resMap.put("success", 0);
					resMap.put("info", "产品库存不足");
				}
			}else{
				resMap.put("success", 0);
				resMap.put("info", "产品不存在");
			}
			
			//设置剩余操作记录数据
			sale.setProductId(pro.getProductId());
			sale.setType(2);
			User user = SessionUtil.getCurrenUser(request);
			sale.setOperator(user.getName());
			saleService.addProductRecord(sale);
			resMap.put("success", 1);
			resMap.put("info", "出售成功");
		} catch (Exception e) {
			resMap.put("success", 0);
			resMap.put("info", "出售错误");
			e.printStackTrace();
		}
		return "saleProduct-success";
	}
	
	//删除
	public String deleteProduct(){
		try {
			sale.setType(2);
			Integer count = saleService.getProductRecordCount(sale);
			if(count == -1){
				resMap.put("success", 0);
				resMap.put("info", "查询销售记录出错，请联系管理员");
				return "deleteProduct-success";
			}else if(count > 0){
				resMap.put("success", 0);
				resMap.put("info", "该产品已有出售，不可删除");
				return "deleteProduct-success";
			}else if(count == 0){
				//记录删除数据
				Product pro = new Product();
				pro = productService.findProductById(sale.getProductId());
				
				//删除
				pro.setProductId(sale.getProductId());
				productService.deleteProduct(pro);
				saleService.deleteProductRecord(sale);
				//删除日志
				User user = SessionUtil.getCurrenUser(request);
				OperationLog operationLog = new OperationLog();
				operationLog.setUserId(user.getUserId());
				operationLog.setOperation("删除了"+pro.getProductName()+"的信息");
				operationLogService.addOperationLog(operationLog);
				
				resMap.put("success", 1);
				resMap.put("info", "删除成功");
			}
		} catch (Exception e) {
			resMap.put("success", 0);
			resMap.put("info", "删除出错，请联系管理员");
			e.printStackTrace();
		}
		return "deleteProduct-success";
	}
	
	public String editProduct(){
		try {
			Product pro = new Product();
			pro = productService.findProductByName(product.getProductName());
			if(pro != null && pro.getProductId()!=0 && pro.getProductId()!=product.getProductId()){
					resMap.put("success", 0);
					resMap.put("info", "产品名称不可与已有产品重复");
					return "editProduct-success";
			}else{
				//记录编辑前数据
				pro = productService.findProductById(product.getProductId());
				
				//编辑
				productService.editProduct(product);
				
				//记录编辑日志
				String logText = "修改了"+pro.getProductName()+"的信息";
				if(!(product.getProductName().equals(pro.getProductName()))){
					logText += "，名字改为为"+product.getProductName();
				}
				if((int)product.getSock() != (int)pro.getSock()){
					logText += "，数量由"+pro.getSock()+"改为"+product.getSock();
				}
				if((int)product.getPrice() != (int)pro.getPrice()){
					logText += "，价格由"+pro.getPrice()+"改为"+product.getPrice();
				}
				if((int)product.getPrePrice() != (int)pro.getPrePrice()){
					logText += "，优惠价由"+pro.getPrePrice()+"改为"+product.getPrePrice();
				}
				User user = SessionUtil.getCurrenUser(request);
				OperationLog operationLog = new OperationLog();
				operationLog.setUserId(user.getUserId());
				operationLog.setOperation(logText);
				operationLogService.addOperationLog(operationLog);
				
				resMap.put("success", 1);
				resMap.put("info", "编辑成功");
			}
		} catch (Exception e) {
			resMap.put("success", 0);
			resMap.put("info", "编辑出错，请联系管理员");
			e.printStackTrace();
		}
		return "editProduct-success";
	}
	
	
	public Map<String, Object> getResMap() {
		return resMap;
	}

	public void setResMap(Map<String, Object> resMap) {
		this.resMap = resMap;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}
}
