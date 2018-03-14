package com.hening.sale.corecode.operationLog.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hening.sale.common.base.BaseAction;
import com.hening.sale.corecode.login.entity.User;
import com.hening.sale.corecode.operationLog.entity.OperationLog;
import com.hening.sale.corecode.operationLog.service.OperationLogService;
import com.hening.sale.utils.SessionUtil;

@SuppressWarnings("serial")
public class OperationLogAction extends BaseAction{
	
	private Map<String,Object> resMap = new HashMap<String,Object>();
	private OperationLog oplog = new OperationLog();
	
	@Autowired
	private OperationLogService operationLogService;

	public String operationLog(){
		User user = SessionUtil.getCurrenUser(request);
		resMap.put("user", user);
		return "operationLog-success";
	}
	
	public String findOperationLogList(){
		try {
			List<Map<String,Object>> operationlogs = operationLogService.findOperationLogList(oplog);
			resMap.put("list", operationlogs);
			resMap.put("success", 1);
		} catch (Exception e) {
			resMap.put("info", "查询出错");
			resMap.put("success", 0);
			e.printStackTrace();
		}
		return "findOperationLogList-success";
	}
	
	public Map<String, Object> getResMap() {
		return resMap;
	}

	public void setResMap(Map<String, Object> resMap) {
		this.resMap = resMap;
	}

	public OperationLog getOplog() {
		return oplog;
	}

	public void setOplog(OperationLog oplog) {
		this.oplog = oplog;
	}
}
