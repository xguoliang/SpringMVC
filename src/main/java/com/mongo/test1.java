package com.mongo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongo.singe.MongoDBClient;
import com.mongodb.MongoClient;

public class test1 {
	public static void main(String arg[]){
		test1.getData();
	}
	public static void getData(){
		String tableName="pubhtmlnums";
		String field="eid";
		String value="103";
		Map<String, Object> dataMap =  new HashMap<>();
		dataMap = MongoDBClient.getMapData(tableName, field, value)	;
//		Map NumsMap = new HashMap();
//		NumsMap.put("eid", "103");
//		NumsMap.put("openId","494041e2-8e39-47e4-b4bd-a0ed4541e38e");
//		NumsMap.put("htmlfilename","pZZZaczh.html");
//		NumsMap.put("read", "1");
//		List readListMe = MongoDBClient.getListData("pubhtmlnums", NumsMap);// 查询该用户的阅读次数
//		if (readListMe == null || readListMe.isEmpty()) {// 如果该用户没有该页面的阅读记录，则加进去，而且阅读数+1
//			System.out.println("读取数据成功！");
//		}
		for (String key :dataMap.keySet()){
			System.out.println(key);
			System.out.println(dataMap.get(key).toString());
		}
	}
}
