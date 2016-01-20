package com.mongo.morphia.complex.core;

/**
 * Copyright 1993-2010 Kingdee Software (China) Co., Ltd.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */




/**
 *
 * @since 2010-6-12
 * @author pl
 */
public interface BType<E> extends Identifiable {
    
    BType<E> adaptFrom(E bobj);
    
    E adaptTo(Class<E> clazz);
}
