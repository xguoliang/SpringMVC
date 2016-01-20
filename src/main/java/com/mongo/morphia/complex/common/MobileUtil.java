package com.mongo.morphia.complex.common;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @since 2013-11-18 手机号相关工具类
 * @author lian_lin
 *
 */
public class MobileUtil {

//	private static String mobileRegExp = "^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";  
	//添加香港电话及国际电话
	private static String mobileRegExp = "^((\\+86)?(1(([35][0-9])|(47)|(70)|[8][0123456789]))\\d{8}$)|((\\+(?!86))\\d+)$";
	
	public static boolean isMobile(String mobile){
       Pattern p = Pattern.compile(mobileRegExp);  
       Matcher m = p.matcher(mobile);  
       return m.find();//boolean  
	}
  
	
	public static void main(String[] args) {
		System.out.println(MobileUtil.isMobile("18676792836@qq.com"));
		System.out.println(MobileUtil.isMobile("13632755656"));
		System.out.println(MobileUtil.isMobile("15017908885"));
		System.out.println(MobileUtil.isMobile("+85298699598"));
		System.out.println(MobileUtil.isMobile("12345645678"));
		
	}
	
}
