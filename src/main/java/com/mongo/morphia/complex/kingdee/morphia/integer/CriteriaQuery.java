package com.mongo.morphia.complex.kingdee.morphia.integer;

/**
 * Copyright 1993-2010 Kingdee Software (China) Co., Ltd.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */



import com.mongodb.DBObject;
import com.mongodb.ReadPreference;




/**
 * 
 * @since 2010-5-27
 * @author pl
 */
public interface CriteriaQuery<E> {

    /**
     * <p>
     * Create a filter based on the specified condition and value.
     * </p>
     * <p>
     * <b>Note</b>: Property is in the form of "name op" ("age >").
     * </p>
     * <p>
     * Valid operators are ["=", "==","!=", "<>", ">", "<", ">=", "<=", "in", "nin", "all", "size",
     * "exists"]
     * </p>
     * <p>
     * Examples:
     * </p>
     * 
     * <ul>
     * <li>{@code filter("yearsOfOperation >", 5)}</li>
     * <li>{@code filter("rooms.maxBeds >=", 2)}</li>
     * <li>{@code filter("rooms.bathrooms exists", 1)}</li>
     * <li>{@code filter("stars in", new Long[] 3,4}) //3 and 4 stars (midrange?)}</li>
     * <li>{@code filter("age >=", age)}</li>
     * <li>{@code filter("age =", age)}</li>
     * <li>{@code filter("age", age)} (if no operator, = is assumed)</li>
     * <li>{@code filter("age !=", age)}</li>
     * <li>{@code filter("age in", ageList)}</li>
     * <li>{@code filter("customers.loyaltyYears in", yearsList)}</li>
     * </ul>
     * 
     * <p>
     * You can filter on id properties <strong>if</strong> this query is restricted to a Class<T>.
     */
    CriteriaQuery<E> filter(String condition, Object value);

    /** Fluent query interface: {@code createQuery(Ent.class).field("count").greaterThan(7)...} */
    CriteriaQueryField<E> field(String fieldExpr);

    /**
     * <p>
     * Sorts based on a property. Examples:
     * </p>
     * 
     * <ul>
     * <li>{@code order("age")}</li>
     * <li>{@code order("-age")} (descending sort)</li>
     * <li>{@code order("age,date")}</li>
     * <li>{@code order("age,-date")} (age ascending, date descending)</li>
     * </ul>
     */
    CriteriaQuery<E> order(String condition);

    // CriteriaQuery<E> orderAscending(String condition);
    
    // CriteriaQuery<E> orderDescending(String condition);
    
    
    /**
     * Limit the fetched result set to a certain number of values.
     * 
     * @param value
     *            must be >= 0. A value of 0 indicates no limit.
     */
    CriteriaQuery<E> limit(int value);

    /**
     * Starts the query results at a particular zero-based offset.
     * 
     * @param value
     *            must be >= 0
     */
    CriteriaQuery<E> offset(int value);
    
    /**
     * <p>
     * Generates a string that consistently and uniquely specifies this query. There is no way to
     * convert this string back into a query and there is no guarantee that the string will be
     * consistent across versions.
     * </p>
     * 
     * <p>
     * In particular, this value is useful as a key for a simple memcache query cache.
     * </p>
     */
    @Override
    String toString();

    // added by mgl 2010-10-8
    CriteriaQuery<E> retrievedFields(boolean include, String...fields);
    
    /**
     * Hints as to which index should be used. 
     * @param idxName
     * @return
     */
    CriteriaQuery<E> hintIndex(String idxName);
    
    /**
     * 
     * @param idxObject
     * @return
     */
    CriteriaQuery<E> hintIndex(DBObject idxObject);
    /**
     * 指定是否secondary优先
     * @param readerPreference
     * @return
     */
    CriteriaQuery<E> readPreference(ReadPreference readerPreference);
    
}