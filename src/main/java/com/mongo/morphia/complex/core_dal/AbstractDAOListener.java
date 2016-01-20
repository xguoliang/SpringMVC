/**
 * Copyright 1993-2010 Kingdee Software (China), Co. Ltd., All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */


package com.mongo.morphia.complex.core_dal;


import com.mongo.morphia.complex.core.BObject;
import com.mongo.morphia.complex.core_dal.exceptions.DataAccessException;



/**
 *
 * @since 2010-7-15
 * @author pl
 */
public abstract class AbstractDAOListener<E extends BObject> implements DAOListener<E> {
	
	private DAO<E> dao;
	
    public AbstractDAOListener() {

    }
	
	public AbstractDAOListener(DAO<E> dao) {
		this.dao = dao;
	}
	
	protected DAO<E> getDAO() {
		return dao;
	}
	
	public void setDAO(DAO<E> dao) {
	    this.dao = dao;
	}

	@Override
	public void preSave(E bObj) throws DataAccessException {
	}

	@Override
	public void preAddNew(E bObj) throws DataAccessException {
	}

	@Override
	public void preUpdate(Updates updates) throws DataAccessException {
	}

	@Override
	public void preUpdate(CriteriaQuery<E> filter) {
	}

	@Override
	public void preDelete(CriteriaQuery<E> filter) {
	}

	@Override
	public boolean exists(String id) throws DataAccessException {
		return true;
	}

	@Override
	public E postHandleFindOne(E bObj) throws DataAccessException {
		return bObj;
	}

	@Override
	public void preHandleQuery(CriteriaQuery<E> filter) {
	}

	@Override
	public Results<E> postHandleQuery(Results<E> results) throws DataAccessException {
		return results;
	}	
}