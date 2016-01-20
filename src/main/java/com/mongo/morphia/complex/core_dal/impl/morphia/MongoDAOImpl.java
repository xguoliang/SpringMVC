package com.mongo.morphia.complex.core_dal.impl.morphia;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.morphia.Key;
import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.PostPersist;
import com.google.code.morphia.annotations.Reference;
import com.google.code.morphia.mapping.MappedClass;
import com.google.code.morphia.mapping.MappedField;
import com.google.code.morphia.mapping.Mapper;
import com.google.code.morphia.mapping.MappingException;
import com.google.code.morphia.query.Query;
import com.mongo.morphia.complex.core.BObject;
import com.mongo.morphia.complex.core.ContextToken;
import com.mongo.morphia.complex.core_dal.Aggregation;
import com.mongo.morphia.complex.core_dal.AggregationBuilder;
import com.mongo.morphia.complex.core_dal.AggregationResult;
import com.mongo.morphia.complex.core_dal.CriteriaQuery;
import com.mongo.morphia.complex.core_dal.CriteriaQueryBuilder;
import com.mongo.morphia.complex.core_dal.MongoHelper;
import com.mongo.morphia.complex.core_dal.Results;
import com.mongo.morphia.complex.core_dal.Updates;
import com.mongo.morphia.complex.core_dal.annotation.Cascade;
import com.mongo.morphia.complex.core_dal.exceptions.DataAccessException;
import com.mongo.morphia.complex.core_dal.exceptions.DataIntegrityViolationException;
import com.mongo.morphia.complex.core_dal.exceptions.RecordExistsException;
import com.mongo.morphia.complex.core_dal.exceptions.RecordNotFoundException;
import com.mongo.morphia.complex.core_dal.impl.AbstractDAO;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.mongodb.ReadPreference;
//import com.kingdee.cbos.core.framework.DynaBean;
import com.mongodb.WriteConcern;


/**
 * DAO实现
 * @author yuanpei_lin
 *
 * @param <E>
 */
public class MongoDAOImpl<E extends BObject> extends AbstractDAO<E> {
    private static Logger logger = LoggerFactory.getLogger(MongoDAOImpl.class);
	private final MongoHelperImpl mongoHelper;

	public MongoDAOImpl(ContextToken ct, Class<E> entityClass) {
		super(ct, entityClass);
		mongoHelper = new MongoHelperImpl(token, entityClass);
		IndexEnsurer.ensureIndexes(mongoHelper, entityClass);
	}

	public MongoHelperImpl getMongoHelper() {		
		return mongoHelper;
	}

	@Override
	protected long _count(CriteriaQuery<E> filter) {
		return ((CriteriaQueryImpl<E>) filter).count();
	}
    
	/**根据CriteriaQuery删除 
	 * @param filter
	 */
	@Override
    protected void _delete(CriteriaQuery<E> filter, WriteConcern writeConcern) {
	    if(writeConcern==null){
	        collection().remove(((CriteriaQueryImpl<E>)filter).getQueryObject());	
	    }else{
	        collection().remove(((CriteriaQueryImpl<E>)filter).getQueryObject(), writeConcern);  
	    }
	}
	
	/**
	 * 查询单个对象
	 * @param id
	 * @return
	 */
    private DBObject get( String id ) {   
    	BasicDBObject basicDBObject =  new BasicDBObject();
        // modified by mgl 2010-10-13 不需要转换成ObjectId，转换后反而查不出数据
        // basicDBObject.put(Mapper.ID_KEY, asObjectIdMaybe(id));
        basicDBObject.put(Mapper.ID_KEY, id);
        BasicDBObject dbObject = (BasicDBObject) collection().findOne(basicDBObject);       
        return dbObject;
    }
    
