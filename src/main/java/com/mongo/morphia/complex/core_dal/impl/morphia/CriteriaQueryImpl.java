package com.mongo.morphia.complex.core_dal.impl.morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.google.code.morphia.DatastoreImpl;
import com.google.code.morphia.Key;
import com.google.code.morphia.annotations.Reference;
import com.google.code.morphia.mapping.MappedClass;
import com.google.code.morphia.mapping.MappedField;
import com.google.code.morphia.query.FieldEnd;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.QueryImpl;
import com.mongo.morphia.complex.core_dal.CriteriaQuery;
import com.mongo.morphia.complex.core_dal.CriteriaQueryField;
import com.mongo.morphia.complex.core_dal.exceptions.DataAccessException;
import com.mongodb.DBObject;
import com.mongodb.ReadPreference;

/**
 * 动态查询CriteriaQuery<T>实现
 * 
 * @author yuanpei_lin
 * 
 * @param <T>
 */
public class CriteriaQueryImpl<T> implements CriteriaQuery<T> {
	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CriteriaQueryImpl.class);

    private MongoQueryImpl<T> query;
    @SuppressWarnings("unused")
    private String qlString;

    private MongoHelperImpl mongoHelper;

    public CriteriaQueryImpl(MongoQueryImpl<T> query, MongoHelperImpl mongoHelper) {
        this.query = query;
        this.mongoHelper = mongoHelper;
    }

    public CriteriaQueryImpl(MongoQueryImpl<T> query, String qlString) {
        this.query = query;
        this.qlString = qlString;

    }

    @Override
    public CriteriaQueryField<T> field(String fieldExpr) {
        FieldEnd<? extends Query<T>> fieldPart = query.field(fieldExpr);
        return new CriteriaQueryFieldImpl<T>(fieldPart, this);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    // 如果是关联对象上的字段，做模糊查询，使用query.filter：
    // query.filter("关联字段.关联对象上的某字段名", Pattern.compile(string));
    public CriteriaQuery<T> filter(String condition, Object value) {
        condition = convertIdType(condition);
        Class subClass = subClass(condition);
        if (subClass != null) {
            String[] fields = condition.split("\\.");
            condition = condition.substring(condition.indexOf(".") + 1, condition.length());
            List<Key> subKeyList = subClassFilter(subClass, condition, value);
            if (subKeyList != null && subKeyList.size() > 0) {
                if (subKeyList.size() == 1)
                    query.field(fields[0]).equal(subKeyList.get(0));
                else
                    query.field(fields[0]).hasAnyOf(subKeyList);
            }
        } else {
            // added by mgl 2010-7-23 绕开mophia的陷阱 新版本中已没有这个陷阱
            // if (!condition.startsWith("_id") && value != null &&
            // ObjectId.isValid(value.toString())) {
            // Pattern pattern = Pattern.compile("^.*" + value + ".*$", Pattern.CASE_INSENSITIVE);
            // query.filter(condition, pattern);
            // } else {
            query.filter(condition, value);
            // }
        }
        return this;
    }

    /**
     * 获取Reference Class
     * 
     * @param condition
     * @return
     */
    @SuppressWarnings({ "rawtypes" })
    private Class subClass(String condition) {
        Class ret = null;
        if (condition != null && condition.indexOf(".") > 0) {
            String[] fields = condition.split("\\.");
            if (fields.length > 0) {
                String refFieldName = fields[0];
                Map<String, Class> map = getReferenceClass();
                if (map != null && map.containsKey(refFieldName)) {
                    ret = map.get(refFieldName);
                }
            }
        }
        return ret;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private List subClassFilter(Class subClass, String condition, Object value) {
        condition = convertIdType(condition);
        List<Key> ret = new ArrayList<Key>();

        // modified by mgl 2010-11-11 优化
        String collectionName = mongoHelper.getCollectionName(subClass);
        if (condition.equals("_id")) {
            ret.add(new Key(collectionName, value));
        } else if (condition.startsWith("_id =") || condition.startsWith("_id in")) {
            Collection<?> cc = null;
            if (value.getClass().isArray()) {
                cc = Arrays.asList(value);
            } else {
                cc = (Collection) value;
            }
            for (Object c : cc) {
                ret.add(new Key(collectionName, c));
            }
        } else {
            QueryImpl subQuery = new QueryImpl(subClass, mongoHelper.getDb().getCollection(collectionName),
                    new DatastoreImpl(mongoHelper.getMorphia(), mongoHelper.getMongo(), mongoHelper.getDb().getName()));

            List<Key> keyList = subQuery.filter(condition, value).asKeyList();

            if (keyList != null && keyList.size() > 0) {
                for (Key key : keyList) {
                    ret.add(new Key(collectionName, key.getId()));
                }
            } else {
                // added by mgl 2010-11-8 造个假的
                ret.add(new Key(collectionName, ObjectId.get()));
            }
        }

        return ret;
    }

    /**
     * id 转换为_id
     * 
     * @param condition
     * @return
     */
    private String convertIdType(String condition) {
        StringBuffer sb = null;
        if (condition != null) {
            sb = new StringBuffer();
            String condis[] = condition.split(" ");
            if (condis[0].equals("id")) {
                sb.append("_id");
            } else {
                sb.append(condis[0]);
            }
            for (int i = 1; i < condis.length; i++) {
                sb.append(" " + condis[i]);
            }
        }
        return (sb != null) ? sb.toString() : null;
    }

    @SuppressWarnings("rawtypes")
    private Map<String, Class> getReferenceClass() {
        Map<String, Class> ret = null;
        MappedClass mappedClass = mongoHelper.getMapper().getMCMap()
                .get(query.getEntityClass().getCanonicalName());
        if (mappedClass != null) {
            for (MappedField mappedField : mappedClass.getPersistenceFields()) {
                if (mappedField.getAnnotation(Reference.class) != null) {
                    if (ret == null)
                        ret = new HashMap<String, Class>();
                    ret.put(mappedField.getNameToStore(), mappedField.isSingleValue() ? mappedField.getType()
                            : (Class)mappedField.getSubType());
                }
            }
        }
        return ret;
    }

    @Override
    public CriteriaQuery<T> limit(int value) {

    	//TODO mongodb 2.6 limit too large bug, mongodb crash
    	// https://jira.mongodb.org/browse/SERVER-13537
    	if (value > 10000) {
    		value = 10000;
    		if(LOGGER.isTraceEnabled())
    		LOGGER.trace("limit too large, force to 10000 !!!!!!!, "
    				+ "mongodb 2.6 limit too large bug, mongodb crash"
    				+ " https://jira.mongodb.org/browse/SERVER-13537", new Exception());
    	}
    	
        query.limit(value);
        return this;
    }

    @Override
    public CriteriaQuery<T> offset(int value) {
        query.offset(value);
        return this;
    }

    @Override
    public CriteriaQuery<T> order(String condition) {
        query.order(condition);
        return this;
    }

    public List<T> asList() {
        return query.asList();
    }

    public long countAll() {
        long ret = 0;
        try {
            ret = query.countAll();
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
        return ret;
    }
    
    public long count() {
        long ret = 0;
        try {
            ret = query.count();
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
        return ret;
    }

    public T get() {
        return query.get();
    }

    public Iterator<T> iterator() {
        return query.iterator();
    }

    public List<Key<T>> asKeyList() {
        return query.asKeyList();
    }

    public Iterable<T> fetch() {
        return query.fetch();
    }

    public Iterable<Key<T>> fetchKeys() {
        return query.fetchKeys();
    }

    public Key<T> getKey() {
        return query.getKey();
    }

    public DBObject getFieldsObject() {
        return query.getFieldsObject();
    }

    public DBObject getQueryObject() {
        return query.getQueryObject();
    }

    public DBObject getSortObject() {
        return query.getSortObject();
    }

    public Query<T> getQuery() {
        return this.query;
    }

    // added by mgl 2010-10-8
    @Override
    public CriteriaQuery<T> retrievedFields(boolean include, String... fields) {
        query.retrievedFields(include, fields);
        return this;
    }

    public void setMongoHelper(MongoHelperImpl mongoHelper) {
        this.mongoHelper = mongoHelper;
    }

    public MongoHelperImpl getMongoHelper() {
        return mongoHelper;
    }

    public void enableSnapshotMode() {
        query.enableSnapshotMode();
    }

    public void disableSnapshotMode() {
        query.disableSnapshotMode();
    }

    @Override
    public CriteriaQuery<T> hintIndex(String idxName) {
        query.hintIndex(idxName);
        return this;
    }

    @Override
    public CriteriaQuery<T> readPreference(ReadPreference readerPreference) {
        query.setReadPreference(readerPreference);
        return this;
    }

    @Override
    public CriteriaQuery<T> hintIndex(DBObject idxObject) {
        query.hintIndex(idxObject);
        return this;
    }
}
