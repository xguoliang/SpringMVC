/**
 * Copyright 1993-2011 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package com.mongo.morphia.complex.common;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @since 2011-6-16
 * @author daqiang_kang
 */
public class CollectionUtils {
    /**
     * @param <E>
     * @param list
     *            Can be null.
     * @param start
     * @param limit
     * @return This method Never returns null, but may return empty.
     */
    public static <E> List<E> subList(List<E> list, int start, int limit) {
        List<E> result = new ArrayList<E>();
        if (list == null) {
            return result;
        }

        start = Math.max(0, start);
        limit = Math.max(0, limit);

        int fromIndex = start;
        fromIndex = Math.min(list.size(), fromIndex);
        int toIndex = (limit > 0) ? (fromIndex + limit) : list.size();
        toIndex = Math.min(list.size(), toIndex);
        List<E> subList = list.subList(fromIndex, toIndex);

        if (subList != null && !subList.isEmpty()) {
            result.addAll(subList);
        }

        return result;
    }
    
    /**
     * @since 2013-08-09 删除 list中指定的元素
     * @author lian_lin
     * @param list 待删除list对象
     * @param removeIds 待删除元素
     */
	public static void removeElment(List<String> list, String removeIds) {

		String[] arrRemoveIds = removeIds.split(",");
		Iterator<String> iter = list.iterator();
		String tmp;
		while (iter.hasNext()) {
			tmp = iter.next();
			for (String tmpRemoveId : arrRemoveIds)
				if (tmp.equals(tmpRemoveId)) {
					iter.remove();
					break;
				}
		}
	}
	
	/**
	 * 
	 * @param list 
	 * @param target  目标值
	 * @return 从list中查找大于target的最小值，并返回该值
	 */
	public static int getTheLeastNumberBigThanTarget(List<Integer> list,int target){
		int select = 0;
		Collections.sort(list);
		if(target<=list.get(0))
			select = list.get(0);
		for(int i=1;i<list.size();i++){
			if(target>list.get(i-1)&&target<=list.get(i)){
				select = list.get(i);
				break;
			}
		}
		return select;
	}
}
