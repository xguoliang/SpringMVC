/**
 * Copyright 1993-2010 Kingdee Software (China), Co. Ltd., All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */


package com.mongo.morphia.complex.core_dal;

/**
 *
 * @since 2010-7-22
 * @author pl
 */
public interface Aggregation<E> {
	
	/** 
	 * @param fieldName 支持 <code>"field1"</code> 及 <code>"field1, field2"</code>
	 */
	Aggregation<E> sum(String fieldName);
	
	Aggregation<E> count();
	
	/** 
	 * @param fieldName 支持 <code>"field1"</code> 及 <code>"field1, field2"</code>
	 */
	Aggregation<E> avg(String fieldName);
	
	/** 
	 * @param fieldName 支持 <code>"field1"</code> 及 <code>"field1, field2"</code>
	 */
	Aggregation<E> min(String fieldName);
	
	/** 
	 * @param fieldName 支持 <code>"field1"</code> 及 <code>"field1, field2"</code>
	 */
	Aggregation<E> max(String fieldName);
	
	/** 
	 * @param fieldName 支持 <code>"field1"</code> 及 <code>"field1, field2"</code>
	 */
	Aggregation<E> groupBy(String fieldName);
	
	/**
	 * @see CriteriaQuery#filter(String, Object)
	 */
	Aggregation<E> having(String condition, Object value);
	
	/**
	 * @see CriteriaQuery#field(String)
	 */
	CriteriaQueryField<E> having(String fieldName);		
		
	Aggregation<E> query(CriteriaQuery<E> query);
	
    /**
     * 对汇总结果排序
     * 
     * @param condition 例如-count(),max(field1)
     * @return
     */
    Aggregation<E> order(String condition);// added by mgl 2011-4-11
}