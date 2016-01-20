package com.mongo.morphia.ourtest;

import java.lang.reflect.Field;

public class until {
	//基本类型的String的转换
	public static  String tostring(Object object){
		Field fields[] = object.getClass().getFields();
//		Class clazz=Class.forName(object.getClass().getName());
		StringBuffer temp = null;
		for(int i=0;i<fields.length;i++){
			temp.append(fields[i].toString());
		}
		return temp.toString();
	}
}
