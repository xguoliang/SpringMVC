package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GsonTest {

	public static void main(String[] args) {
		Map map = new HashMap();
		map.put("name", "nam1");
		map.put("age", "12");

		List list = new ArrayList();
		list.add("name");
		list.add("123");
		list.add(map);

		// json格式的字符串
		String str = "[{'name':'kevin','age':25},{'name':'cissy','age':24}]";
		// 创建一个Gson对象
		Gson gson = new Gson();

		// 1：把map对象转换成Json字符串。
		String jsonStr = gson.toJson(map);
		System.out.println(jsonStr);

		// 2：把集合对象转换成Json字符串
		jsonStr = gson.toJson(list);
		System.out.println(jsonStr);

		
		//3:json字符串转为java对象list,map，必须先知道json的数据结构
		List<Object> temp = gson.fromJson(str,List.class);
		Map<String,Object> maptemp =(Map<String, Object>) temp.get(0);
		for(String key :maptemp.keySet())
		{
			System.out.println(key);
			System.out.println(maptemp.get(key));
		}
		
		//4json字符串转为java对象，
		// 创建一个JsonParser
		JsonParser parser = new JsonParser();

		// 通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
		JsonElement el = parser.parse(str);

		// 把JsonElement对象转换成JsonObject
		JsonObject jsonObj = null;
		if (el.isJsonObject()) {
			jsonObj = el.getAsJsonObject();
		}

		// 把JsonElement对象转换成JsonArray
		JsonArray jsonArray = null;
		if (el.isJsonArray()) {
			jsonArray = el.getAsJsonArray();
		}

		// 遍历JsonArray对象
		MyField field = null;
		Iterator it = jsonArray.iterator();
		while (it.hasNext()) {
			JsonElement e = (JsonElement) it.next();
			// JsonElement转换为JavaBean对象
			field = gson.fromJson(e, MyField.class);
			System.out.println(field.getName() + " === " + field.getAge());
		}
	}

}

class MyField {
	private String name;
	private Integer age;

	public MyField() {
	}

	public MyField(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
