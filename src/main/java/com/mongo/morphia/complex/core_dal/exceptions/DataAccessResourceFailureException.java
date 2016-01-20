/**
 * Copyright 1993-2010 Kingdee Software (China) Co., Ltd.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */


package com.mongo.morphia.complex.core_dal.exceptions;

/**
 * Data access exception thrown when a resource fails completely:
 * for example, if we can't connect to a mongoDB because of network
 * failure.
 *
 * @author Rod Johnson
 * @author Thomas Risberg
 * @author pl
 */
public class DataAccessResourceFailureException extends DataAccessException {

    private static final long serialVersionUID = 8376269921071192972L;

    public DataAccessResourceFailureException() {
        super();
    }

    public DataAccessResourceFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataAccessResourceFailureException(String message) {
        super(message);
    }

    public DataAccessResourceFailureException(Throwable cause) {
        super(cause);
    }
}
