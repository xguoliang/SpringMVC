/**
 * Copyright 1993-2012 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package com.mongo.morphia.complex.core;

import com.mongo.morphia.complex.core.impl.AbstractDynaBObject;



/**
 * 
 * @since 2012-9-14
 * @author daqiang_kang
 */
public interface EntityService<T extends AbstractDynaBObject> {
    /**
     * @param id
     *            Can be null or empty.
     * @return
     */
    boolean exist(String id);

    /**
     * @param value
     *            Can be null.
     */
    void save(T value);

    /**
     * @param value
     *            Can be null.
     */
    void validate(T value);
}
