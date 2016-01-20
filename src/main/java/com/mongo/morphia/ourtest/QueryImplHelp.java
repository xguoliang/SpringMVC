package com.mongo.morphia.ourtest;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.DatastoreImpl;
import com.google.code.morphia.query.QueryImpl;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class QueryImplHelp<T> extends  QueryImpl<T> {

    public QueryImplHelp(Class<T> clazz, DBCollection coll, Datastore ds) {
        super(clazz, coll, ds);

    }

    public QueryImplHelp(Class<T> clazz, DBCollection coll, Datastore ds, int offset, int limit) {
        super(clazz, coll, ds, offset, limit);
   
    }

    public QueryImplHelp(Class<T> clazz, DBCollection coll, DatastoreImpl ds, DBObject baseQuery) {
        super(clazz, coll, ds, baseQuery);

    }
    
   
}