	/**
	 * 是否存在
	 * @id 
	 */
	@Override
    protected boolean _exists(String id) {
		return get(id)!=null;
	}
	
	
	@Override
    protected E _findOne(String id) {
		BasicDBObject basicDBObject =  new BasicDBObject();
        // modified by mgl 2010-10-13 不需要转换成ObjectId，转换后反而查不出数据
        // basicDBObject.put(Mapper.ID_KEY, asObjectIdMaybe(id));
        basicDBObject.put(Mapper.ID_KEY, id);
        BasicDBObject res = (BasicDBObject) collection().findOne(basicDBObject, null, ReadPreference.primary());   
		if(res!=null){
			return (E)mongoHelper.fromDBObject(res);
		}else{
			return null;
		}
	    // modified by mgl 2010-9-27
//	    CriteriaQueryImpl<E> cquery = (CriteriaQueryImpl<E>) getCriteriaQueryBuilder().createCriteriaQuery();
//	    cquery.readPreference(ReadPreference.primaryPreferred());
//	    cquery.field(Mapper.ID_KEY).equal(id);
//	    Results<E> results = new ResultsImpl<E>(cquery.getQuery(), mongoHelper);//find(id);
//	    if (results.hasNext()) {
//	    	return results.next();
//	    } else {
//	    	return null;
//	    }
	}
	
	/**
	 * 根据id[]查询
	 */
	@Override
	protected Results<E> _find(String... id) {	
		List<Object> list = new ArrayList<Object>();
		for(String keyId:id){
            // modified by mgl 2010-10-13 不需要转换成ObjectId，转换后反而查不出数据
            // list.add(asObjectIdMaybe(keyId));
            list.add(keyId);
		}
		CriteriaQueryImpl<E> cquery = (CriteriaQueryImpl<E>) getCriteriaQueryBuilder().createCriteriaQuery();
		if (id.length>0) {
			cquery.field(Mapper.ID_KEY).hasAnyOf(list);
		}
		cquery.readPreference(ReadPreference.primaryPreferred());
		return new ResultsImpl<E>(cquery.getQuery(), mongoHelper);
	}

	@Override
	protected Results<String> _findIds(CriteriaQuery<E> criteria) {
		Query<E> query = ((CriteriaQueryImpl<E>)criteria).getQuery();		
		return new IDResultsImpl<E>(query.fetchKeys());		
	}
	
	/**
	 * 取得mongodb 的collection
	 * @param clazz
	 * @return
	 */
	protected DBCollection collection() {	
		return getMongoHelper().getCollection(entityClass);
         
    }
	
	/**
	 * 得到CriteriaQueryBuilder实例
	 */
	@Override
    public CriteriaQueryBuilder<E> getCriteriaQueryBuilder() {
		return new CriteriaQueryBuilderImpl<E>(entityClass,collection(),getMongoHelper());
	}

    @Override
    public MongoHelper getStorageEngine() {
        return getMongoHelper();
    }
	
	/**
	 * CriteriaQuery返回Result对象
	 */
    @Override
    protected Results<E> _query(CriteriaQuery<E> criteria) {
        ResultsImpl<E> rsts = new ResultsImpl<E>(((CriteriaQueryImpl<E>) criteria).getQuery(), mongoHelper);
        return rsts;
    }
	
    @Override
    protected Results<E> _query(CriteriaQuery<E> criteria, ReadPreference readPreference) throws DataAccessException {
        MongoQueryImpl<E> queryImpl = (MongoQueryImpl<E>)(((CriteriaQueryImpl<E>) criteria).getQuery());
        if(readPreference!=null)
            queryImpl.setReadPreference(readPreference);
        ResultsImpl<E> rsts = new ResultsImpl<E>(queryImpl, mongoHelper);
        return rsts;
    }
    
    @Override
    protected String _addNew(E bObj) throws RecordExistsException, DataIntegrityViolationException, DataAccessException {
        String id = bObj.getId();
        if (id != null && exists(id)) {
            throw new RecordExistsException(this.entityClass.getCanonicalName() + " id:" + id + " is exists");
        } else {
            _save(bObj);
        }
        return bObj.getId();
    }

