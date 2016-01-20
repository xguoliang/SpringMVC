package com.mongo.morphia.complex.core;

/**
 * Copyright 1993-2010 Kingdee Software (China) Co., Ltd.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */



/**
 * 上下文令牌已过期
 * 
 * @since 2010-5-26
 * @author pl
 */
public class ContextTokenExpiredException extends Exception {

	private static final long serialVersionUID = 6787320392587297283L;

	public ContextTokenExpiredException() {
		super();
	}

	public ContextTokenExpiredException(String message) {
		super(message);
	}
}
