/**
 * Copyright 1993-2010 Kingdee Software (China) Co., Ltd.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */


package com.mongo.morphia.complex.core_dal;

/**
 *
 * @since 2010-5-20
 * @author pl
 */
public interface QueryCallback<E> {
    
    /*
     * @return true if more data should be fetched
     */
    boolean process(E item);
}
