package com.mongo.morphia.complex.kingdee.common.consts;

/**
 * Copyright 1993-2010 Kingdee , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */



/**
 * 
 * @since 2010-5-28
 * @author guolei_mao
 */
public interface PersistenceUnitConstants {
	String PERSISTENCE = "persistence";
	String PERSISTENCE_UNIT = "persistence-unit";
	String DEFAULT_VALUE = "defaultValue";
	String PROPERTIES = "properties";
	String PROPERTY = "property";
	String NAME = "name";
	String VALUE = "value";

	String PREFIX = "DataSource_";
	
    String MF_ENTITY_PACKAGE = "Entity-Package";// old
    String MF_PERSISTENCE_PACKAGE = "Persistence-Package";
	String MF_PERSISTENCE_UNIT = "Persistence-Unit";
	
	String MONGODB = "mongodb";
	String URL = "url";
	String DBNAME = "dbname";
	String USER = "user";
	String PASSWORD = "password";
    String CONNECTIONS_PER_HOST = "connectionsPerHost";
    String THREADS_ALLOWED_TO_BLOCK_FOR_CONNECTION_MULTIPLIER = "threadsAllowedToBlockForConnectionMultiplier";
    String MAX_WAIT_TIME = "maxWaitTime";
    String CONNECT_TIMEOUT = "connectTimeout";
    String SOCKET_TIMEOUT = "socketTimeout";
    String AUTO_CONNECT_RETRY = "autoConnectRetry";
    String SOCKET_KEEPALIVE = "socketKeepAlive";
    
    String WRITE_CONCERN = "writeConcern";
    String READ_PREFERENCE = "readPreference";

	String TENANTIDS = "tenantIDs";
	String TENANTID = "tenantID";
}
