/**
 * Copyright 1993-2010 Kingdee Software (China) Co., Ltd.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */


package com.mongo.morphia.complex.core_dal.exceptions;

/**
 *
 * @since 2010-6-13
 * @author pl
 */
public class RecordExistsException extends DataAccessException {

    private static final long serialVersionUID = 8376269921071192972L;

    public RecordExistsException() {
        super();
    }

    public RecordExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordExistsException(String message) {
        super(message);
    }

    public RecordExistsException(Throwable cause) {
        super(cause);
    }
}
