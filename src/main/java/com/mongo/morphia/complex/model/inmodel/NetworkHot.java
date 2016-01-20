package com.mongo.morphia.complex.model.inmodel;
/**
 * Copyright 1993-2012 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */



import java.io.Serializable;

import com.google.code.morphia.annotations.Embedded;

/**
 * 
 * @since 2012-3-19
 * @author guolei_mao
 */
@Embedded
public class NetworkHot implements Serializable {
    private static final long serialVersionUID = 3697027249176841276L;
    /**
     * 本周热度
     */
    private long weekhot;

    public void setWeekhot(long weekhot) {
        this.weekhot = weekhot;
    }

    public long getWeekhot() {
        return weekhot;
    }
}
