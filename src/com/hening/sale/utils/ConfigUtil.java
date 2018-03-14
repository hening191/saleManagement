package com.hening.sale.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;


public class ConfigUtil {
	
	private static Properties props = new Properties();
	
	static {
		ClassLoader loader = ConfigUtil.class.getClassLoader();
		InputStream ips = loader.getResourceAsStream("properties/config.properties");
		try {
			props.load(ips);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public static String getValue(String key) {
		return props.getProperty(key);
	}
	
	public static Properties getProperties(String fileName){
		InputStreamReader is=null;
		try{
			is=new InputStreamReader(ConfigUtil.class.getResourceAsStream(fileName), "UTF-8");
		}catch(Exception e){
			throw new RuntimeException("找不到相应的文件！"+fileName);
		}
		
		Properties properties=new Properties();
		try {
			properties.load(is);
			if(is!=null)is.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return properties;
	}
	
	public static void main(String[] args) {
		String str= ConfigUtil.getValue("system.file.upordownload.type");
		System.out.println(str);
	}

}
