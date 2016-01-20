/**
 * Copyright 1993-2010 Kingdee Software (China) Co., Ltd.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package com.mongo.morphia.complex.core.impl;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import com.mongo.morphia.complex.core.Context;
import com.mongo.morphia.complex.core.ContextToken;
import com.mongo.morphia.complex.core.ContextTokenExpiredException;


/**
 * 上下文令牌的简单实现：将上下文及令牌相关信息在调用中全部传递，远程调用中使用序列化机制
 * 
 * @since 2010-5-26
 * @author pl
 */
public final class SerializedContextToken implements ContextToken, Serializable {

	private static final long serialVersionUID = -8884776560747805L;

	private final Context ctx;

	private final long created;

	private long expiredMillis;

	public static final long DEFAULT_EXPIRED_MILLIS = Long.MAX_VALUE;

	/**
	 * 无过期时间
	 * 
	 * @param ctx
	 *            运行上下文
	 */
	public SerializedContextToken(Context ctx) {
		this(ctx, DEFAULT_EXPIRED_MILLIS);
	}

	/**
	 * @param ctx
	 *            运行上下文
	 * @param expiredDuration
	 *            有效时间
	 */
	public SerializedContextToken(Context ctx, Duration expiredDuration) {
		this(ctx, expiredDuration.getMillis());
	}

	/**
	 * @param ctx
	 *            运行上下文
	 * @param expiredMillis
	 *            有效时间（以毫秒计）
	 */
	public SerializedContextToken(Context ctx, long expiredMillis) {
		this.ctx = new ContextImpl(ctx.getUserId(), ctx.getTenantId(), ctx.getNetworkId());
		created = new DateTime().getMillis();
		this.expiredMillis = expiredMillis;
	}

	@Override
	public Context getContext() {
		return ctx;
	}

	public long getExpiredMillis() {
		return expiredMillis;
	}

	public void setExpiredMillis(long expiredMillis) {
		this.expiredMillis = expiredMillis;
	}

	public Duration getExpiredDuration() {
		return new Duration(expiredMillis);
	}

	public void setExpiredMillis(Duration expiredDuration) {
		this.expiredMillis = expiredDuration.getMillis();
	}

	@Override
	public void checkExpired() throws ContextTokenExpiredException {
		if (created + expiredMillis > new DateTime().getMillis())
			throw new ContextTokenExpiredException();
	}
}