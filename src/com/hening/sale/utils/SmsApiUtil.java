package com.hening.sale.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.cloopen.rest.sdk.CCPRestSDK;


@SuppressWarnings("unchecked")
public class SmsApiUtil {
	
	public static void sendSMSToPhone(String phone, Map<String, Object> paramMap)throws Exception{
    	HashMap<String, Object> result = null;
    	try{
    		CCPRestSDK restAPI = new CCPRestSDK();
    		restAPI.init("app.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
    		restAPI.setAccount("8a48b5514f2b46d0014f3985d9681204", "229a3596ba014857b683cae30b232635");// 初始化主帐号名称和主帐号令牌
    		restAPI.setAppId("8a216da85d158d1b015d5a5972291c19");// 初始化应用ID
    		result = restAPI.sendTemplateSMS( 
    				phone,
    				"192307" ,
    				new String[]{ paramMap.get("offlineMin")+""}
    				);
    		
    		if("000000".equals(result.get("statusCode"))){
    			//正常返回输出data包体信息（map）
    			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
    			Set<String> keySet = data.keySet();
    			for(String key:keySet){
    				Object object = data.get(key);
    				System.out.println(key +" = "+object);
    			}
    		}else{
    			//异常返回输出错误码和错误信息
    			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
    		}
    	}catch(Exception e){
    		throw new Exception( e.getMessage() );
    	}
    }
	
	
	public static void addProjMsgNotice(String phone, Map<String, Object> paramMap)throws Exception{
    	HashMap<String, Object> result = null;
    	try{
    		CCPRestSDK restAPI = new CCPRestSDK();
    		restAPI.init("app.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
    		restAPI.setAccount("8a48b5514f2b46d0014f3985d9681204", "229a3596ba014857b683cae30b232635");// 初始化主帐号名称和主帐号令牌
    		restAPI.setAppId("8aaf07085aabcbbd015ac5436257071c");// 初始化应用ID
    		result = restAPI.sendTemplateSMS( 
    				phone,
    				"161329" ,
    				new String[]{ paramMap.get("proj_name")+"", paramMap.get("proj_license_code")+""}
    				);
    		
    		if("000000".equals(result.get("statusCode"))){
    			//正常返回输出data包体信息（map）
    			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
    			Set<String> keySet = data.keySet();
    			for(String key:keySet){
    				Object object = data.get(key);
    				System.out.println(key +" = "+object);
    			}
    		}else{
    			//异常返回输出错误码和错误信息
    			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
    		}
    	}catch(Exception e){
    		throw new Exception( e.getMessage() );
    	}
    }
	
	public static void main(String[] args) {
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		paramMap.put("offlineMin", "10"); 
//		paramMap.put("backup_time", "2017年3月19日00:27:58"); 
//		paramMap.put("success_count", "5"); 
//		paramMap.put("error_count", "2"); 
		
		
		try {
			sendSMSToPhone("18511090052", paramMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
