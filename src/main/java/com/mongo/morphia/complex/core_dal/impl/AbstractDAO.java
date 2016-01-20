package com.mongo.morphia.complex.core_dal.impl;

/**
 * Copyright 1993-2010 Kingdee Software (China), Co. Ltd., All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;















import com.mongo.morphia.complex.common.StringUtils;
import com.mongo.morphia.complex.core.BObject;
import com.mongo.morphia.complex.core.ContextToken;
import com.mongo.morphia.complex.core.DataAccessListener;
import com.mongo.morphia.complex.core_dal.AbstractDAOListener;
import com.mongo.morphia.complex.core_dal.CriteriaQuery;
import com.mongo.morphia.complex.core_dal.DAO;
import com.mongo.morphia.complex.core_dal.DAOListener;
import com.mongo.morphia.complex.core_dal.DAOListenerManager;
import com.mongo.morphia.complex.core_dal.QueryCallback;
import com.mongo.morphia.complex.core_dal.Results;
import com.mongo.morphia.complex.core_dal.Updates;
import com.mongo.morphia.complex.core_dal.exceptions.DataAccessException;
import com.mongo.morphia.complex.core_dal.exceptions.DataIntegrityViolationException;
import com.mongo.morphia.complex.core_dal.exceptions.RecordExistsException;
import com.mongo.morphia.complex.core_dal.exceptions.RecordNotFoundException;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;


/**
 * 
 * @since 2010-7-15
 * @author pl
 */
public abstract class AbstractDAO<E extends BObject> implements DAO<E> {

//    private static Logger logger = LoggerFactory.getLogger(AbstractDAO.class);

    protected final ContextToken token;
    protected final Class<E> entityClass;

