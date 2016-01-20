/**
 * Copyright 1993-2012 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package com.mongo.morphia.complex.core_dal.impl.morphia;

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
