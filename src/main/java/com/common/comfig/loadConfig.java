package com.common.comfig;

import java.util.Properties;
//mongo.url=192.168.22.204
//mongo.port=guest
//mongo.dbname=guest
//mongo.connectionTimeOut=0
//mongo.user
//mongo.password
/**
 * 获取mongoDB的配置文件
 * @author Administrator
 *
 */

public class loadConfig {
	static Properties p= new Properties();
	static{
		String s = ClassLoaderUtil.getResource("").getPath();
		System.out.println(s);
		 p= ClassLoaderUtil.getProperties("config/mongoDB/mongoDB.properties");
	}
	public static String getMongoUrl(){
		return p.getProperty("mongo.url",null);
	}
	
	
	public static String getMongoPort(){
		return  p.getProperty("mongo.port","27017");
	}
	
	public static String getMongoDbname(){
		return p.getProperty("mongo.dbname",null);
	}
	
	public static String getMongoConnectionTimeOut(){
		return  p.getProperty("mongo.connectionTimeOut",null);
	}
	
	public static String getMongoUser(){
		return  p.getProperty("mongo.user",null);
	}
	
	public static String getMongoPassword(){
		return  p.getProperty("mongo.password",null);
	}
	
}
