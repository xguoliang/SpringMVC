package com.mongo.morphia.complex.core_dal.impl;

/**
 * Copyright 1993-2010 Kingdee Software (China), Co. Ltd., All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */



import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongo.morphia.complex.core.BObject;
import com.mongo.morphia.complex.core_dal.AbstractDAOListener;
import com.mongo.morphia.complex.core_dal.CriteriaQuery;
import com.mongo.morphia.complex.core_dal.DAO;
import com.mongo.morphia.complex.core_dal.Results;
import com.mongo.morphia.complex.core_dal.Updates;
import com.mongo.morphia.complex.core_dal.exceptions.DataAccessException;


/**
 *
 * @since 2010-7-15
 * @author pl
 */
public 	class ObjectScopeDAOListener<E extends BObject> extends AbstractDAOListener<E> {
    private static Logger logger = LoggerFactory.getLogger(ObjectScopeDAOListener.class);
    
    public ObjectScopeDAOListener() {
        super();
    }
    
	public ObjectScopeDAOListener(DAO<E> dao) {
		super(dao);
	}

	private String getCurrTenantId() {
		return getDAO().getContextToken().getContext().getTenantId();
	}

	// TODO 未做到关联对象设置 tenantId
	
	@Override
	public void preSave(E bObj) throws DataAccessException {
        if (isFilterByTenant()) {
            bObj.setTenantId(getCurrTenantId());
        }
	}

	@Override
    public void preAddNew(E bObj) throws DataAccessException {
        if (isFilterByTenant()) {
            bObj.setTenantId(getCurrTenantId());
        }
    }

	@Override
	public void preUpdate(Updates updates) throws DataAccessException {
		// TODO 从updates中去掉 "tenantId"
	}

	@Override
	public void preUpdate(CriteriaQuery<E> filter) {
		appendFilter(filter);
	}

	@Override
	public void preDelete(CriteriaQuery<E> filter) {
		appendFilter(filter);
	}

	@Override
	public boolean exists(String id) throws DataAccessException {
		E bobj = getDAO().findOne(id);
        if (bobj != null) {
            return true;
            // String tId = bobj.getTenantId();
            // return tId == null || tId.equals(getCurrTenantId());
        }
        return false;	
	}

	@Override
	public E postHandleFindOne(E bObj) throws DataAccessException {
	    return bObj;
        // String tId = bObj.getTenantId();
        // if (tId == null || tId.equals(getCurrTenantId())) {
        // return bObj;
        // } else {
        // return null;
        // }
	}

	@Override
	public void preHandleQuery(CriteriaQuery<E> filter) {
		appendFilter(filter);
	}

	@Override
	public Results<E> postHandleQuery(Results<E> results) throws DataAccessException {
        if (!isFilterByTenant())
            return results;
	    
        // modified by mgl 2010-7-28 某些场景如搜索时需要移除错误数据，而不是抛出异常
	    Iterator<E> ite = results.iterator();
	    while (ite.hasNext()) {
            try {
                E bObj = ite.next();
                String tId = bObj.getTenantId();
                if (tId != null && !tId.equals(getCurrTenantId())) {
                    ite.remove();
                }
            } catch (Exception e) {
                logger.warn("", e);
                // DBCursor还没有实现remove功能
                // ite.remove();
            }
        }

		return results;
	}

    private void appendFilter(CriteriaQuery<E> filter) {
        if (isFilterByTenant()) {
            com.google.common.base.Preconditions.checkNotNull(filter);
                filter.filter("tenantId = ", getCurrTenantId());
        }
    }

    private boolean isFilterByTenant() {
        return getDAO().isFilterByTenant() && getDAO().getContextToken() != null
                && getDAO().getContextToken().getContext() != null;
    }
}