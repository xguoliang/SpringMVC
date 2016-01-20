/**
 * Copyright 1993-2010 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package com.mongo.morphia.complex.core_dal.impl.morphia;


import com.mongo.morphia.complex.core_dal.AggregationResult;
import com.mongodb.DBObject;

/**
 *
 * @since 2010-7-24
 * @author guolei_mao
 */
public class AggregationResultImpl implements AggregationResult {
    private DBObject dbo;

    public AggregationResultImpl(DBObject dbo) {
        this.dbo = dbo;
    }
    
    public void setDbo(DBObject dbo) {
        this.dbo = dbo;
    }

    public DBObject getDbo() {
        return dbo;
    }

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.AggregationResult#getCount()
     */
    @Override
    public long getCount() {
        return ((Double) dbo.get("count")).longValue();
    }

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.AggregationResult#getSum(java.lang.String)
     */
    @Override
    public Number getSum(String fieldName) {
        return (Number) dbo.get("sum_" + fieldName.trim());
    }

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.AggregationResult#getAvg(java.lang.String)
     */
    @Override
    public Number getAvg(String fieldName) {
        Number sum = getSum(fieldName);
        long  count = getCount();
        return Utils.avg(sum, (int) count);
    }

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.AggregationResult#getMax(java.lang.String)
     */
    @Override
    public Number getMax(String fieldName) {
        return (Number) dbo.get("max_" + fieldName.trim());
    }

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.AggregationResult#getMin(java.lang.String)
     */
    @Override
    public Number getMin(String fieldName) {
        return (Number) dbo.get("min_" + fieldName.trim());
    }

    @Override
    public Object getGroupBy(String fieldName) {
        return dbo.get(fieldName);
    }

}
