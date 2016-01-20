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
public class RecordNotFoundException extends DataAccessException {

    private static final long serialVersionUID = 3056154312278182276L;

    public RecordNotFoundException() {
        super();
    }

    public RecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException(Throwable cause) {
        super(cause);
    }
}
