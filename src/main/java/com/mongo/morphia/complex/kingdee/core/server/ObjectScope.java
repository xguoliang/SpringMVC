package com.mongo.morphia.complex.kingdee.core.server;

import com.mongo.morphia.complex.kingdee.core.DataAccessListener;

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