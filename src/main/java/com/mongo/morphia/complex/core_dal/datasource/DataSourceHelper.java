/**
 * Copyright 1993-2010 Kingdee , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */


package com.mongo.morphia.complex.core_dal.datasource;

import com.mongo.morphia.complex.core.ContextToken;
import com.mongo.morphia.complex.core_dal.WARListener;



/**
 *
 * @since 2010-6-7
 * @author guolei_mao
 */
public class DataSourceHelper {
	public static DataSource getDataSource(ContextToken ct, String persistenceUnit) {
		PersistenceInfo pi = DataSourceConfigManager.getByContextToken(ct);
		PersistenceUnitInfo pui = pi.getByName(persistenceUnit);
		return pui.getDataSource();
	}
	
	/*
	 * @param beanType 实体类型
	 */
	public static DataSource getDataSource(ContextToken ct, Class<?> beanType) {
		return getDataSource(ct, WARListener.getPersistenceUnit(beanType));
	}

	/*
	 * @param bean 业务bean
	 */
	public static DataSource getDataSource(ContextToken ct, Object bean) {
		return getDataSource(ct, bean.getClass());
	}
}
