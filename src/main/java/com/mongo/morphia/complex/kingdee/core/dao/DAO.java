package com.mongo.morphia.complex.kingdee.core.dao;

/**
 * Copyright 1993-2010 Kingdee Software (China) Co., Ltd.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */


import java.util.List;







import com.mongo.morphia.complex.core_dal.exceptions.DataAccessException;
import com.mongo.morphia.complex.core_dal.exceptions.DataAccessResourceFailureException;
import com.mongo.morphia.complex.kingdee.core.server.BObject;
import com.mongo.morphia.complex.kingdee.morphia.integer.CriteriaQuery;
import com.mongo.morphia.complex.kingdee.morphia.integer.CriteriaQueryBuilder;
import com.mongo.morphia.complex.kingdee.morphia.integer.Results;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;

public interface DAO<E extends BObject> {

    Class<E> getBObjectClass();
    
//    ContextToken getContextToken();
//    
//
//    void save(E... bObjects) throws DataIntegrityViolationException, DataAccessException;
    void save(E... bObjects) throws DataAccessException;
//
//
//	void save(WriteConcern writerConcern, E... bObjects)throws DataIntegrityViolationException, DataAccessException;
//	
//    String addNew(E bObj) throws RecordExistsException, DataIntegrityViolationException, DataAccessException;
//    
//    void addNew(E... bObjects) throws RecordExistsException, DataIntegrityViolationException, DataAccessException;
//
//    Updates createUpdates();
//    
//    void update(String id, Updates updates) throws RecordNotFoundException, DataIntegrityViolationException,
//	    DataAccessException;
//
//    void update(String id, Updates updates, WriteConcern writeConcern) throws RecordNotFoundException, DataIntegrityViolationException,
//    DataAccessException;
//    
//    void update(CriteriaQuery<E> filter, Updates updates) throws DataIntegrityViolationException, DataAccessException;
//
//    void update(CriteriaQuery<E> filter, Updates updates, WriteConcern writeConcern) throws DataIntegrityViolationException, DataAccessException;
    
//    void delete(String... ids) throws DataAccessException;
    
//    void delete(WriteConcern writeConcern,String... ids) throws DataAccessException;
//
//    void delete(CriteriaQuery<E> filter) throws DataAccessException;
//
//    void delete(CriteriaQuery<E> filter, WriteConcern writeConcern) throws DataAccessException;
//    
//    boolean exists(String id) throws DataAccessException;
//
//    long count(CriteriaQuery<E> filter) throws DataAccessException;
//
//    Results<String> findIds(CriteriaQuery<E> criteria) throws DataAccessException;
//
    E findOne(String id) throws DataAccessResourceFailureException;
//
//    Results<E> find(String... id) throws DataAccessException;

    Results<E> query(CriteriaQuery<E> criteria) throws DataAccessException;
//
//    Results<E> query(CriteriaQuery<E> criteria, ReadPreference readPreference) throws DataAccessException;
//    
//    void query(CriteriaQuery<E> criteria, QueryCallback<E> callback) throws DataAccessException;
//
    CriteriaQueryBuilder<E> getCriteriaQueryBuilder();
//
//    
//    Results<AggregationResult> aggregate(Aggregation<E> aggregation);
//    
//    AggregationBuilder<E> getAggregationBuilder();
//    
//    
//    Object getStorageEngine();
//    
//    
//    void addListener(DAOListener<E> l);
//    
//    void removeListener(DAOListener<E> l);
//    
    List<DAOListener<E>> getListeners();
//    
//    // added by mgl 2010-9-23
//    void setFilterByTenant(boolean filterByTenant);
//
//    boolean isFilterByTenant();
//    
//    // added by mgl 2011-8-8
//    void batchAddNew(List<E> bObjects) throws RecordExistsException, DataIntegrityViolationException,
//            DataAccessException;
//
//    // added by mgl 2011-8-18
//    void update(CriteriaQuery<E> filter, Updates updates, boolean upsert) throws DataIntegrityViolationException,
//            DataAccessException;
//    
//    void update(CriteriaQuery<E> filter, Updates updates, boolean upsert,WriteConcern writeConcern) throws DataIntegrityViolationException,
//    DataAccessException;
}