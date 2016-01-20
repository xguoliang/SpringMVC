package com.mongo.morphia.complex.kingdee.morphia.integer;

/**
 * Copyright 1993-2010 Kingdee Software (China) Co., Ltd.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */




/**
 *
 * @since 2010-5-27
 * @author pl
 */
public interface CriteriaQueryBuilder<E> {
    
    CriteriaQuery<E> createCriteriaQuery();
    
    CriteriaQuery<E> createCriteriaQuery(String qlString);
}
