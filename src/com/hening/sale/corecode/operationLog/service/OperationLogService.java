package com.hening.sale.corecode.operationLog.service;

import java.util.List;
import java.util.Map;

import com.hening.sale.corecode.operationLog.entity.OperationLog;

public interface OperationLogService {
	
	void addOperationLog(OperationLog operationLog);
	
	List<Map<String,Object>> findOperationLogList(OperationLog operationLog);
}
