package com.mongo.morphia.complex.core_dal.entity;

/**
 * Copyright 1993-2012 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */



import java.util.Date;

import com.mongo.morphia.complex.core.impl.AbstractDynaBObject;



/**
 * 
 * @since 2012-7-2
 * @author guolei_mao
 */
public class LongQuery extends AbstractDynaBObject {
    private static final long serialVersionUID = -5855278701032034769L;
    private String url;
    private String readPreference;
    private String collName;
    private String query;
    private Date start;
    private long cost;
    private String stackTrace;

    public LongQuery() {

    }

    public LongQuery(String url, String readPreference, String collName, String query, Date start, long cost,
            String stackTrace) {
        this.url = url;
        this.readPreference = readPreference;
        this.collName = collName;
        this.query = query;
        this.start = start;
        this.cost = cost;
        this.stackTrace = stackTrace;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setReadPreference(String readPreference) {
        this.readPreference = readPreference;
    }

    public String getReadPreference() {
        return readPreference;
    }

    public void setCollName(String collName) {
        this.collName = collName;
    }

    public String getCollName() {
        return collName;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getStart() {
        return start;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public long getCost() {
        return cost;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getStackTrace() {
        return stackTrace;
    }
}
