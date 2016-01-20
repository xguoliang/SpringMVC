/**
 * Copyright 1993-2010 Kingdee Software (China) Co., Ltd.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */


package com.mongo.morphia.complex.core;

/**
 * 表述cBOS对象的作用域。
 *
 * @since 2010-5-20
 * @author pl
 */

@DataAccessListener
public interface ObjectScope {
    
    /**
     * @return 租户标识，如果租户无关或未指定，返回{@code null}。
     */    
    String getTenantId();
    
    /**
     * 设置租户标识。
     * @param tenantId
     */
    void setTenantId(String tenantId);    
}