	/**
	 * 替换reference表名  like @Reference MyEntity  =>表名为 T_SubsystemName_MyEntity
	 * mongodb中Reference的存储格式{"$ref":"MyEntity"} =>变为{"$ref":"T_SubsystemName_MyEntity"}
	 * @param dbObject主表的DBObject
	 * @param key 属性名称
	 * @param reName改动后的表名
	 */	
    // modified by mgl 2011-4-12
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void reNameDBREF(DBObject dbObject, String key, Collection fieldValueCollection) {
        if (dbObject.containsField(key)) {
            Object dbrefs = dbObject.get(key);
            if (dbrefs instanceof DBRef) {
                dbObject.removeField(key);
                String reName = getMongoHelper().getCollectionName(fieldValueCollection.iterator().next().getClass());
                dbObject.put(key, new DBRef(null, reName, ((DBRef) dbrefs).getId()));
            } else if (dbrefs instanceof List) {
                List<DBRef> oldlist = (List<DBRef>) dbrefs;
                List<DBRef> newlist = new ArrayList<DBRef>(oldlist.size());
                Iterator ite = fieldValueCollection.iterator();
                for (DBRef ref : (List<DBRef>) dbrefs) {
                    String reName = getMongoHelper().getCollectionName(ite.next().getClass());
                    newlist.add(new DBRef(null, reName, ref.getId()));
                }
                dbObject.removeField(key);
                dbObject.put(key, newlist);
            }
        }
    }
	
    /**
     * 保存单个BObject
     * 
     * @param bObject
     */
    @Override
    protected void _save(E bObj) throws DataIntegrityViolationException, DataAccessException {
        innerSave(bObj,null);
    }
	
	@Override
	protected void _save(WriteConcern writerConcern, E bObj)
			throws DataIntegrityViolationException, DataAccessException {
		innerSave(bObj, writerConcern);
	}
    
    // modified by mgl 2010-7-27 支持多级级联保存
    @SuppressWarnings("rawtypes")
    private DBObject innerSave(BObject bObject,WriteConcern writerConcern) {
        if (!bObject.getClass().isAnnotationPresent(Embedded.class)) {
            try {
                getMongoHelper().getMapper().addMappedClass(bObject.getClass());
                Utils.generateId(bObject);
            } catch (Exception me) {
                logger.warn("", me);
                throw new DataAccessException(me);
            }
        }

        DBObject dbObject = null;

        MappedClass mappedClass = getMongoHelper().getMapper().getMappedClass(bObject);
        if (mappedClass != null) {
            for (MappedField mappedField : mappedClass.getPersistenceFields()) {
                // 取Reference数据
                Collection fieldValueCollection = getFieldValues(mappedField, bObject, Reference.class);
                if (fieldValueCollection != null && fieldValueCollection.size() > 0) {
                    // field对应的数据库名
                    if (isCascade(bObject, mappedField.getJavaFieldName())) {// 级联新增
                        List list = saveCollection(fieldValueCollection);
                        if (dbObject == null)
                            dbObject = getMongoHelper().toDBObject(bObject);
                        if (list != null) {
                            Iterator ite = fieldValueCollection.iterator();
                            List<DBRef> dbrefList = new ArrayList<DBRef>(list.size());
                            for (int i = 0; i < list.size(); i++) {
                                Key key = (Key) list.get(i);
                                String fieldDbName = getMongoHelper().getCollectionName(ite.next().getClass());
                                dbrefList.add(new DBRef(null, fieldDbName, key.getId()));
                            }
                            dbObject.removeField(mappedField.getNameToStore());
                            if (mappedField.isMultipleValues()) {
                                dbObject.put(mappedField.getNameToStore(), dbrefList);
                            } else {
                                dbObject.put(mappedField.getNameToStore(), dbrefList.get(0));
                            }
                        }
                    } else {// 非级联新增改变Reference表名
                        if (dbObject == null)
                            dbObject = getMongoHelper().toDBObject(bObject);
                        reNameDBREF(dbObject, mappedField.getNameToStore(), fieldValueCollection);
                    }
                } else if (mappedField.hasAnnotation(Embedded.class)) {
                    // added by mgl 2010-8-9 支持Embedded对象的级联保存
                    Collection fvc = getFieldValues(mappedField, bObject, Embedded.class);
                    if (fvc != null && fvc.size() > 0) {
                        List<DBObject> list = new ArrayList<DBObject>(fvc.size());
                        for (Object object : fvc) {
                            if (object instanceof BObject) {
                                // DBObject dbo = innerSave((BObject) object);
                                DBObject dbo = getMongoHelper().toDBObject(object);
                                list.add(dbo);
                            }
                        }
                        if (list.size() > 0) {
                            if (dbObject == null)
                                dbObject = getMongoHelper().toDBObject(bObject);
                            if (mappedField.isMultipleValues()) {
                                dbObject.put(mappedField.getNameToStore(), list);
                            } else {
                                dbObject.put(mappedField.getNameToStore(), list.get(0));
                            }
                        }
                    }
                }
            }
        }

        if (dbObject == null)
            dbObject = getMongoHelper().toDBObject(bObject);

        if (!bObject.getClass().isAnnotationPresent(Embedded.class)) {
            // modified by mgl 2010-11-21
//            if (DynaBean.class.isAssignableFrom(bObject.getClass())) {
//                Map<String, Object> ext = ((DynaBean) bObject).extended();
//                if (ext != null)
//                    dbObject.putAll(ext);
//            }
        	    if(writerConcern == null){
        	    		getMongoHelper().getCollection(bObject.getClass()).save(dbObject);
        	    }else{
        	    		getMongoHelper().getCollection(bObject.getClass()).save(dbObject, writerConcern);
        	    }
            
        }
        isErr();

		getMongoHelper().getMapper().updateKeyInfo(bObject, dbObject,
				getMongoHelper().getMapper().createEntityCache());
		getMongoHelper().getMapper().getMappedClass(bObject)
				.callLifecycleMethods(PostPersist.class, bObject, dbObject,
						getMongoHelper().getMapper());
        
        return dbObject;
    }
    
