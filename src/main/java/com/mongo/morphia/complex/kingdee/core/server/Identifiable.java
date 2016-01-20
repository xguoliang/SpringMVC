package com.mongo.morphia.complex.kingdee.core.server;

/**
 * 表述一个cBOS对象的唯一标识（多租户环境下）。
 * 
 * @since 2010-5-20
 * @author pl
 */
public interface Identifiable {
    
    String getId();
    
    void setId(String id);
}
