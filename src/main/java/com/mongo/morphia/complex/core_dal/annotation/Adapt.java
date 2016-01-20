package com.mongo.morphia.complex.core_dal.annotation;

/**
 * Copyright 1993-2010 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */




import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记entityType上的field和entity上的field之间的映射
 * @since 2010-6-22
 * @author guolei_mao
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Adapt {
    String value();
}
