/**
 * Copyright 1993-2010 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */


package com.mongo.morphia.complex.core_dal.impl.morphia;

import java.util.Iterator;
import java.util.List;

import com.mongo.morphia.complex.core_dal.Results;



/**
 *
 * @since 2010-7-24
 * @author guolei_mao
 */
public class AggResultsImpl<E> implements Results<E> {
    private List<E> results;
    private Iterator<E> ite;
    
    public AggResultsImpl(List<E> list) {
        results = list;
        ite = results.iterator();
    }

    /* (non-Javadoc)
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<E> iterator() {
        return ite;
    }

    /* (non-Javadoc)
     * @see java.util.Iterator#hasNext()
     */
    @Override
    public boolean hasNext() {
        return ite.hasNext();
    }

    /* (non-Javadoc)
     * @see java.util.Iterator#next()
     */
    @Override
    public E next() {
        return ite.next();
    }

    /* (non-Javadoc)
     * @see java.util.Iterator#remove()
     */
    @Override
    public void remove() {
        ite.remove();
    }

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.Results#asList()
     */
    @Override
    public List<E> asList() {
        return this.results;
    }

}
