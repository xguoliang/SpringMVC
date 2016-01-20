package com.mongo.morphia.complex.common;


import java.text.Collator;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

/**
 * @since 2013-06-28 集合排序工具类
 * @author lian_lin
 *
 */
public class ObjectComparator implements Comparator<Object> {
	
	private Map<String,String> orderMap;
	private ObjectComparator(Map<String,String> orderMap){
		this.orderMap=orderMap;
	}
	
	public static ObjectComparator instance(Map<String,String> orderMap){
		return new ObjectComparator(orderMap);
	}
	
	
	public int compare(Object o1, Object o2) {
		int flag=0;
		Set<String> keys=orderMap.keySet();
		
		for (String key : keys) {
			String order=orderMap.get(key);
			//如果无排序规则直接进入下一个条件

			if(!order.equalsIgnoreCase("asc") && !order.equalsIgnoreCase("desc")){continue;}
			String[] fs=key.split("XX");
			Object v1=o1;
			Object v2=o2;
			for (String f : fs) {
				if(v1==null || v2==null)break;
				v1=ReflectUtil.getAttributeValue(v1, f);//PT072279 bug  PBG092362 
				v2=ReflectUtil.getAttributeValue(v2, f);
			}
			
			if(v1==null || v2==null){
				if(v1==null && v2!=null)flag=-1;
				else if(v1!=null && v2==null)flag=1;
			}else{
				if(v1 instanceof String)flag=Collator.getInstance(java.util.Locale.CHINA).compare(v1,v2);
				else flag = ((Comparable)v1).compareTo(v2);
			}
			
			//多条件排序时如果相等才继续，否则直接返回
			if(flag!=0){
				if(order.equalsIgnoreCase("desc"))
					flag=flag<0?1:-1;
				break;
			}
		}
		
		return flag;
	}

}