    public AbstractDAO(ContextToken token, Class<E> entityClass) {
        this.token = token;
        this.entityClass = entityClass;

        registerListeners();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void registerListeners() {
        Class[] interfaces = getInterfaces(entityClass);
        for (Class<?> t : interfaces) {
            if (t.isAnnotationPresent(DataAccessListener.class)) {
                String beanId = t.getAnnotation(DataAccessListener.class).value();
                if (beanId == null || beanId.length() == 0) {
                    beanId = t.getSimpleName() + "DAOListener";
                }

                // added by mgl 2010-9-23
                Class<? extends DAOListener> dlClass = DAOListenerManager.getById(beanId);
                if (dlClass != null) {
                    DAOListener dl = com.mongo.morphia.complex.common.BeanUtils.createInstance(dlClass);
                    this.addListener(dl);
                }

                /*
                 * // added by mgl 2010-7-16 BundleContext bc =
                 * FrameworkUtil.getBundle(this.getClass()).getBundleContext(); try {
                 * ServiceReference[] srs = bc.getServiceReferences(DAOListener.class.getName(),
                 * null); if (srs != null) { for (int i = 0; i < srs.length; i++) { DAOListener dl =
                 * (DAOListener) bc.getService(srs[i]); bc.ungetService(srs[i]); if
                 * (dl.getClass().getSimpleName().equals(beanId)) { // getService出来的实例每次都是相同的，没法直接用
                 * dl = com.kingdee.cbos.common.utils.BeanUtils.createInstance(dl.getClass());
                 * this.addListener(dl); break; } } } } catch (InvalidSyntaxException e) {
                 * logger.warn("", e); }
                 */
            }
        }
    }

    // 获取类上实现的所有接口，包括父类实现的接口以及接口继承的接口
    @SuppressWarnings("rawtypes")
    private Class[] getInterfaces(Class<E> clazz) {
        Set<Class> set = new HashSet<Class>();
        Class<?> parent = clazz;
        while (parent != null) {
            Class[] types = parent.getInterfaces();
            for (Class type : types) {
                set.add(type);
            }
            parent = parent.getSuperclass();
        }

        Set<Class> set2 = new HashSet<Class>();
        for (Class type : set) {
            getInterfaces(set2, type);
        }
        set.addAll(set2);

        return set.toArray(new Class[0]);
    }

    @SuppressWarnings("rawtypes")
    private void getInterfaces(Set<Class> set, Class interfaceClass) {
        Class[] types = interfaceClass.getInterfaces();
        for (Class type : types) {
            set.add(type);
            getInterfaces(set, type);
        }
    }

    @Override
    public final ContextToken getContextToken() {
        return token;
    }

    @Override
    public final Class<E> getBObjectClass() {
        return entityClass;
    }

    @Override
    public final void save(E... bObjects) throws DataIntegrityViolationException, DataAccessException {
        for (E bObj : bObjects) {
            for (DAOListener<E> l : getListeners()) {
                l.preSave(bObj);
            }
            _save(bObj);
        }
    }
    
    @Override
    public final void save(WriteConcern writerConcern, E... bObjects)throws DataIntegrityViolationException, DataAccessException {
        for (E bObj : bObjects) {
            for (DAOListener<E> l : getListeners()) {
                l.preSave(bObj);
            }
            _save(writerConcern,bObj);
        }
    }

    protected abstract void _save(E bObj) throws DataIntegrityViolationException, DataAccessException;
    
    protected abstract void _save(WriteConcern writerConcern, E bObj) throws DataIntegrityViolationException, DataAccessException;

    @Override
    public final String addNew(E bObj) throws RecordExistsException, DataIntegrityViolationException,
            DataAccessException {
        for (DAOListener<E> l : getListeners()) {
            l.preAddNew(bObj);
        }
        return _addNew(bObj);
    }

    @Override
    public final void addNew(E... bObjects) throws RecordExistsException, DataIntegrityViolationException,
            DataAccessException {
        for (E bObj : bObjects) {
            addNew(bObj);
        }
    }

    protected abstract String _addNew(E bObj) throws RecordExistsException, DataIntegrityViolationException,
            DataAccessException;

    @Override
    public final void update(String id, Updates updates) throws RecordNotFoundException,
            DataIntegrityViolationException, DataAccessException {
        update(id,updates,null);
    }

    @Override
    public void update(String id, Updates updates, WriteConcern writeConcern) throws RecordNotFoundException,
            DataIntegrityViolationException, DataAccessException {
        for (DAOListener<E> l : getListeners()) {
            l.preUpdate(updates);
        }
        _update(id, updates,writeConcern);        
    }

    @Override
    public void update(CriteriaQuery<E> filter, Updates updates, WriteConcern writeConcern)
            throws DataIntegrityViolationException, DataAccessException {
        update(filter, updates,false, writeConcern);        
    }
    @Override
    public final void update(CriteriaQuery<E> filter, Updates updates) throws DataIntegrityViolationException,
            DataAccessException {
        update(filter, updates, false);
    }

    @Override
    public final void update(CriteriaQuery<E> filter, Updates updates, boolean upsert)
            throws DataIntegrityViolationException, DataAccessException {
        update(filter, updates, upsert, null);  
    }
    
    @Override
    public void update(CriteriaQuery<E> filter, Updates updates, boolean upsert, WriteConcern writeConcern)
            throws DataIntegrityViolationException, DataAccessException {
        if (filter == null) {
            filter = getCriteriaQueryBuilder().createCriteriaQuery();
        }

        for (DAOListener<E> l : getListeners()) {
            l.preUpdate(filter);
            l.preUpdate(updates);
        }

        _update(filter, updates, upsert,null);        
    }

    protected abstract void _update(String id, Updates updates, WriteConcern writeConcern) throws RecordNotFoundException,
            DataIntegrityViolationException, DataAccessException;
    protected abstract void _update(CriteriaQuery<E> filter, Updates updates, boolean upsert, WriteConcern writeConcern)
            throws DataIntegrityViolationException, DataAccessException;
    
    @Override
    public final void delete(String... ids) throws DataAccessException {
        delete(null, ids);
    }

    @Override
    public final void delete(CriteriaQuery<E> filter) throws DataAccessException {
        delete(filter,null);
    }

    @Override
    public void delete(CriteriaQuery<E> filter, WriteConcern writeConcern) throws DataAccessException {
        if (filter == null) {
            filter = getCriteriaQueryBuilder().createCriteriaQuery();
        }
        for (DAOListener<E> l : getListeners()) {
            l.preDelete(filter);
        }

        _delete(filter, writeConcern);
    }
    
    protected abstract void _delete(CriteriaQuery<E> filter, WriteConcern writeConcern) throws DataAccessException;

    @Override
    public void delete(WriteConcern writeConcern, String... ids) throws DataAccessException {
        CriteriaQuery<E> filter = getCriteriaQueryBuilder().createCriteriaQuery();

        // modified by mgl 2010-11-15
        // for(String id : ids) {
        // filter.filter("id = ", id);
        // }

        List<String> idList = new ArrayList<String>();
        for (String id : ids) {
            idList.add(id);
        }
        filter.field("id").hasAnyOf(idList);

        delete(filter, null);        
    }


    @Override
    public final boolean exists(String id) throws DataAccessException {
        if (StringUtils.isEmpty(id)) {
            return false;
        } else {
            id = id.trim();
        }

        if (!_exists(id)) {
            return false;
        } else {
            for (DAOListener<E> l : getListeners()) {
                if (!l.exists(id)) {
                    return false;
                }
            }
        }

        return true;
    }

    protected abstract boolean _exists(String id) throws DataAccessException;

    @Override
    public final long count(CriteriaQuery<E> filter) throws DataAccessException {
        if (filter == null) {
            filter = getCriteriaQueryBuilder().createCriteriaQuery();
        }

        for (DAOListener<E> l : getListeners()) {
            l.preHandleQuery(filter);
        }

        return _count(filter);
    }

    protected abstract long _count(CriteriaQuery<E> filter) throws DataAccessException;

    @Override
    public final Results<String> findIds(CriteriaQuery<E> filter) throws DataAccessException {
        if (filter == null) {
            filter = getCriteriaQueryBuilder().createCriteriaQuery();
        }

        for (DAOListener<E> l : getListeners()) {
            l.preHandleQuery(filter);
        }

        return _findIds(filter);
    }

    protected abstract Results<String> _findIds(CriteriaQuery<E> filter) throws DataAccessException;

    @Override
    public final E findOne(String id) throws DataAccessException {
        E bObj = _findOne(id);
        if (bObj != null) {
            for (DAOListener<E> l : getListeners()) {
                bObj = l.postHandleFindOne(bObj);
            }
        }

        return bObj;
    }

    protected abstract E _findOne(String id) throws DataAccessException;

    @Override
    public final Results<E> find(String... id) throws DataAccessException {
        Results<E> results = _find(id);
        boolean temp = filterByTenant;
        filterByTenant = false;
        for (DAOListener<E> l : getListeners()) {
            l.postHandleQuery(results);
        }
        filterByTenant = temp;

        return results;
    }

    protected abstract Results<E> _find(String... id) throws DataAccessException;

    @Override
    public final Results<E> query(CriteriaQuery<E> filter) throws DataAccessException {
        if (filter == null) {
            filter = getCriteriaQueryBuilder().createCriteriaQuery();
        }

        for (DAOListener<E> l : getListeners()) {
            l.preHandleQuery(filter);
        }

        Results<E> results = _query(filter);

        for (DAOListener<E> l : getListeners()) {
            l.postHandleQuery(results);
        }

        return results;
    }
    

    @Override
    public Results<E> query(CriteriaQuery<E> filter, ReadPreference readPreference) throws DataAccessException {
        if (filter == null) {
            filter = getCriteriaQueryBuilder().createCriteriaQuery();
        }

        for (DAOListener<E> l : getListeners()) {
            l.preHandleQuery(filter);
        }

        Results<E> results = _query(filter,readPreference);

        for (DAOListener<E> l : getListeners()) {
            l.postHandleQuery(results);
        }

        return results;
    }

    protected abstract  Results<E> _query(CriteriaQuery<E> filter, ReadPreference readPreference)throws DataAccessException;

    protected abstract Results<E> _query(CriteriaQuery<E> criteria) throws DataAccessException;

    @Override
    public final void query(CriteriaQuery<E> filter, QueryCallback<E> callback) throws DataAccessException {
        Results<E> rs = query(filter);
        while (rs.hasNext()) {
            callback.process(rs.next());
        }
    }

    private List<DAOListener<E>> listeners;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public final synchronized void addListener(DAOListener<E> l) {
        if (listeners == null)
            listeners = new ArrayList<DAOListener<E>>();

        if (l instanceof AbstractDAOListener) {
            ((AbstractDAOListener) l).setDAO(this);
        }
        listeners.add(l);
    }

    @Override
    public final synchronized void removeListener(DAOListener<E> l) {
        if (listeners == null)
            return;

        listeners.remove(l);
    }

    @Override
    public final synchronized List<DAOListener<E>> getListeners() {
        if (listeners == null) {
            listeners = new ArrayList<DAOListener<E>>();
        }

        return listeners;
    }

    // added by mgl 2010-9-23
    private boolean filterByTenant = true;

    @Override
    public void setFilterByTenant(boolean filterByTenant) {
        this.filterByTenant = filterByTenant;
    }

    @Override
    public boolean isFilterByTenant() {
        return filterByTenant;
    }

    // added by mgl 2011-8-8
    @Override
    public void batchAddNew(List<E> bObjects) throws RecordExistsException, DataIntegrityViolationException,
            DataAccessException {
        if (bObjects == null || bObjects.size() == 0)
            return;

        for (E e : bObjects) {
            for (DAOListener<E> l : getListeners()) {
                l.preSave(e);
            }
        }

        _batchAddNew(bObjects);
    }

    protected abstract void _batchAddNew(List<E> bObjects) throws RecordExistsException,
            DataIntegrityViolationException, DataAccessException;
}