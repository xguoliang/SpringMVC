package com.mongo.morphia.complex.core_dal;

public interface CriteriaQueryField<E> {

    CriteriaQuery<E> exists();

    CriteriaQuery<E> doesNotExist();

    CriteriaQuery<E> greaterThan(Object val);

    CriteriaQuery<E> greaterThanOrEq(Object val);

    CriteriaQuery<E> lessThan(Object val);

    CriteriaQuery<E> lessThanOrEq(Object val);

    CriteriaQuery<E> equal(Object val);
    
    // 用不上索引
    @Deprecated
    CriteriaQuery<E> notEqual(Object val);

    CriteriaQuery<E> hasThisOne(Object val);// 用于List或Array字段中是否包含某值

    CriteriaQuery<E> hasAllOf(Iterable<?> vals);

    CriteriaQuery<E> hasAnyOf(Iterable<?> vals);

    CriteriaQuery<E> hasNoneOf(Iterable<?> vals);

    CriteriaQuery<E> hasThisElement(Object val);
    
    // added by mgl 2010-10-13
    CriteriaQuery<E> startsWith(String prefix);

    // 用不上索引
    @Deprecated
    CriteriaQuery<E> startsWithIgnoreCase(String prefix);

    @Deprecated
    CriteriaQuery<E> endsWith(String postfix);

    @Deprecated
    CriteriaQuery<E> endsWithIgnoreCase(String postfix);

    @Deprecated
    CriteriaQuery<E> contains(String string);

    @Deprecated
    CriteriaQuery<E> containsIgnoreCase(String string);
}
