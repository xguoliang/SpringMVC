/**
 * Copyright 1993-2010 Kingdee Software (China) Co., Ltd.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */


package com.mongo.morphia.complex.core_dal.exceptions;

/**
 * Root of the hierarchy of data access exceptions.
 *
 * <p>This exception hierarchy aims to let user code find and handle the
 * kind of error encountered without knowing the details of the particular
 * data access API in use (e.g. MongoDB Java Driver, JPA).
 *
 * <p>As this class is a runtime exception, there is no need for user code
 * to catch it or subclasses if any error is to be considered fatal
 * (the usual case).
 *
 * @author Rod Johnson
 * @author pl
 */
public class DataAccessException extends RuntimeException {

    private static final long serialVersionUID = 7638073372110932146L;

    public DataAccessException() {
        super();
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(Throwable cause) {
        super(cause);
    }
}
