/**
 * Copyright 1993-2010 Kingdee , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package com.mongo.morphia.complex.core_dal.datasource;

/**
 * 
 * @since 2010-6-7
 * @author guolei_mao
 */
public class DataSourceException extends RuntimeException {
    private static final long serialVersionUID = -1369970325855559482L;

    public DataSourceException() {
        super();
    }

    public DataSourceException(String message) {
        super(message);
    }

    public DataSourceException(Throwable cause) {
        super(cause);
    }

    public DataSourceException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
