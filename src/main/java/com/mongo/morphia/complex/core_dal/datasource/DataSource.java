package com.mongo.morphia.complex.core_dal.datasource;

/**
 * Copyright 1993-2010 Kingdee , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */



import com.mongodb.DB;
import com.mongodb.Mongo;

/**
 * 
 * @since 2010-6-7
 * @author guolei_mao
 */
public interface DataSource {
	/*
	 * 获取mongo
	 */
	Mongo getMongo() throws DataSourceException;

	DB getDB() throws DataSourceException;
}
