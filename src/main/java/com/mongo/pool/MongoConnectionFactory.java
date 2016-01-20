package com.mongo.pool;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.comfig.loadConfig;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.MongoOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteResult;

public class MongoConnectionFactory {
	private static Logger Log = LoggerFactory.getLogger(MongoConnectionFactory.class);
	private static MongoClient mongoClient = null;
	private static String host = "192.168.";
	private static String port = "27107";
	private static String dbName = "";
	private static String username = "";
	private static String password = "";
	private static int poolSize = 100;// 线程池的连接数量 （最新版本是100，老版本10个）
	private static int blockSize = 10; // 等待队列长度 默认值
	private static int MaxWaitTime=30000;//一个线程的等待可用的连接的最大等待时间。
	private static int timeoutMS=30000;//线程池的超时连接
	private static boolean retry=true;//=true时，,当服务器的套接字无法建立时，驱动将保持与同一个服务器的连接
	@SuppressWarnings("deprecation")
	public static DB getDB() throws UnknownHostException {
		DB conn = null;
		if (mongoClient == null) {
			try {
				intializeMongoClient();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.error("monggodb初始化数据库连接出错!出错如下：{0}",e);
				e.printStackTrace();
			}

		}
		conn = mongoClient.getDB(dbName);
//		conn.authenticate(username, password.toCharArray());
		return conn;

	}

	private static void intializeMongoClient() throws Exception {
		try {
			mongoClient = new MongoClient(host, Integer.parseInt(port));
			MongoOptions opt = mongoClient.getMongoOptions();
//			opt.setAutoConnectRetry(retry);//opt.autoConnectRetry=true;
//			opt.setConnectionsPerHost(poolSize);// 连接数量
//			opt.setMaxWaitTime(MaxWaitTime);//
//			opt.setThreadsAllowedToBlockForConnectionMultiplier(blockSize);//// 等待队列长度 
//			opt.setConnectTimeout(timeoutMS);
			System.out.println(opt.getConnectTimeout());//10000
			System.out.println(opt.getConnectionsPerHost());//100连接数量
			System.out.println(opt.getMaxWaitTime());//120000
			System.out.println(opt.getThreadsAllowedToBlockForConnectionMultiplier());//5
			System.out.println(opt.connectTimeout);//10000
			opt.setConnectionsPerHost(poolSize);// 连接数量
			System.out.println(opt.getConnectionsPerHost());
			opt.setConnectTimeout(50000);
			System.out.println("test");
		} catch (UnknownHostException e) {
			Log.error("monggodb初始化数据库连接host{0}出错!出错如下：{1}",host,e);
			// log error
		} catch (MongoException e) {
			// log error
			Log.error("monggodb初始化数据库连接出错!出错如下：{0}",e);
		}catch( Exception e){
			Log.error("monggodb初始化数据库连接出错!出错如下：{0}",e);
		}
		Log.info("初始化数据库monggodb连接，参数如下：host：{0}，port：{1}，poolSize：{2}，blockSize：{3}，");
	}

	public static synchronized void closeConnection() {

		if (mongoClient != null) {
			mongoClient.close();
		}
	}
	
	public static void  main(String arg[]){
		MongoConnectionFactory.host=loadConfig.getMongoUrl();
		MongoConnectionFactory.port = loadConfig.getMongoPort();
		MongoConnectionFactory.dbName=loadConfig.getMongoDbname();
		try {
			DB monggoDB=MongoConnectionFactory.getDB();
			System.out.println("连接monggo数据成功！");
			if (monggoDB!=null){
				Map<String,Object> map= new HashMap<>();
				//无该句，默认插入数据："_id": ObjectId("5630b5abfdba64d55026ac08"),有的话，则以该“_id”作为_id
				//且不能有重复值
				map.put("_id", "12345");
				//"id"相当于一个普通字段
				map.put("id", "12345");
				map.put("test1", "test1");
				map.put("test2", "test2");
				DBCollection collection = monggoDB.getCollection("test1");
				BasicDBObject searchObject = new BasicDBObject();
				if (map != null && !map.isEmpty()) {
					String field;
					Object value;
					for (Entry<String, Object> entry : map.entrySet()) {
						field = (String) entry.getKey();
						value = entry.getValue();
						searchObject.put(field, value);
					}
				}
				WriteResult result = collection.insert(searchObject);
				System.out.println(result); 
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	///[二]
	// 方式三：连接到mongodb服务器集群（会自动发现主服务器）
	/**
	 * MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27017),
	                                      new ServerAddress("localhost", 27018),
	                                      new ServerAddress("localhost", 27019)));
		 */                                      
	/**
	 * 支持多个host及port的 连接池
	 * 
     * @Description: mongo连接池初始化
     */
    private static void init() {
    try {
    	String[] host = {"127.0.0.1"};
        int[] port = {27001};
        //服务列表
        List<ServerAddress> replicaSetSeeds = new ArrayList<ServerAddress>();
        for (int i = 0; i < host.length; i++) {
            replicaSetSeeds.add(new ServerAddress(host[i], port[i]));
        }
        //连接池参数设置
        MongoOptions options = new MongoOptions();
        options.connectionsPerHost =100;
        options.threadsAllowedToBlockForConnectionMultiplier = 5;
        Mongo mongo2 = new Mongo(replicaSetSeeds, options);
        //从服务器可读
        mongo2.setReadPreference(ReadPreference.SECONDARY);
    } catch (Exception e){
        e.printStackTrace();
    }
}

}
