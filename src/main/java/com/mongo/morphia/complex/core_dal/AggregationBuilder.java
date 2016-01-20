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
public interface AggregationBuilder<E> {
    
	Aggregation<E> createAggregation();
    
	Aggregation<E> createAggregation(String qlString);
}
