/**
 * Copyright 1993-2010 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */


package com.mongo.morphia.complex.core_dal.impl.morphia;


import com.mongo.morphia.complex.core_dal.Aggregation;
import com.mongo.morphia.complex.core_dal.AggregationBuilder;
import com.mongodb.DBCollection;

/**
 *
 * @since 2010-7-24
 * @author guolei_mao
 */
public class AggregationBuilderImpl<E> implements AggregationBuilder<E> {
    private MongoHelperImpl mongohelper;
    private DBCollection coll;
    private Class<E> clazz;
    
    public AggregationBuilderImpl(MongoHelperImpl mongohelper, DBCollection coll, Class<E> clazz) {
        this.mongohelper = mongohelper;
        this.coll = coll;
        this.clazz = clazz;
    }
    
    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.AggregationBuilder#createAggregation()
     */
    @Override
    public Aggregation<E> createAggregation() {
        return new AggregationImpl<E>(mongohelper, coll, clazz);
    }

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.AggregationBuilder#createAggregation(java.lang.String)
     */
    @Override
    public Aggregation<E> createAggregation(String qlString) {
        throw new UnsupportedOperationException();
    }

}
