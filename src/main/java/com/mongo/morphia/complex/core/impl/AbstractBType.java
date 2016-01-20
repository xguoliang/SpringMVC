/**
 * Copyright 1993-2010 Kingdee Software (China) Co., Ltd.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */


package com.mongo.morphia.complex.core.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

import com.google.common.base.Throwables;
import com.mongo.morphia.complex.core.BType;
import com.mongo.morphia.complex.core.DynaBean;


/**
 *
 * @since 2010-6-12
 * @author pl
 */
public abstract class AbstractBType<E> implements BType<E> {

	private String id;
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}
	
	@SuppressWarnings("unchecked")
	@Override
    public BType<E> adaptFrom(E obj) {
        try {        	
            Map<String,Object> beanMap;
            if (obj instanceof DynaBean) {
            	beanMap = ((DynaBean)obj).toMap();
            } else {
        		beanMap = new HashMap<String,Object>(new BeanMap(obj));
        		if(beanMap.containsKey("class")){
        		    beanMap.remove("class");
        		}
            }
            BeanUtils.populate(this, beanMap);
        } catch (Throwable th) {
            Throwables.propagate(th);
        }
        
        return this;
    }

    @Override
    public E adaptTo(Class<E> clazz) {
    	E obj = com.mongo.morphia.complex.common.BeanUtils.createInstance(clazz);
    	try {
    		@SuppressWarnings("unchecked")
    	    Map<String,Object> beanMap = new HashMap<String,Object>(new BeanMap(this));
    	    if(beanMap.containsKey("class")){
    	    	beanMap.remove("class");
    	    }
    	    if (DynaBean.class.isAssignableFrom(clazz)) {
    	    	((DynaBean)obj).putAll(beanMap);
    	    } else {
    	    	BeanUtils.populate(obj, beanMap);
    	    }
    	} catch (Throwable th) {
    	    Throwables.propagate(th);
    	}
    
    	return obj;
    }
}
