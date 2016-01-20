package com.mongo.morphia.complex.kingdee.morphia;



import java.util.Iterator;
import java.util.NoSuchElementException;

import com.google.code.morphia.query.MorphiaIterator;
import com.mongodb.BasicDBObject;

/**
 * 
 * @since 2012-8-14
 * @author guolei_mao
 */
public class MorphiaIteratorgl<T, V> implements Iterator<V> {
    private MongoHelperImpl mongoHelper;
    private MorphiaIterator<T, V> mi;

    public MorphiaIteratorgl(MongoHelperImpl mongoHelper, MorphiaIterator<T, V> mi) {
        this.mongoHelper = mongoHelper;
        this.mi = mi;
    }

    @Override
    public boolean hasNext() {
        return mi.hasNext();
    }

    @Override
    public V next() {
        if (!hasNext())
            throw new NoSuchElementException();
        BasicDBObject dbObj = mi.getNext();
        dbObj = (BasicDBObject) mongoHelper.escape(dbObj, true, false);
        return mi.processItem(dbObj);
    }

    @Override
    public void remove() {
        mi.remove();
    }

}
