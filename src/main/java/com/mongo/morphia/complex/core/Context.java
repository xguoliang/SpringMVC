package com.mongo.morphia.complex.core;

/**
 * Copyright 1993-2010 Kingdee Software (China) Co., Ltd.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */




/**
 * 运行上下文
 *
 * @since 2010-5-26
 * @author pl
 */
public interface Context {

    /**
     * @return 当前用户
     */
    String getUserId();
    
    /**
     * @return 当前租户
     */
    String getTenantId();
    
    String getNetworkId();
}
