package com.mongo.morphia.complex.common;

import java.util.Locale;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @since 2014-03-05 spring业务类生成，主要应用非spring上下文中获取spring业务类
 * @author lian_lin
 *
 */
public class SpringBeanFactory {

	private static WebApplicationContext wac = null;
	
	public static Object getBean(ServletContext sc,String beanName){
		if(wac==null){
			wac = WebApplicationContextUtils.getWebApplicationContext(sc);
			Locale.setDefault(Locale.CHINA);
		}
		return wac.getBean(beanName);
	}
}
	
