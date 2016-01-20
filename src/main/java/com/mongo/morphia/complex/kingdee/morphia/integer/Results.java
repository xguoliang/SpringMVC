package com.mongo.morphia.complex.kingdee.morphia.integer;



import java.util.Iterator;
import java.util.List;

public interface Results<E>  extends Iterable<E>, Iterator<E> {
    /**
     * Takes all the values in the results and adds to a List.
     *
     * @return a List containing all the results
     */
    public List<E> asList();
}
