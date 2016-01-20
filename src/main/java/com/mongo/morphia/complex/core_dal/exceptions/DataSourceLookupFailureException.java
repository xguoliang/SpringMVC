/**
 * Copyright 1993-2010 Kingdee Software (China) Co., Ltd.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */


package com.mongo.morphia.complex.core_dal.exceptions;

/**
 * Exception to be thrown by a DataSourceLookup implementation,
 * indicating that the specified DataSource could not be obtained.
 *
 * @author Juergen Hoeller
 * @since 2010-6-13
 */
public class DataSourceLookupFailureException extends DataAccessException {

    private static final long serialVersionUID = 8376269921071192972L;

    public DataSourceLookupFailureException() {
        super();
    }

    public DataSourceLookupFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataSourceLookupFailureException(String message) {
        super(message);
    }

    public DataSourceLookupFailureException(Throwable cause) {
        super(cause);
    }
}
