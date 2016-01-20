package com.mongo.morphia.ourtest;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.mongodb.DB;
import com.mongodb.Mongo;

public class mogoFactory {
    private static ConcurrentMap<String, Mongo> mongoMap = new ConcurrentHashMap<String, Mongo>();
    private static ConcurrentMap<String, DB> dbMap = new ConcurrentHashMap<String, DB>();
    public Mongo getMongo(String key){
    	Mongo mongo = mongoMap.get(key);
    	if (mongo==null){
//    		 mongo = new Mongo();
    	}
    	return mongo;
    }
}
