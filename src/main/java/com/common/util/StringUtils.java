package com.common.util;


public class StringUtils {

	public static boolean isEmpty(String str){
		
		return str == null || str.trim().length() == 0;
	}
	
	public static String getProperty(String key){
		
		return  PropertiesUtils.getInstance().getProperty(key);
	}
}
