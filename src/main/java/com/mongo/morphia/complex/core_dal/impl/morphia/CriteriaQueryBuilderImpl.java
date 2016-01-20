package com.mongo.morphia.complex.core_dal.impl.morphia;

import com.google.code.morphia.DatastoreImpl;
import com.mongo.morphia.complex.core_dal.CriteriaQuery;
import com.mongo.morphia.complex.core_dal.CriteriaQueryBuilder;
import com.mongodb.DBCollection;

/**
 * 用于建造CriteriaQuery
 * 
 * modified by mgl 2010-7-30
 * 
 * @author yuanpei_lin
 * 
 * @param <T>
 */
public class CriteriaQueryBuilderImpl<T> implements CriteriaQueryBuilder<T> {
    private MongoHelperImpl mongohelper;
    private MongoQueryImpl<T> query;

    public CriteriaQueryBuilderImpl(Class<T> clazz, DBCollection coll, MongoHelperImpl mongohelper) {
        DatastoreImpl ds = new DatastoreImpl(mongohelper.getMorphia(), mongohelper.getMongo(), mongohelper.getDb()
                .getName());
        query = new MongoQueryImpl<T>(clazz, coll, ds);
        this.mongohelper = mongohelper;
    }

    public CriteriaQueryBuilderImpl(Class<T> clazz, DBCollection coll, int offset, int limit,
            MongoHelperImpl mongohelper) {
        DatastoreImpl ds = new DatastoreImpl(mongohelper.getMorphia(), mongohelper.getMongo(), mongohelper.getDb()
                .getName());
        query = new MongoQueryImpl<T>(clazz, coll, ds, offset, limit);
        this.mongohelper = mongohelper;
    }

    @Override
    public CriteriaQuery<T> createCriteriaQuery() {
        return new CriteriaQueryImpl<T>(query, mongohelper);
    }

    @Override
    public CriteriaQuery<T> createCriteriaQuery(String qlString) {
        return new CriteriaQueryImpl<T>(query, qlString);
    }

}
