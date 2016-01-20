/**
 * Copyright 1993-2010 Kingdee Software (China), Co. Ltd., All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */


package com.mongo.morphia.complex.core_dal.impl.morphia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.code.morphia.Key;
import com.mongo.morphia.complex.core.BObject;
import com.mongo.morphia.complex.core_dal.Results;


/**
 *
 * @since 2010-7-15
 * @author pl
 */
public class IDResultsImpl<E extends BObject> implements Results<String> {
	
	private final Iterator<Key<E>> iterator;
	
	public IDResultsImpl(Iterable<Key<E>> iterable) {
		this.iterator = iterable.iterator();
	}

	@Override
	public Iterator<String> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public String next() {
		return iterator.next().getId().toString();
	}

	@Override
	public void remove() {
		iterator.remove();
	}

	@Override
	public List<String> asList() {		
		List<String> list = new ArrayList<String>();
        while ( hasNext() ) {
            list.add(next());
        }
        return list;
	}
}
