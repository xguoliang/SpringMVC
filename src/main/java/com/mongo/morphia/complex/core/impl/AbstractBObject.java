package com.mongo.morphia.complex.core.impl;

/**
 * Copyright 1993-2010 Kingdee Software (China) Co., Ltd.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */



import java.io.Serializable;

import com.mongo.morphia.complex.core.BObject;



/**
 * 
 * @since 2010-6-10
 * @author pl
 */
public abstract class AbstractBObject implements BObject, Serializable {

	private static final long serialVersionUID = 1926071137525387427L;

	@com.google.code.morphia.annotations.Id
	private String id;

	private String tenantId;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getTenantId() {
		return tenantId;
	}

	@Override
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
}
