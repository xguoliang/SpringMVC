package com.mongo.morphia.ourtest;

import java.util.Map;

import com.mongodb.Mongo;

public class MongoFactoryimp {
	public static Map<String, Mongo> mongoMap;
	public Map<String,String> map ;
	
	/**
	 * 根据Url和DateBase获取MongoDB
	 * @param UrlDateBase
	 * @return
	 */
	public synchronized Mongo getMongo(String UrlDateBase) {
		if (mongoMap==null){
			init();
		}
		return mongoMap.get(UrlDateBase);
	}

	private void init(){
		
	}
	
	
}
