/**
 * Copyright 1993-2010 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */
package com.mongo.morphia.complex.core_dal.query;

import com.mongo.morphia.complex.core.BObject;
import com.mongo.morphia.complex.core_dal.CriteriaQueryField;




/**
 *
 * @since 2010-6-29
 * @author guolei_mao
 */
public class CommonQueryField<T extends BObject> {
    private CommonQuery<T> cq;
    private CriteriaQueryField<T> cqf;

    public CommonQueryField(CommonQuery<T> cq, CriteriaQueryField<T> cqf) {
        this.cq = cq;
        this.cqf = cqf;
    }

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.CriteriaQueryField#exists()
     */
    public CommonQuery<T> exists() {
        cqf.exists();
        return cq;
    }

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.CriteriaQueryField#doesNotExist()
     */
    public CommonQuery<T> doesNotExist() {
        cqf.doesNotExist();
        return cq;
    }

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.CriteriaQueryField#greaterThan(java.lang.Object)
     */
    public CommonQuery<T> greaterThan(Object val) {
        cqf.greaterThan(val);
        return cq;
    }

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.CriteriaQueryField#greaterThanOrEq(java.lang.Object)
     */
    public CommonQuery<T> greaterThanOrEq(Object val) {
        cqf.greaterThanOrEq(val);
        return cq;
    }

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.CriteriaQueryField#lessThan(java.lang.Object)
     */
    public CommonQuery<T> lessThan(Object val) {
        cqf.lessThan(val);
        return cq;
    }

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.CriteriaQueryField#lessThanOrEq(java.lang.Object)
     */
    public CommonQuery<T> lessThanOrEq(Object val) {
        cqf.lessThanOrEq(val);
        return cq;
    }

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.CriteriaQueryField#equal(java.lang.Object)
     */
    public CommonQuery<T> equal(Object val) {
        cqf.equal(val);
        return cq;
    }

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.CriteriaQueryField#notEqual(java.lang.Object)
     */
//    public CommonQuery<T> notEqual(Object val) {
//        cqf.notEqual(val);
//        return cq;
//    }

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.CriteriaQueryField#hasThisOne(java.lang.Object)
     */
    public CommonQuery<T> hasThisOne(Object val) {
        cqf.hasThisOne(val);
        return cq;
    }

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.CriteriaQueryField#hasAllOf(java.lang.Iterable)
     */
    public CommonQuery<T> hasAllOf(Iterable<?> vals) {
        cqf.hasAllOf(vals);
        return cq;
    }

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.CriteriaQueryField#hasAnyOf(java.lang.Iterable)
     */
    public CommonQuery<T> hasAnyOf(Iterable<?> vals) {
        cqf.hasAnyOf(vals);
        return cq;
    }

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.CriteriaQueryField#hasNoneOf(java.lang.Iterable)
     */
    public CommonQuery<T> hasNoneOf(Iterable<?> vals) {
        cqf.hasNoneOf(vals);
        return cq;
    }

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.CriteriaQueryField#hasThisElement(java.lang.Object)
     */
    public CommonQuery<T> hasThisElement(Object val) {
        cqf.hasThisElement(val);
        return cq;
    }

}
