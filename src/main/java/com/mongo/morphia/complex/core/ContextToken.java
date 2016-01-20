package com.mongo.morphia.complex.core;

/**
 * Copyright 1993-2010 Kingdee Software (China) Co., Ltd.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */





/**
 * 得到运行上下文的令牌。
 * 
 * <p>在 REST 服务中，此令牌在每次 REST 请求的 HTTP Header 中携带；
 * 在 RPC 风格的服务中，所有操作在参数中携带此令牌。
 *
 * @since 2010-5-26
 * @author pl
 */
public interface ContextToken {
    
    /**
     * @return 运行上下文
     */
    Context getContext();
    
    /**
     * 检查令牌是否过期
     * @throws ContextTokenExpiredException
     */
    void checkExpired() throws ContextTokenExpiredException;
}