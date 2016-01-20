/**
 * Copyright 1993-2010 Kingdee Software (China) Co., Ltd.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package com.mongo.morphia.complex.core.impl;

import java.util.Map;

import com.mongo.morphia.complex.core.DynaBObject;



/**
 * {@code BObject }的实现
 * 
 * @since 2010-5-20
 * @author pl
 */
public class AbstractDynaBObject extends AbstractDynaBean implements DynaBObject {

	private static final long serialVersionUID = -8786299302915716158L;

	@com.google.code.morphia.annotations.Id
	private String id;

	private String tenantId;

	public AbstractDynaBObject() {
		super();
	}

	public AbstractDynaBObject(Map<String, Object> extMap) {
		super(extMap);
	}

	@Override
	public final String getId() {
		return id;
	}

	@Override
	public final void setId(String id) {
		this.id = id;
	}

	@Override
	public final String getTenantId() {
		return tenantId;
	}

	@Override
	public final void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
}