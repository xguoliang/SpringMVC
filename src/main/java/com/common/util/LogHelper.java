package com.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class LogHelper{
	
	private final static Log log = LogFactory.getLog(LogHelper.class);
	
	public static void info(Object o){
		
		log.info(o);
	}
	
	public static void info(Object o,Throwable e){
		
		log.info(o,e);
	}
	
	public static void error(Object o){
		
		log.error(o);
	}
	
	public static void error(Object o,Throwable e){
		
		log.error(o,e);
	}

	public static void debug(Object o){
	
		log.debug(o);
	}
	
	public static void debug(Object o,Throwable e){
		
		log.debug(o,e);
	}
}
