package com.mongo.morphia.complex.kingdee.core.dao;
/**
 *
 * 
 * 修订历史记录：
 * 
 * Revision	1.0	 2010-11-3  guolei_mao  创建。
 * 
 */



import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.BasicBSONDecoder;
import org.bson.BasicBSONEncoder;




import com.mongo.morphia.complex.core.ContextToken;
import com.mongo.morphia.complex.kingdee.core.dao.factory.DAOFactory;
import com.mongo.morphia.complex.kingdee.core.server.BObject;
import com.mongo.morphia.complex.kingdee.morphia.integer.CriteriaQuery;
import com.mongodb.DBCallback;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.DefaultDBCallback;
import com.mongodb.WriteConcern;

/**
 * 类说明:
 * 
 * @author <a href="mailto:guolei_mao@kingdee.com">guolei_mao</a>
 * @version 1.0, 2010-11-3
 */
public abstract class BaseDAOImpl<T extends BObject> implements BaseDAO<T> {
    protected ContextToken token;
    protected DAO<T> dao;

    public BaseDAOImpl() {

    }

    public BaseDAOImpl(ContextToken ct) {
        this.token = ct;
    }

    @SuppressWarnings("unchecked")
    protected DAO<T> getDAO() {
        if (dao == null) {
            Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                    .getActualTypeArguments()[0];
            dao = DAOFactory.getInstance(token, entityClass);
        }
        return dao;
    }
//
//    @Override
//    public Results<T> findAll() {
//        return getDAO().find();
//    }
//
//    @Override
//    public List<T> findAll(int start, int limit) {
//        CriteriaQuery<T> query = getQuery();
//        query.offset(start).limit(limit);
//        return getDAO().query(query).asList();
//    }
//    
//    @Override
//    public List<T> findAll(String order, int start, int limit) {
//        CriteriaQuery<T> query = getQuery();
//        if (order != null) {
//            query.order(order);
//        }
//        query.offset(start).limit(limit);
//        return getDAO().query(query).asList();
//    }

//    @Override
//    public long countAll(int limit) {
//        CriteriaQuery<T> query = getQuery();
//        query.limit(limit);
//        return getDAO().count(query);
//    }

    @Override
    public T find(String id) {
        return getDAO().findOne(id);
    }

//    @Override
//    public void delete(String id) {
//        getDAO().delete(id);
//    }

    @SuppressWarnings("unchecked")
    @Override
    public void save(T t) {
        getDAO().save(t);
    }
//    
//    @Override
//    public void save(T t, WriteConcern writerConcern){
//    		 getDAO().save(writerConcern, t);
//    }
//
//    /*
//     * (non-Javadoc)
//     * 
//     * @see com.kingdee.cbos.core.framework.dal.BaseDAO#exist(java.lang.String)
//     */
//    @Override
//    public boolean exist(String id) {
//        return this.getDAO().exists(id);
//    }

    protected CriteriaQuery<T> getQuery() {
        CriteriaQuery<T> query = getDAO().getCriteriaQueryBuilder().createCriteriaQuery();
        return query;
    }

//    protected List<T> findBy(String field, Object value) {
//        return findBy(field, value, 0, 0);
//    }
//
//    protected List<T> findBy(String field, Object value, int start, int limit) {
//        CriteriaQuery<T> query = getQuery();
//        if (value instanceof BObject) {
//            query.filter(field + ".id", ((BObject) value).getId());
//        } else {
//            query.field(field).equal(value);
//        }
//        query.offset(start).limit(limit);
//        Results<T> results = getDAO().query(query);
//
//        return results.asList();
//    }
//
//    @Override
//    public List<T> find(List<String> ids) {
//        if (ids == null || ids.size() == 0)
//            return new ArrayList<T>(4);
//
//        CriteriaQuery<T> query = getQuery();
//        query.field("id").hasAnyOf(ids);
//        return getDAO().query(query).asList();
//    }
//
//    protected CriteriaQuery<T> getQuery(int start, int limit) {
//        CriteriaQuery<T> query = getDAO().getCriteriaQueryBuilder().createCriteriaQuery();
//        query.offset(start).limit(limit);
//        return query;
//    }
//
//    /**
//     * 左闭右开区间
//     * 
//     * @param dateField
//     * @param startTime
//     * @param endTime
//     * @return
//     */
//    protected CriteriaQuery<T> getQuery(String dateField, Date startTime, Date endTime) {
//        CriteriaQuery<T> query = getDAO().getCriteriaQueryBuilder().createCriteriaQuery();
//        if (startTime != null) {
//            query.field(dateField).greaterThanOrEq(startTime);
//        }
//        if (endTime != null) {
//            query.field(dateField).lessThan(endTime);
//        }
//        return query;
//    }
//
//    protected CriteriaQuery<T> getQuery(String dateField, Date startTime, Date endTime, int start, int limit) {
//        CriteriaQuery<T> query = getQuery(dateField, startTime, endTime);
//        query.offset(start).limit(limit);
//        return query;
//    }
//
//    @Override
//    public void inc(String id, String field, int i) {
//        Updates up = this.getUpdates();
//        up.inc(field, i);
//        this.getDAO().update(id, up);
//    }
//
//    @Override
//    public void incFloat(String id, String field, float f) {
//        Updates up = this.getUpdates();
//        up.incFloat(field, f);
//        this.getDAO().update(id, up);
//    }
//
//    protected Updates getUpdates() {
//        return this.getDAO().createUpdates();
//    }
//
//    @Override
//    public byte[] encode(T entity) {
//        return new BasicBSONEncoder().encode(toDBObject(entity));
//    }
//
//    @Override
//    public T decode(byte[] data) {
//        DBCollection dbCollection = ((MongoDAOImpl<T>) getDAO()).getMongoHelper().getCollection();
////        DBCallback callback = ((MongoDAOImpl<T>) getDAO()).getMongoHelper().getMongo().getMongoOptions().dbCallbackFactory
////                .create(dbCollection);
//        DBCallback callback = DefaultDBCallback.FACTORY.create(dbCollection);
//        callback.reset();
//        new BasicBSONDecoder().decode(data, callback);
//        return fromDBObject((DBObject) callback.get());
//
//    }
//
//    protected DBObject toDBObject(T entity) {
//        return ((MongoDAOImpl<T>) getDAO()).getMongoHelper().toDBObject(entity);
//    }
//
//    protected T fromDBObject(DBObject dbObject) {
//        ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
//        Class<T> aClass = (Class<T>) superclass.getActualTypeArguments()[0];
//        return ((MongoDAOImpl<T>) getDAO()).getMongoHelper().fromDBObject(aClass, dbObject);
//    }
}
