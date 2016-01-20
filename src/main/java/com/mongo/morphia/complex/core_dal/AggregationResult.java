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
public interface AggregationResult {	
	
	long getCount();
	
	Number getSum(String fieldName);
	
	Number getAvg(String fieldName);
	
	Number getMax(String fieldName);
	
	Number getMin(String fieldName);

    // added by mgl 2010-11-23
    Object getGroupBy(String fieldName);
}
