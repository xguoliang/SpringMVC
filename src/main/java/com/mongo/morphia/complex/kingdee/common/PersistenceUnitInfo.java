package com.mongo.morphia.complex.kingdee.common;

/**
 * Copyright 1993-2010 Kingdee , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */



import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;





import com.mongo.morphia.complex.kingdee.core.dao.factory.DataSource;
import com.mongo.morphia.complex.kingdee.core.dao.factory.DataSourceImpl;
import com.mongo.morphia.complex.kingdee.morphia.MongoFactory;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;

/**
 * 
 * @since 2010-5-28
 * @author guolei_mao
 */
public class PersistenceUnitInfo implements Serializable{
    private static final long serialVersionUID = 6867271956530478229L;

//    protected List<PersistenceUnitProperty> persistenceUnitProperties = new ArrayList<PersistenceUnitProperty>();

    protected String persistenceUnitName;
    protected List<String> managedClassNames = new ArrayList<String>();
    protected URL persistenceUnitRootUrl;

    protected String mongoUrl;
    protected String mongoDbName;
    protected String mongoUser;
    protected String mongoPw;
    
    // MongoOptions 中参数
    protected int connectionsPerHost;
    protected int threadsAllowedToBlockForConnectionMultiplier;
    protected int maxWaitTime;
    protected int connectTimeout;
    protected int socketTimeout;
    protected boolean autoConnectRetry;
    protected boolean socketKeepAlive;
    private WriteConcern writeConcern;
    private ReadPreference readPreference;

    protected DataSource ds;

    public PersistenceUnitInfo() {
        super();
    }

    public String getPersistenceUnitName(){
        return persistenceUnitName;
    }
    
    public void setPersistenceUnitName(String persistenceUnitName){
        this.persistenceUnitName = persistenceUnitName;
    }
    
    public List<String> getManagedClassNames(){
        return managedClassNames;
    }

    public void setManagedClassNames(List<String> managedClassNames){
        this.managedClassNames = managedClassNames;
    }
    
    public URL getPersistenceUnitRootUrl(){
        return persistenceUnitRootUrl;
    }
    
    public void setPersistenceUnitRootUrl(URL persistenceUnitRootUrl){
        this.persistenceUnitRootUrl = persistenceUnitRootUrl;
    }

    public String getMongoUrl() {
        return mongoUrl;
    }

    public void setMongoUrl(String mongoUrl) {
        this.mongoUrl = mongoUrl;
    }

    public String getMongoDbName() {
        return mongoDbName;
    }

    public void setMongoDbName(String mongoDbName) {
        this.mongoDbName = mongoDbName;
    }

    public String getMongoUser() {
        return this.mongoUser;
    }

    public void setMongoUser(String mongoUser) {
        this.mongoUser = mongoUser;
    }

    public String getMongoPw() {
        return this.mongoPw;
    }

    public void setMongoPw(String pw) {
        this.mongoPw = pw;
    }

    public DataSource getDataSource() {
        if (ds == null) {
            ds = new DataSourceImpl(this);
        }
        return ds;
    }

    public void setConnectionsPerHost(int connectionsPerHost) {
        this.connectionsPerHost = connectionsPerHost;
        if (connectionsPerHost > MongoFactory.getMongoOptions(mongoUrl).connectionsPerHost) {
            MongoFactory.getMongoOptions(mongoUrl).connectionsPerHost = connectionsPerHost;
        }
    }

    public int getConnectionsPerHost() {
        return connectionsPerHost;
    }

    public void setThreadsAllowedToBlockForConnectionMultiplier(int threadsAllowedToBlockForConnectionMultiplier) {
        this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
        if (threadsAllowedToBlockForConnectionMultiplier > MongoFactory.getMongoOptions(mongoUrl).threadsAllowedToBlockForConnectionMultiplier) {
            MongoFactory.getMongoOptions(mongoUrl).threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
        }
    }

    public int getThreadsAllowedToBlockForConnectionMultiplier() {
        return threadsAllowedToBlockForConnectionMultiplier;
    }

    public void setMaxWaitTime(int maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
        MongoFactory.getMongoOptions(mongoUrl).maxWaitTime = maxWaitTime;
    }

    public int getMaxWaitTime() {
        return maxWaitTime;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        MongoFactory.getMongoOptions(mongoUrl).connectTimeout = connectTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
        MongoFactory.getMongoOptions(mongoUrl).socketTimeout = socketTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setAutoConnectRetry(boolean autoConnectRetry) {
        this.autoConnectRetry = autoConnectRetry;
        MongoFactory.getMongoOptions(mongoUrl).autoConnectRetry = autoConnectRetry;
    }

    public boolean isAutoConnectRetry() {
        return autoConnectRetry;
    }
    
    public void setSocketKeepAlive(boolean keepalive){
        socketKeepAlive = keepalive;
        MongoFactory.getMongoOptions(mongoUrl).socketKeepAlive = keepalive;
    }
    
    public boolean isSocketKeepAlive(){
        return socketKeepAlive;
    }

    public void setWriteConcern(WriteConcern writeConcern) {
        this.writeConcern = writeConcern;
        if (writeConcern != null) {
            MongoFactory.setWriteConcern(mongoUrl, mongoDbName, writeConcern);
        }
    }

    public WriteConcern getWriteConcern() {
        return writeConcern;
    }

    public void setReadPreference(ReadPreference readPreference) {
        this.readPreference = readPreference;
        if (readPreference != null) {
            MongoFactory.setReadPreference(mongoUrl, mongoDbName, readPreference);
        }
    }

    public ReadPreference getReadPreference() {
        return readPreference;
    }
}
