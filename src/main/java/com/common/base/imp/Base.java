package com.common.base.imp;

import java.util.Map;

import com.common.base.IBase;
import com.common.util.LogHelper;



public class Base implements IBase{
	
	public void logError(Object error,Object errorCode,Object message){
		
		LogHelper.error(error+"["+errorCode+"/"+message+"]"+this.getClass().getName());
	}
	
	public void logError(Object obj,Throwable e){
		LogHelper.error(obj+this.getClass().getName(),e);
	}
	
	public void logInfo(Object obj){
		LogHelper.info(obj+this.getClass().getName());
	}

	public void addMessage(String error,int errorCode,String message,
			Map<String,Object> map)throws Exception{
		
		map.put("error",error);
		map.put("errorCode", errorCode);
		LogHelper.info(error+"["+errorCode+"/"+message+"]"+this.getClass().getName());
	}
	
	public void addError(String error,int errorCode,String message,
			Map<String,Object> map)throws Exception{
		map.put("success",false);
		map.put("error",error);
		map.put("errorCode", errorCode);
		LogHelper.error(error+"["+errorCode+"/"+message+"]"+this.getClass().getName());
	}
}
