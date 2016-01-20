/**
 * Copyright 1993-2012 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */
package com.mongo.morphia.complex.core.impl;

import com.mongo.morphia.complex.core.EntityService;


/**
 * 
 * @since 2012-9-14
 * @author daqiang_kang
 */
public abstract class EntityServiceImpl<T extends AbstractDynaBObject> implements EntityService<T> {

    /*
     * (non-Javadoc)
     * 
     * @see com.kingdee.mail.service.BaseService#save(com.kingdee.cbos.core.framework.impl.
     * AbstractDynaBObject)
     */
    @Override
    public void save(T value) {
        if (value == null) {
            return;
        }

        this.validate(value);
        this.presaveProcess(value);

        String id = value.getId();
        id = (id == null) ? "" : id.trim();
        boolean exist = this.exist(id);
        if (exist) {
            this.update(value);
        } else {
            this.insert(value);
        }
    }

    abstract protected void insert(T value);

    protected void presaveProcess(T value) {
    }

    abstract protected void update(T value);
}
