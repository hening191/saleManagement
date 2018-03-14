package com.hening.sale.corecode.operationLog.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hening.sale.corecode.operationLog.entity.OperationLog;

public interface OperationLogMapper {

	void addOperationLog(@Param("oplog")OperationLog operationLog);
	
	List<Map<String,Object>> findOperationLogList(@Param("oplog")OperationLog operationLog);
}
