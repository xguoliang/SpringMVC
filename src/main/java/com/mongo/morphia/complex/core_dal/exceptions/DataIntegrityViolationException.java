/**
 * Copyright 1993-2010 Kingdee Software (China) Co., Ltd.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */


package com.mongo.morphia.complex.core_dal.exceptions;

/**
 * Exception thrown when an attempt to insert or update data
 * results in violation of an integrity constraint. Note that this
 * is not purely a relational concept; unique primary keys are
 * required by most database types.
 *
 * @author Rod Johnson
 */
public class DataIntegrityViolationException extends DataAccessException {

    private static final long serialVersionUID = 8376269921071192972L;

    public DataIntegrityViolationException() {
        super();
    }

    public DataIntegrityViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataIntegrityViolationException(String message) {
        super(message);
    }

    public DataIntegrityViolationException(Throwable cause) {
        super(cause);
    }
}
