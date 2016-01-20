/**
 * Copyright 1993-2010 Kingdee Software (China) Co., Ltd.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package com.mongo.morphia.complex.core.impl;

import java.io.Serializable;

import com.mongo.morphia.complex.core.Context;



/**
 * 实现运行上下文接口。
 * 
 * @since 2010-5-26
 * @author pl
 */
public final class ContextImpl implements Context, Serializable {

    private static final long serialVersionUID = 5477712363317717102L;

    private final String userId;

    private final String tenantId;

    private String networkId;

    public ContextImpl(String userId, String tenantId) {
        this.userId = userId;
        this.tenantId = tenantId;
    }

    public ContextImpl(String userId, String tenantId, String networkId) {
        this.userId = userId;
        this.tenantId = tenantId;
        this.networkId = networkId;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }
}
