/**
 * Copyright 1993-2010 Kingdee Software (China) Co., Ltd.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package com.mongo.morphia.complex.common;


import static com.google.common.base.Preconditions.checkArgument;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;

import com.google.common.base.Strings;
import com.google.common.base.Throwables;

/**
 * Bean访问工具类
 * 
 * @since 2010-5-26
 * @author pl
 */
public final class BeanUtils<T> {

    private BeanUtils() {
    }

    public static final Object get(Object bean, Class<? extends Annotation> annotationClass)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        checkArgument(bean != null);
        checkArgument(annotationClass != null);

        String readField = null;

        String field = StringUtils.uncapitalize(annotationClass.getSimpleName());
        if (PropertyUtils.isReadable(bean, field)) {
            readField = field;
        } else {
            field = getField(bean.getClass(), annotationClass);
            if (field != null && PropertyUtils.isReadable(bean, field)) {
                readField = field;
            }
        }

        if (readField != null)
            return get(bean, readField);

        return null;
    }

    public static final void set(Object bean, Class<? extends Annotation> annotationClass, Object value)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        checkArgument(bean != null);
        checkArgument(annotationClass != null);

        String writeField = null;

        String field = annotationClass.getSimpleName();
        if (PropertyUtils.isWriteable(bean, field)) {
            writeField = field;
        } else {
            field = getField(bean.getClass(), annotationClass);
            if (field != null || PropertyUtils.isWriteable(bean, field)) {
                writeField = field;
            }
        }

        if (writeField != null) {
            set(bean, writeField, value);
        }
    }

    public static final Object get(Object bean, String readField) throws IllegalAccessException,
            InvocationTargetException, NoSuchMethodException {
        checkArgument(bean != null);
        checkArgument(!Strings.isNullOrEmpty(readField));

        return PropertyUtils.getProperty(bean, readField);
    }

    public static final void set(Object bean, String writeField, Object value) throws IllegalAccessException,
            InvocationTargetException, NoSuchMethodException {
        checkArgument(bean != null);
        checkArgument(!Strings.isNullOrEmpty(writeField));

        PropertyUtils.setProperty(bean, writeField, value);
    }

    /*
     * 从本class中查找第一个符合标注的field
     */
    public static final String getField(Class<? extends Object> beanClass, Class<? extends Annotation> annotationClass) {
        if (annotationClass == null)
            return null;

        for (Field f : beanClass.getDeclaredFields()) {
            if (f.isAnnotationPresent(annotationClass)) {
                return f.getName();
            }
        }

        return null;
    }

    /**
     * Gets a no-arg constructor and calls it via reflection.
     */
    public static <E> E createInstance(Class<E> type) {
        try {
            // allows private/protected constructors
            Constructor<E> constructor = type.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (Throwable th) {
            throw Throwables.propagate(th);
        }
    }
    
    /**
     * 深拷贝
     * @author ckwang
     * @return
     * @throws IOException
     * @throws OptionalDataException
     * @throws ClassNotFoundException
     */
    public static Object deepClone(Object o) throws IOException, OptionalDataException,ClassNotFoundException   
    {   
	    //将对象写到流里   
    	ByteArrayOutputStream bo=new ByteArrayOutputStream();   
	    ObjectOutputStream oo=new ObjectOutputStream(bo);   
	    oo.writeObject(o);   
	    //从流里读出来   
	    ByteArrayInputStream bi=new ByteArrayInputStream(bo.toByteArray());   
	    ObjectInputStream oi=new ObjectInputStream(bi);   
	    return(oi.readObject());   
    }   
}