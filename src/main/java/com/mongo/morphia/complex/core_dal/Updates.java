/**
 * Copyright 1993-2010 Kingdee Software (China) Co., Ltd.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */
package com.mongo.morphia.complex.core_dal;

import java.util.List;
import java.util.Map;

/**
 * 
 * @since 2010-5-27
 * @author pl
 */
public interface Updates {
    /**
     * Fluent updates interface: 
     * {@code new Updates().field("name").set("newName")...
     */
    UpdatesOperation field(String fieldExpr);
    
    
    /**
     * Sets field to the value supplied.
     */
    Updates set(String field, Object value);

    /**
     * Deletes the given field from an object.
     */
    Updates unset(String field);

    /**
     * Increments field by the number value if field is present in the object, otherwise sets field
     * to the number value.
     */
    Updates inc(String field, int value);
    
    
    
    Updates incFloat(String field, float value);

    /**
     * Appends value to field, if field is an existing array, otherwise sets field to the array
     * [value] if field is not present. If field is present but is not an array, an error condition
     * is raised.
     */
    Updates push(String field, Object value);

    /**
     * Appends each value in values to field, if field is an existing array, otherwise sets field to
     * the array values if field is not present. If field is present but is not an array, an error
     * condition is raised.
     */
    Updates pushAll(String field, List<?> values);

    /**
     * Adds value to the array only if its not in the array already.
     */
    Updates addToSet(String field, Object value);

    /**
     * Adds values to the array only if they're not in the array already.
     */
    Updates addToSet(String field, List<?> values);

    /**
     * Removes the first element in an array.
     */
    Updates popFirst(String field);

    /**
     * Removes the last element in an array.
     */
    Updates popLast(String field);

    /**
     * Removes all occurrences of value from field, if field is an array. If field is present but is
     * not an array, an error condition is raised.
     */
    Updates pull(String field, Object value);

    /**
     * Removes all occurrences of each value in value_array from field, if field is an array. If
     * field is present but is not an array, an error condition is raised.
     */
    Updates pullAll(String field, List<?> values);
    
    public Map<String,Map<String,Object>> getOperations();
}