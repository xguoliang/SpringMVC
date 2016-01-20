/**
 * Copyright 1993-2010 Kingdee Software (China) Co., Ltd.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */


package com.mongo.morphia.complex.integer;

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
