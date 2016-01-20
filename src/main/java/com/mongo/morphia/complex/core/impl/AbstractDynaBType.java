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
import com.mongo.morphia.complex.core.DynaBType;
import com.mongo.morphia.complex.core.DynaBean;


/**
 *
 * @since 2010-6-12
 * @author pl
 */
public abstract class AbstractDynaBType<E> extends AbstractDynaBean implements DynaBType<E> {

    private static final long serialVersionUID = 6913581245276534150L;
    
	private String id;
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}
	

    @Override
    public DynaBType<E> adaptFrom(E obj) {
        try {
            if (obj instanceof DynaBean) {
        	putAll((DynaBean)obj);
            } else {
        	@SuppressWarnings("unchecked")
			Map<String,Object> beanMap = new HashMap<String,Object>(new BeanMap(obj));
        	if(beanMap.containsKey("class")){
        	    beanMap.remove("class");
        	}
        	putAll(beanMap);
            }
        } catch (Throwable th) {
            Throwables.propagate(th);
        }
        
        return this;
    }

    @Override
    public E adaptTo(Class<E> clazz) {
    	E obj = com.mongo.morphia.complex.common.BeanUtils.createInstance(clazz);
    	try {
    	    if (DynaBean.class.isAssignableFrom(clazz)) {
    		((DynaBean)obj).putAll(this);
    	    } else {
    		BeanUtils.populate(obj, this.toMap());
    	    }
    	} catch (Throwable th) {
    	    Throwables.propagate(th);
    	}
    
    	return obj;
    }
}
