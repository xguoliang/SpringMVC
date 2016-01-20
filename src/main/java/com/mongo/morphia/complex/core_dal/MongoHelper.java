package com.mongo.morphia.complex.core_dal;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.mapping.Mapper;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/**
 * 
 * @since 2010-7-29
 * @author guolei_mao
 */
@SuppressWarnings("rawtypes")
public interface MongoHelper {
    Morphia getMorphia();

    Mapper getMapper();

    Mongo getMongo();

    DB getDb();
    
    DB getDb(Class clazz);

    String getCollectionName();

    String getCollectionName(Class clazz);

    boolean isEntityClass(Class clazz);

    DBObject toDBObject(Object entity);

    Object fromDBObject(DBObject dbObject);

    <T> T fromDBObject(Class<T> clazz, DBObject dbObject);

    DBCollection getCollection(Class clazz);
    
    DBCollection getCollection();
}