    /**
     * 查询数据
     * 
     * @param mappedField
     * @param bObject
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Collection getFieldValues(MappedField mappedField, BObject bObject) {
        Collection fieldValueCollection = null;
        Object fieldValue = getFieldValue(mappedField, bObject);
        if (fieldValue != null) {
            if (mappedField.isMultipleValues()) {// like @Reference List myList<MyEntity>
                if (mappedField.getType().isArray()) {// like @Reference MyEntity[] entitys
                    fieldValueCollection = new ArrayList();
                    Object[] o = (Object[]) fieldValue;
                    for (int i = 0; i < o.length; i++) {
                        fieldValueCollection.add(o);
                    }
                } else {
                    fieldValueCollection = (Collection) fieldValue;
                }
            } else {
                fieldValueCollection = new ArrayList();
                fieldValueCollection.add(fieldValue);
            }
        }

        return fieldValueCollection;
    }
	
    /**
     * 根据annotation查询对象属性值
     * 
     * @param mappedField
     * @param bObject
     * @param clazz
     * @return Collection
     */
    @SuppressWarnings({ "rawtypes" })
    private Collection getFieldValues(MappedField mappedField, BObject bObject, Class clazz) {
        Collection fieldValueCollection = null;
        if (mappedField.hasAnnotation(clazz)) {
            fieldValueCollection = getFieldValues(mappedField, bObject);
        }
        return fieldValueCollection;
    }
    
    /**
     * 查询对象属性的值
     * 
     * @param mappedField
     * @param bObject
     * @return
     */
    private Object getFieldValue(MappedField mappedField, BObject bObject) {
        Object fieldValue = null;
        try {
            fieldValue = mappedField.getFieldValue(bObject);
        } catch (IllegalArgumentException e2) {
            throw new DataAccessException(e2);
        }
        return fieldValue;
    }

    protected <T> Key<T> saveOne(T entity) {
        return new Key<T>(getMongoHelper().getCollectionName(entity.getClass()), saveRetId(entity));
    }
	
