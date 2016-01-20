package com.mongo.morphia.complex.core_dal.exceptions;

/**
 * Copyright 1993-2010 Kingdee Software (China) Co., Ltd.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */




/**
 * Fatal exception thrown when we can't connect to database.
 * 
 * @since 2010-6-13
 * @author pl
 */
public class CannotGetDbConnectionException extends DataAccessResourceFailureException {

    private static final long serialVersionUID = 8376269921071192972L;

    public CannotGetDbConnectionException() {
        super();
    }

    public CannotGetDbConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotGetDbConnectionException(String message) {
        super(message);
    }

    public CannotGetDbConnectionException(Throwable cause) {
        super(cause);
    }
}
