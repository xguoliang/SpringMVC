package com.mongo.singe;

import com.common.comfig.loadConfig;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteResult;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class MongoDBClient {
	// private static Logger logger =
	// LoggerFactory.getLogger(MongoDBClient.class);
	private static MongoClient client = null;
	private static GridFS fs = null;
	private static Mongo mongo;
	private static DB mogodb;

	public static DB getMogodb() {
		if (mogodb != null) {
			return mogodb;
		} else {

			// Mongo mongo;
			try {

				String mongoHost = loadConfig.getMongoUrl();
				String mongoPort = loadConfig.getMongoPort();

				String mongoDbName = loadConfig.getMongoDbname();
				if (mongoDbName.isEmpty()) {
					mongoDbName = "pubacc";
				}
				String connectionTimeOut = loadConfig
						.getMongoConnectionTimeOut();

				String mongoUser = loadConfig.getMongoUser();
				String mongoPassword = loadConfig.getMongoPassword();

				if (StringUtils.isNotEmpty(mongoHost)) {

					client = new MongoClient(mongoHost,
							Integer.parseInt(mongoPort));
					// client=new MongoClient(new MongoClientURI(mongoHost));
					mogodb = client.getDB(mongoDbName);

					// MongoClientOptions options =
					// client.getMongoClientOptions();
					//
					// com.mongodb.MongoClientOptions.Builder builder = options
					// .builder();
					// if (connectionTimeOut != null &&
					// !connectionTimeOut.isEmpty()) {
					// builder.connectTimeout(Integer.parseInt(connectionTimeOut));
					// }

					boolean authSucc = true;
					if (StringUtils.isNotEmpty(mongoUser)
							&& StringUtils.isNotEmpty(mongoPassword)) {
						authSucc = mogodb.authenticate(mongoUser,
								mongoPassword.toCharArray());
					}
					if (authSucc) {
						fs = new GridFS(mogodb);
						// logger.info("mongodb:{}:{}/{}", mongoHost, mongoPort,
						// mongoDbName);
					} else {
						fs = null;
						// logger.warn("mongodb:{}:{}/{} authenticate fail [{}]/[{}]",
						// mongoHost, mongoPort, mongoDbName, mongoUser,
						// mongoPassword);
					}

				} else {
					// logger.info("mongodb:{}:{}/{}", mongoHost, mongoPort,
					// mongoDbName);
				}
			} catch (UnknownHostException e) {
				// logger.info("获取mongo失败！");
				// logger.error("", e);
				e.printStackTrace();
			} catch (Exception e) {
				// logger.error("连接mongo数据库出错：", e);
				e.printStackTrace();
			}
		}
		return mogodb;
	}

	public static DB getDB() {
		return mogodb;
	}

	public static boolean isEnable() {
		return fs != null;
	}

	public static DBCursor getCursor(DB conn, String tableName, String field,
			Object value) {
		DBCollection collection = conn.getCollection(tableName);
		BasicDBObject searchObject = new BasicDBObject();
		if (!"".equals(field) && value != null) {
			searchObject.put(field, value);
		}
		DBCursor cursor = collection.find(searchObject);
		return cursor;
	}

	public static DBCursor getCursor(DB conn, String tableName,
			Map<String, Object> map) {
		DBCollection collection = conn.getCollection(tableName);
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
		DBCursor cursor = collection.find(searchObject);
		return cursor;
	}

	public static WriteResult insert(String tableName, Map<String, Object> map) {
		DBCollection collection = getMogodb().getCollection(tableName);
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
		return result;
	}

	public static Map<String, Object> getMapData(String tableName,
			String field, Object value) {
		Map<String, Object> map = new HashMap<String, Object>();
		DBCursor cursor = null;
		try {
			cursor = getCursor(getMogodb(), tableName, field, value);
			while (cursor.hasNext()) {
				map = (Map) cursor.next();
			}
		} catch (Exception e) {
			// logger.info("从mongo取{}数据失败！", tableName);
			// logger.error("", e);
			e.printStackTrace();
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (mongo != null) {
				mongo.close();
			}
		}
		return map;
	}

	public static WriteResult update(String tableName,
			Map<String, Object> mapSel, Map<String, Object> mapValue) {
		WriteResult result = null;
		if (mapSel != null && !mapSel.isEmpty() && mapValue != null
				&& !mapValue.isEmpty()) {
			DBCollection collection = getMogodb().getCollection(tableName);
			BasicDBObject searchObject = new BasicDBObject();
			BasicDBObject setObject = new BasicDBObject();
			BasicDBObject valueObject = new BasicDBObject();
			String field;
			Object value;
			for (Entry<String, Object> entry : mapSel.entrySet()) {
				field = (String) entry.getKey();
				value = entry.getValue();
				searchObject.put(field, value);
			}
			for (Entry<String, Object> entry : mapValue.entrySet()) {
				field = (String) entry.getKey();
				value = entry.getValue();
				valueObject.put(field, value);
			}
			setObject.append("$set", valueObject);// 更新某个字段值
			result = collection.update(searchObject, setObject);
		}

		return result;
	}

	public static void del(String tableName, Map<String, Object> mapSel) {
		if (mapSel != null && !mapSel.isEmpty()) {
			DBCollection collection = getMogodb().getCollection(tableName);
			BasicDBObject searchObject = new BasicDBObject();
			String field;
			Object value;
			for (Entry<String, Object> entry : mapSel.entrySet()) {
				field = (String) entry.getKey();
				value = entry.getValue();
				searchObject.put(field, value);
			}
			collection.remove(searchObject);
		}
	}

	public static Map<String, Object> getMapData(String tableName,
			Map<String, Object> mapSel) {
		Map<String, Object> map = new HashMap<String, Object>();
		DBCursor cursor = null;
		try {
			cursor = getCursor(getMogodb(), tableName, mapSel);
			while (cursor.hasNext()) {
				map = (Map) cursor.next();
			}
		} catch (Exception e) {
			// logger.info("从mongo取{}数据失败！", tableName);
			// logger.error("", e);
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (mongo != null) {
				mongo.close();
			}
		}
		return map;
	}

	public static List<Map<String, Object>> getListData(String tableName,
			String field, Object value) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		DBCursor cursor = null;
		try {

			cursor = getCursor(getMogodb(), tableName, field, value);
			while (cursor.hasNext()) {
				Map<String, Object> map = (Map) cursor.next();
				list.add(map);
			}
			cursor.close();
		} catch (Exception e) {
			// logger.info("从mongo取{}数据失败！", tableName);
			// logger.error("", e);
			e.printStackTrace();
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (mongo != null) {
				mongo.close();
			}
		}
		return list;
	}

	public static List<Map<String, Object>> getListData(String tableName,
			Map<String, Object> mapSel) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		DBCursor cursor = null;
		try {
			cursor = getCursor(getMogodb(), tableName, mapSel);
			while (cursor.hasNext()) {
				Map<String, Object> map = (Map) cursor.next();
				list.add(map);
			}
			cursor.close();
		} catch (Exception e) {
			// logger.info("从mongo取{}数据失败！", tableName);
			// logger.error("", e);
			e.printStackTrace();
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (mongo != null) {
				mongo.close();
			}
		}
		return list;
	}
}