    public <T> Object saveRetId(T entity) {
        DBObject dbObject = null;
        if (entity instanceof BObject) {
            BObject bo = (BObject) entity;
            innerSave(bo,null);
            dbObject = getMongoHelper().toDBObject(entity);
        } else {
            dbObject = getMongoHelper().toDBObject(entity);
            getMongoHelper().getCollection(entity.getClass()).save(dbObject);
        }

        if (dbObject.get(Mapper.ID_KEY) == null) {
            throw new DataAccessException(new MappingException("Missing _id after save!"));
        }
        isErr();
		getMongoHelper().getMapper().updateKeyInfo(entity, dbObject,
				getMongoHelper().getMapper().createEntityCache());
        return dbObject.get(Mapper.ID_KEY);
    }
	
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected List saveCollection(Collection collection) {
        List ret = null;
        if (collection != null) {
            ret = new ArrayList(collection.size());
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                ret.add(saveOne(it.next()));
            }
        }
        return ret;
    }
    
    // 是否级联新增
    protected Boolean isCascade(BObject bObject, String fieldName) {
        Field field = null;
        Class<?> clazz = bObject.getClass();
        while (field == null && clazz != null) {
            try {
                field = clazz.getDeclaredField(fieldName);
            } catch (Exception e1) {
                logger.trace("", e1);
            }
            clazz = clazz.getSuperclass();
        }

        if (field != null && field.isAnnotationPresent(Cascade.class)) {
            return true;
        } else {
            return false;
        }
    }

	@Override
    public Updates createUpdates() {
        return new UpdatesImpl(mongoHelper);
    }

	/**
	 * 修改
	 */
	@Override
    protected void _update(String id, Updates updates, WriteConcern writeConcern) {
		if(!exists(id)) {
			throw new RecordNotFoundException(this.entityClass.getCanonicalName()+ " id:"+id+ " not found"); 
		}
		if ( updates.getOperations().isEmpty() ) {
			throw new DataAccessException("No updates were specified");
		}
		if(writeConcern==null){
		    collection().update(generateDBObjectInstenceByGiveId(id), new BasicDBObject(updates.getOperations()), false, true);
		}else{
		    collection().update(generateDBObjectInstenceByGiveId(id), new BasicDBObject(updates.getOperations()), false, true,writeConcern);
		}
		isErr();
	}
	
	/**
	 * 根据id生产DbObject（未实例化）
	 * @param id
	 * @return
	 */
	DBObject generateDBObjectInstenceByGiveId(String id) {
		CriteriaQuery<E> query = getCriteriaQueryBuilder().createCriteriaQuery().filter("id = ", id);
		return ((CriteriaQueryImpl<E>)query).getQueryObject();
	}

	/**
	 * 更新CriteriaQuery的查询结果
	 */
	@Override
    protected void _update(CriteriaQuery<E> filter, Updates updates, boolean upsert, WriteConcern writeConcern) {
        CriteriaQueryImpl<E> cquery = (CriteriaQueryImpl<E>) filter;
        if(writeConcern==null){
            collection().update(cquery.getQueryObject(), new BasicDBObject(updates.getOperations()), upsert, true);
        }else{
            collection().update(cquery.getQueryObject(), new BasicDBObject(updates.getOperations()), upsert, true, writeConcern);
        }
    }

	public void isErr(){
		DBObject lastErr = collection().getDB().getLastError();
		if (lastErr.get("err") != null)
			throw new MappingException("Error: " + lastErr.toString()); 
	}
	
    /** turns the object into an ObjectId if it is/should-be one */
	@SuppressWarnings("unused")
    private static Object asObjectIdMaybe(Object id) {
		try {
			if (id instanceof String && ObjectId.isValid((String)id))
				return new ObjectId((String)id);
		} catch (Exception e) {
			//sometimes isValid throws exceptions... bad!
		}
		return id;
	}

	// added by mgl 2010-7-20

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.DAO#aggregate(com.kingdee.cbos.core.framework.dal.Aggregation)
     */
    @Override
    public Results<AggregationResult> aggregate(Aggregation<E> aggregation) {
        return new AggResultsImpl<AggregationResult>(((AggregationImpl<E>) aggregation).asList());
    }

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.DAO#getAggregationBuilder()
     */
    @Override
    public AggregationBuilder<E> getAggregationBuilder() {
        return new AggregationBuilderImpl<E>(getMongoHelper(), collection(), entityClass);
    }

    // added by mgl 2011-8-8
    @SuppressWarnings("rawtypes")
    @Override
    protected void _batchAddNew(List<E> bObjects) throws RecordExistsException, DataIntegrityViolationException,
            DataAccessException {
        List<DBObject> dbObjects = new ArrayList<DBObject>(bObjects.size());

        for (int i = 0; i < bObjects.size(); i++) {
            E bObject = bObjects.get(i);
            if (!bObject.getClass().isAnnotationPresent(Embedded.class)) {
                try {
                    getMongoHelper().getMapper().addMappedClass(bObject.getClass());
                    Utils.generateId(bObject);
                } catch (Exception me) {
                    logger.warn("", me);
                    throw new DataAccessException(me);
                }
            }

            DBObject dbObject = null;

            MappedClass mappedClass = getMongoHelper().getMapper().getMappedClass(bObject);
            if (mappedClass != null) {
                for (MappedField mappedField : mappedClass.getPersistenceFields()) {
                    // 取Reference数据
                    Collection fieldValueCollection = getFieldValues(mappedField, bObject, Reference.class);
                    if (fieldValueCollection != null && fieldValueCollection.size() > 0) {
                        // field对应的数据库名
                        if (isCascade(bObject, mappedField.getJavaFieldName())) {// 级联新增
                            List list = saveCollection(fieldValueCollection);
                            if (dbObject == null)
                                dbObject = getMongoHelper().toDBObject(bObject);
                            if (list != null) {
                                Iterator ite = fieldValueCollection.iterator();
                                List<DBRef> dbrefList = new ArrayList<DBRef>(list.size());
                                for (int j = 0; j < list.size(); j++) {
                                    Key key = (Key) list.get(j);
                                    String fieldDbName = getMongoHelper().getCollectionName(ite.next().getClass());
                                    dbrefList.add(new DBRef(null, fieldDbName, key.getId()));
                                }
                                dbObject.removeField(mappedField.getNameToStore());
                                if (mappedField.isMultipleValues()) {
                                    dbObject.put(mappedField.getNameToStore(), dbrefList);
                                } else {
                                    dbObject.put(mappedField.getNameToStore(), dbrefList.get(0));
                                }
                            }
                        } else {// 非级联新增改变Reference表名
                            if (dbObject == null)
                                dbObject = getMongoHelper().toDBObject(bObject);
                            reNameDBREF(dbObject, mappedField.getNameToStore(), fieldValueCollection);
                        }
                    } else if (mappedField.hasAnnotation(Embedded.class)) {
                        // added by mgl 2010-8-9 支持Embedded对象的级联保存
                        Collection fvc = getFieldValues(mappedField, bObject, Embedded.class);
                        if (fvc != null && fvc.size() > 0) {
                            List<DBObject> list = new ArrayList<DBObject>(fvc.size());
                            for (Object object : fvc) {
                                if (object instanceof BObject) {
                                    DBObject dbo = innerSave((BObject) object, null);
                                    list.add(dbo);
                                }
                            }
                            if (list.size() > 0) {
                                if (dbObject == null)
                                    dbObject = getMongoHelper().toDBObject(bObject);
                                if (mappedField.isMultipleValues()) {
                                    dbObject.put(mappedField.getNameToStore(), list);
                                } else {
                                    dbObject.put(mappedField.getNameToStore(), list.get(0));
                                }
                            }
                        }
                    }
                }
            }

            if (dbObject == null)
                dbObject = getMongoHelper().toDBObject(bObject);

            dbObjects.add(dbObject);
        }

        getMongoHelper().getCollection(bObjects.get(0).getClass()).insert(dbObjects);
        isErr();

        for (int i = 0; i < bObjects.size(); i++) {
            E bObject = bObjects.get(i);
            DBObject dbObject = dbObjects.get(i);
            getMongoHelper().getMapper().updateKeyInfo(bObject, dbObject,
                    getMongoHelper().getMapper().createEntityCache());
            getMongoHelper().getMapper().getMappedClass(bObject)
                    .callLifecycleMethods(PostPersist.class, bObject, dbObject, getMongoHelper().getMapper());
        }
    }
}
