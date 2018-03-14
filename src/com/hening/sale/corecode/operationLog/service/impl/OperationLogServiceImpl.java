package com.hening.sale.corecode.operationLog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hening.sale.corecode.operationLog.entity.OperationLog;
import com.hening.sale.corecode.operationLog.mapper.OperationLogMapper;
import com.hening.sale.corecode.operationLog.service.OperationLogService;

@Service("OperationLogService")
public class OperationLogServiceImpl implements OperationLogService {

	@Autowired
	private OperationLogMapper operationMapper;
	
	@Override
	public void addOperationLog(OperationLog operationLog) {
		try {
			operationMapper.addOperationLog(operationLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Map<String, Object>> findOperationLogList(OperationLog operationLog) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = operationMapper.findOperationLogList(operationLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
}
