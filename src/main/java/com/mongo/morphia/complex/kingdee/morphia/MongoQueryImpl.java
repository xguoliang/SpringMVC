package com.mongo.morphia.complex.kingdee.morphia;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.DatastoreImpl;
import com.google.code.morphia.query.QueryImpl;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.ReadPreference;

/**
 * 类说明:
 * 
 * @author <a href="mailto:guolei_mao@kingdee.com">guolei_mao</a>
 * @version 1.0, 2011-4-7
 */
public class MongoQueryImpl<T> extends QueryImpl<T> {
    private static Logger log = LoggerFactory.getLogger(MongoQueryImpl.class);
    private DBCollection dbColl = null;
    private DBObject indexKeys;
    
    public MongoQueryImpl(Class<T> clazz, DBCollection coll, Datastore ds) {
        super(clazz, coll, ds);
        this.dbColl = coll;
    }

    public MongoQueryImpl(Class<T> clazz, DBCollection coll, Datastore ds, int offset, int limit) {
        super(clazz, coll, ds, offset, limit);
        this.dbColl = coll;
    }

    public MongoQueryImpl(Class<T> clazz, DBCollection coll, DatastoreImpl ds, DBObject baseQuery) {
        super(clazz, coll, ds, baseQuery);
        this.dbColl = coll;
    }
    
    public long count() {
        DBObject query = getQueryObject();
        if (log.isTraceEnabled())
            log.trace("Executing count(" + dbColl.getName() + ") for query: " + query);
        return dbColl.getCount(query, null, super.getLimit(), 0);
    }
    
    @Override
    public DBCursor prepareCursor() {
        DBCursor cursor = super.prepareCursor();
        if(indexKeys!=null)
            cursor.hint(indexKeys);
        return cursor;
    }
    
    public void hintIndex(DBObject indexkeys){
        indexKeys = indexkeys;
    }
    
   public void setReadPreference(ReadPreference preference){
       dbColl.setReadPreference(preference);
   }
}
