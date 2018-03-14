package com.hening.sale.common.base;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.hening.sale.common.constant.Constants;

public class SystemServlet extends HttpServlet{

	
	 /**
	 *
	 *@field serialVersionUID:TODO(description here)
	 *@author (作者):mf-luozg
	 *
	 */
	 
	private static final long serialVersionUID = 1L;
	
//	private static DictionaryService dictionaryService =
//			(DictionaryService) SpringContextHolder.getBean(DictionaryService.class);

	
	@Override
	public void init() throws ServletException {
		try {
			
			Properties config = new Properties();
			InputStream inStream = new BufferedInputStream(
					SystemServlet.class.getClassLoader().getResourceAsStream("properties/fileter.properties"));
			config.load(inStream);
			String urlConfig = config.getProperty("anonymous");
			//初始化 拦截器  不拦截的路径
			for (String url : urlConfig.split(",")) {
				Constants.URL_LIST.add( url.trim() );
			}
			
			// 初始化 数据字典
			//dictionaryService.loadingDictionaryData();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
