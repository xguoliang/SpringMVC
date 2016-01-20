package com.mongo.morphia.complex.core_dal.impl.morphia;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Polymorphic;
import com.google.code.morphia.mapping.Mapper;
import com.mongo.morphia.complex.core.ContextToken;
import com.mongo.morphia.complex.core.impl.AbstractDynaBean;
import com.mongo.morphia.complex.core_dal.MongoHelper;
import com.mongo.morphia.complex.core_dal.SubSystemUtils;
import com.mongo.morphia.complex.core_dal.datasource.DataSource;
import com.mongo.morphia.complex.core_dal.datasource.DataSourceHelper;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/**
 * morphia帮助类
 * 
 * @author yuanpei_lin
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MongoHelperImpl implements MongoHelper {
    private static Morphia morphia = new Morphia();
    private static Mapper mapper = morphia.getMapper();
    private ContextToken token;
    private String collectionName;
    private Class entityClass;

    public MongoHelperImpl(ContextToken ct, Class entityClass) {
        this.token = ct;
        this.entityClass = entityClass;
        if (!mapper.isMapped(entityClass))
            mapper.addMappedClass(entityClass);
        this.collectionName = mapper.getCollectionName(entityClass);
        String simpleName = entityClass.getSimpleName();

        if (!collectionName.startsWith("T_") && entityClass.getAnnotation(Entity.class) == null) {
            StringBuffer sb = new StringBuffer();
            String subSystemName = SubSystemUtils.getSubSystem(entityClass);
            sb.append("T");
            if (subSystemName != null && !subSystemName.equals("")) {
                sb.append("_");
                sb.append(subSystemName);
            }
            sb.append("_");
            sb.append(simpleName);
            this.collectionName = sb.toString();
            mapper.getMappedClass(entityClass).setCollectionName(this.collectionName);
        }
    }

    public MongoHelperImpl() {

    }

    @Override
    public Morphia getMorphia() {
        return morphia;
    }

    public static void setMorphia(Morphia morphia) {
        MongoHelperImpl.morphia = morphia;
    }

    @Override
    public Mapper getMapper() {
        return mapper;
    }

    public static void setMapper(Mapper mapper) {
        MongoHelperImpl.mapper = mapper;
    }

    @Override
    public Mongo getMongo() {
        DataSource dataSource = DataSourceHelper.getDataSource(token, entityClass);
        return dataSource.getMongo();
    }

    @Override
    public DB getDb() {
        DataSource dataSource = DataSourceHelper.getDataSource(token, entityClass);
        return dataSource.getDB();
    }

    @Override
    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    @Override
    public String getCollectionName(Class clazz) {
        mapper.addMappedClass(clazz);
        String colName = mapper.getCollectionName(clazz);
        String simpleName = clazz.getSimpleName();
        if (!colName.startsWith("T_") && clazz.getAnnotation(Entity.class) == null) {
            StringBuffer sb = new StringBuffer();
            String subSystemName = SubSystemUtils.getSubSystem(clazz);
            sb.append("T");
            if (subSystemName != null && !subSystemName.equals("")) {
                sb.append("_");
                sb.append(subSystemName);
            }
            sb.append("_");
            sb.append(simpleName);
            colName = sb.toString();
            mapper.getMappedClass(clazz).setCollectionName(colName);
        }
        return colName;
    }

    @Override
    public boolean isEntityClass(Class clazz) {
        boolean ret = false;
        if (clazz.getAnnotation(Entity.class) != null)
            ret = true;
        return ret;
    }

    @Override
    public DBObject toDBObject(Object entity) {
        if (entity instanceof AbstractDynaBean) {
            AbstractDynaBean adb = (AbstractDynaBean) entity;
            Map<String, Object> extMap = adb.extended();
            if (extMap != null) {
                for (Iterator iterator = extMap.entrySet().iterator(); iterator.hasNext();) {
                    Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator.next();
                    if (entry.getKey() == null || entry.getValue() == null)
                        iterator.remove();
                }
            }
        }
        DBObject dbObject = mapper.toDBObject(entity);
        if (entity.getClass().getAnnotation(Polymorphic.class) == null) {
            // 继承和多态模型下不能删除className，否则装载不了子类
            if (dbObject.containsField(Mapper.CLASS_NAME_FIELDNAME)) {
                dbObject.removeField(Mapper.CLASS_NAME_FIELDNAME);
            }
        }
        return escape(dbObject, true, true);
    }

    @Override
    public DBCollection getCollection(Class clazz) {
        return getDb(clazz).getCollection(this.getCollectionName(clazz));
    }
    
    @Override
    public DBCollection getCollection() {
        return getDb(getEntityClass()).getCollection(this.getCollectionName());
    }

    @Override
    public DB getDb(Class clazz) {
        DataSource cbDataSource = DataSourceHelper.getDataSource(token, clazz);
        return cbDataSource.getDB();
    }

    public ContextToken getToken() {
        return token;
    }

    @Override
    public Object fromDBObject(DBObject dbObject) {
        return mapper.fromDBObject(getEntityClass(), dbObject, mapper.createEntityCache());
    }

    @Override
    public <T> T fromDBObject(Class<T> clazz, DBObject dbObject) {
        return (T) mapper.fromDBObject(clazz, dbObject, mapper.createEntityCache());
    }

    public Class getEntityClass() {
        return entityClass;
    }
    
    public DBObject escape(DBObject o, boolean top, boolean forward) {
        DBObject rtv = null;
        if (o instanceof BasicDBList) {
            rtv = new BasicDBList();
        } else {
            rtv = new BasicDBObject();
        }

        for (String s : o.keySet()) {
            String key = s;
            if (!top) {
                key = escape(s, forward);
            }
            Object inner = o.get(s);
            if (inner instanceof DBObject) {
                inner = escape((DBObject) inner, false, forward);
            } else if (inner instanceof Map) {
                inner = escape((Map<String, Object>) inner, forward);
            }
            rtv.put(key, inner);
        }
        return rtv;
    }

    private Map<String, Object> escape(Map<String, Object> o, boolean forward) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (String s : o.keySet()) {
            String key = escape(s, forward);
            Object inner = o.get(s);
            if (inner instanceof DBObject) {
                inner = escape((DBObject) inner, false, forward);
            } else if (inner instanceof Map) {
                inner = escape((Map<String, Object>) inner, forward);
            }
            map.put(key, inner);
        }
        return map;
    }

    private String escape(String s, boolean forward) {
        if (forward) {
            if (s.contains(".")) {
                return s.replaceAll("\\.", "~~");
            }
            return s;
        } else {
            if (s.contains("~~")) {
                return s.replaceAll("~~", "\\.");
            }
            return s;
        }
    }
}
